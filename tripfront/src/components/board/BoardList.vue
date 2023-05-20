<template>
  <b-container>
    <b-row align-v="center" align-h="end" class="button-space">
      <b-button variant="primary" @click="moveRegist">글쓰기</b-button>
    </b-row>
    <board-list-table :boards="boards" :start="start" :notice="notice" />
    <!-- search -->
    <!-- pagenation -->
    <b-row align-h="center">
      <b-pagination
        v-model="pgno"
        :total-rows="maxPg"
        :per-page="spp"
        first-number
        last-number
        limit="10"
      ></b-pagination>
      <!-- <b-pagination :pageSetting="pageDataSetting(count, maxPg, spp, block)" /> -->
    </b-row>
  </b-container>
</template>

<script>
import { option } from "@/api/boardapi";
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
      // spp: 20,
      spp: 1, // pagination test
      key: "",
      word: "",
      count: 0,
      boards: [],
    };
  },
  computed: {
    start() {
      return (this.pgno - 1) * this.spp + 1;
    },
    maxPg() {
      return Math.ceil(this.count / this.spp);
    },
  },
  watch: {
    pgno() {
      this.changeOption();
    },
  },
  methods: {
    changeOption() {
      this.$emit("change-option", { pgno: this.pgno, key: this.key, word: this.word });
      this.selectBoards();
    },
    async selectBoards() {
      console.log("selectBoards start " + this.pgno);
      await option(
        {
          pgno: this.pgno,
          spp: this.spp,
          key: this.searchKey,
          word: this.searchKey,
          notice: this.notice,
        },
        ({ data }) => {
          this.count = data.count;
          if (data.boards) {
            this.boards = data.boards;
          } else if (this.count != 0) {
            alert("잘못된 페이지 번호 입니다.");
            this.pgno = 1;
            // this.key = "";
            // this.word = "";
            // this.selectBoards();
            // this.$router.push({ name: "boardList", params: { pgno: 1 } });
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
    // if (this.$route.query.pgno) {
    //   this.pgno = this.$route.query.pgno;
    // }
    // if (this.$route.query.key) {
    //   this.key = this.$route.query.key;
    //   this.word = this.$route.query.word;
    // }
  },
};
</script>

<style scoped>
.button-space {
  height: 4rem;
}
</style>
