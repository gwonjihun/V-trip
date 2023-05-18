<template>
  <b-container>
    <b-table striped hover no-border-collapse :fields="fields" :items="boards" @row-clicked="clickRow">
      <template #cell(index)="data">
        {{ data.index + start }}
      </template>
    </b-table>
    <!-- search -->
    <!-- pagenation -->
  </b-container>
</template>

<script>
import { option } from "@/api/boardapi";

export default {
  name: "BoardList",
  data() {
    return {
      pgno: 1,
      spp: 20,
      key: "",
      word: "",
      count: 0,
      fields: [
        {
          key: "index",
          label: "번호",
        },
        {
          key: "title",
          label: "제목",
        },
        {
          key: "nickname",
          label: "작성자",
        },
        {
          key: "reads",
          label: "조회수",
        },
        {
          key: "comment_num",
          label: "댓글수",
        },
        {
          key: "like_num",
          label: "좋아요수",
        },
        {
          key: "createat",
          label: "작성일",
        },
      ],
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
        () => {
          alert("error");
        }
      );
    },
    clickRow(item) {
      console.log(item.content_id);
      this.$router.push({ name: "boardDetail", params: { content_id: item.content_id } });
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
