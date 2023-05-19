<template>
  <b-container>
    <board-list-table :boards="boards" :start="start" :notice="notice" />
    <!-- search -->
    <!-- pagenation -->
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
  },
  data() {
    return {
      pgno: 1,
      spp: 20,
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
  },
  methods: {
    async selectBoards() {
      await option(
        {
          pgno: this.pgno,
          key: this.key,
          word: this.word,
          notice: this.notice,
        },
        ({ data }) => {
          this.count = data.count;
          if (data.boards) {
            this.boards = data.boards;
          } else if (this.count != 0) {
            alert("잘못된 페이지 번호 입니다.");
            this.pgno = 1;
            this.key = "";
            this.word = "";
            this.selectBoards();
            this.$router.push({ name: "boardList", params: { pgno: 1 } });
          }
        },
        (error) => {
          alert(error);
          alert("error");
        }
      );
    },
  },
  created() {
    if (this.$route.query.pgno) {
      this.pgno = this.$route.query.pgno;
    }
    if (this.$route.query.key) {
      this.key = this.$route.query.key;
      this.word = this.$route.query.word;
    }
    this.selectBoards();
  },
};
</script>

<style></style>
