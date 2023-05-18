<template>
  <b-container>
    <b-row>
      <b-col cols="8">
        <h4 class="float-left">{{ board.title }}</h4>
      </b-col>
      <b-col cols="4">
        <p class="mb-1">작성자: {{ board.writer.nickname }}</p>
        <p class="mb-1">작성일: {{ board.createat }}</p>
        <p class="mb-1" v-if="board.updateat">수정일: {{ board.updateat }}</p>
      </b-col>
    </b-row>
    <hr />
    <b-row>
      <b-col>
        <p class="float-left">{{ board.content }}</p>
      </b-col>
    </b-row>
    <hr />
    <b-row>
      <b-col>
        <div class="float-right">
          <span>조회수: {{ board.reads }}</span>
          <span>좋아요수: {{ board.like_num }}</span>
          <span>댓글수: {{ board.comment_num }}</span>
          <b-button>댓글보기</b-button>
        </div>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { listDetail } from "@/api/boardapi";

export default {
  name: "BoardDetail",
  data() {
    return {
      board: {
        content_id: Number,
        title: String,
        content: String,
        writername: String,
        reads: Number,
        createat: String,
        updateat: String,
        deleteat: String,

        comment_num: Number,
        like_num: Number,

        writer: {},
      },
      comments: [],
    };
  },
  created() {
    listDetail(this.$route.params.content_id, ({ data }) => {
      this.board = data;
    });
  },
};
</script>

<style scope>
span {
  margin-right: 1rem;
}
</style>
