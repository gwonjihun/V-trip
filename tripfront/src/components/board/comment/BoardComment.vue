<template>
  <b-container class="mt-2">
    <br />
    <p v-if="!comments.length">아직 댓글이 없습니다. 첫 댓글이 주인공이 되어보세요!</p>
    <comment-item v-for="comment in comments" :key="comment.comment_id" v-bind="comment" />
    <br />
    <comment-form :content_id="content_id" />
  </b-container>
</template>

<script>
import { commentList } from "@/api/boardapi";
import CommentItem from "./CommentItem.vue";
import CommentForm from "./CommentForm.vue";

export default {
  components: { CommentItem, CommentForm },
  name: "BoardComment",
  props: {
    content_id: Number,
  },
  data() {
    return {
      comments: [],
    };
  },
  watch: {
    content_id() {
      this.getCommentList();
    },
  },
  methods: {
    getCommentList() {
      commentList(
        this.content_id,
        ({ data }) => (this.comments = data),
        (err) => {
          alert(err);
        }
      );
    },
  },
  created() {
    // this.getCommentList();
    this.$EventBus.$on("board-reload", () => {
      this.getCommentList();
    });
  },
  mounted() {
    this.getCommentList();
  },
};
</script>

<style scoped></style>
