<template>
  <b-container class="mt-2">
    <!-- <b-table :items="comments" /> -->
    <comment-item
      v-for="comment in comments"
      :key="comment.comment_id"
      v-bind="comment"
      @regist-comment="getCommentList"
    />
    <br />
    <comment-form :content_id="content_id" @regist-comment="getCommentList" />
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
  methods: {
    getCommentList() {
      commentList(
        this.content_id,
        ({ data }) => (this.comments = data),
        (err) => console.log(err)
      );
    },
  },
  created() {
    this.getCommentList();
  },
};
</script>

<style scope></style>
