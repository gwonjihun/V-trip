<template>
  <div>
    <b-form-group label="동반자" label-for="user-search-dropdown">
      <b-form-tags id="user-search-dropdown" v-model="value" no-outer-focus class="mb-2">
        <template v-slot="{ tags, disabled, addTag, removeTag }">
          <ul v-if="tags.length > 0" class="list-inline d-inline-block mb-2">
            <li v-for="tag in tags" :key="tag" class="list-inline-item">
              <b-form-tag @remove="removeTag(tag)" :title="tag" :disabled="disabled" variant="info">{{
                tag
              }}</b-form-tag>
            </li>
          </ul>

          <b-dropdown v-if="ismodify" size="sm" variant="outline-secondary" block menu-class="w-100">
            <template #button-content> <b-icon icon="person-fill"></b-icon> 공유 중인 사용자 </template>
            <b-dropdown-form @submit.stop.prevent="() => { }">
              <b-form-group label="Search tags" label-for="tag-search-input" label-cols-md="auto" class="mb-0"
                label-size="sm" :description="searchDesc" :disabled="disabled">
                <b-form-input v-model="search" id="tag-search-input" type="search" size="sm"
                  autocomplete="off"></b-form-input>
              </b-form-group>
            </b-dropdown-form>
            <b-dropdown-divider></b-dropdown-divider>
            <b-dropdown-item-button v-for="option in availableOptions" :key="option"
              @click="onOptionClick({ option, addTag })">
              {{ option }}
            </b-dropdown-item-button>
            <b-dropdown-text v-if="availableOptions.length === 0">
              There are no tags available to select
            </b-dropdown-text>
          </b-dropdown>
        </template>
      </b-form-tags>
    </b-form-group>
  </div>
</template>

<script>
import { selectUserOption } from "@/api/userapi";

export default {
  name: "UserSearch",
  data() {
    return {
      options: ["Apple", "Orange", "Banana", "Lime", "Peach", "Chocolate", "Strawberry"],
      search: "",
      value: [],
    };
  },
  props: {
    ismodify: { type: Boolean, default: false },
    userlist:[],
  },
  watch:{
    userlist(){
      
    }
  },
  computed: {
    criteria() {
      let word = this.search.trim().toLowerCase();
      this.getUserList(word);
      return word;
    },
    availableOptions() {
      const criteria = this.criteria;
      // Filter out already selected options
      const options = this.options.filter((opt) => this.value.indexOf(opt) === -1);
      if (criteria) {
        // Show only options that match criteria
        return options.filter((opt) => opt.toLowerCase().indexOf(criteria) > -1);
      }
      // Show all options available
      return options;
    },
    searchDesc() {
      if (this.criteria && this.availableOptions.length === 0) {
        return "There are no tags matching your search criteria";
      }
      return "";
    },
  },
  methods: {
    onOptionClick({ option, addTag }) {
      addTag(option);
      this.search = "";
    },
    getUserList(word) {
      selectUserOption(
        word,
        ({ data }) => {
          this.options = data.map((item) => item.id);
        },
        (err) => {
          alert(err);
        }
      );
    },
  },
  created() {
    this.getUserList("");
  },
};
</script>

<style></style>
