<template>
  <b-tabs>
    <b-tab title="게시판">
      <board-list :board-pgno="pgno" :search-key="key" :search-word="user_id" @change-option="handleOption" />
    </b-tab>
    <b-tab title="여행계획"> 여행계획 리스트 </b-tab>
  </b-tabs>
</template>

<script>
import BoardList from "../board/BoardList.vue";
export default {
  name: "UserContents",
  components: { BoardList },
  props: {
    user_id: String,
  },
  data() {
    return {
      pgno: 1,
      key: "writername",
      word: "",
    };
  },
  methods: {
    handleOption(option) {
      this.pgno = option.pgno;
      this.key = option.key;
      this.word = option.word;
    },
  },
};
</script>

<style></style>
