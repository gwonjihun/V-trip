<template>
  <b-container>
    <b-row align-v="center" align-h="end" class="button-space">
      <b-button v-if="!notice" variant="primary" @click="moveRegist">글쓰기</b-button>
    </b-row>
    <board-list-table v-if="count" :boards="boards" :start="start" :notice="notice" />
    <p v-else>검색결과가 없습니다.</p>
    <!-- search -->
    <b-row align-h="center" class="m-2">
      <b-col cols="2">
        <b-form-select v-model="key" :options="options"></b-form-select>
      </b-col>
      <b-col cols="6">
        <b-form-input id="search-word" v-model="word"></b-form-input>
      </b-col>
      <b-button variant="primary" @click="changeOption">검색</b-button>
    </b-row>
    <!-- pagenation -->
    <b-row align-h="center">
      <b-pagination
        v-model="pgno"
        :total-rows="count"
        :per-page="spp"
        first-number
        last-number
        limit="10"
      ></b-pagination>
    </b-row>
  </b-container>
</template>

<script>
import { boardOption } from "@/api/boardapi";
import BoardListTable from "./BoardListTable.vue";

export default {
  components: { BoardListTable },
  name: "BoardList",
  props: {
    notice: { type: Boolean, require: true, default: false },
    boardPgno: Number,
    searchKey: String,
    searchWord: String,
  },
  data() {
    return {
      pgno: 1,
      spp: 10,
      // spp: 2, // pagination test
      key: "",
      word: "",
      count: 0,
      boards: [],
      options: [
        { value: "none", text: "---------" },
        { value: "title", text: "제목" },
        { value: "writername", text: "작성자" },
      ],
    };
  },
  computed: {
    start() {
      return (this.pgno - 1) * this.spp + 1;
    },
  },
  watch: {
    pgno() {
      this.changeOption();
      this.selectBoards();
    },
    searchKey() {
      this.selectBoards();
    },
    searchWord() {
      this.selectBoards();
    },
  },
  methods: {
    changeOption() {
      this.$emit("change-option", { pgno: this.pgno, key: this.key, word: this.word });
    },
    async selectBoards() {
      await boardOption(
        {
          pgno: this.pgno,
          spp: this.spp,
          key: this.searchKey,
          word: this.searchWord,
          notice: this.notice,
        },
        ({ data }) => {
          console.log(data);
          this.count = data.count;
          if (data.boards) {
            this.boards = data.boards;
          } else if (this.count != 0) {
            alert("잘못된 페이지 번호 입니다.");
            this.pgno = 1;
          }
        },
        (error) => {
          alert(error);
          alert("error");
        }
      );
    },
    moveRegist() {
      this.$router.push({ name: "boardRegist" });
    },
  },
  async created() {
    await this.selectBoards();
    this.pgno = this.boardPgno;
    this.key = this.searchKey;
    this.word = this.searchWord;
  },
  async mounted() {
    await this.selectBoards();
  },
};
</script>

<style scoped>
.button-space {
  height: 4rem;
}
</style>
