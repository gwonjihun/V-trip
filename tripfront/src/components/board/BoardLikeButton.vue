<template>
  <b-row align-h="center">
    <b-button v-if="!isLike" variant="primary" :disabled="!isLogin" @click="insLike">좋아요</b-button>
    <b-button v-else variant="danger" :disabled="!isLogin" @click="delLike">좋아요</b-button>
  </b-row>
</template>

<script>
import { likeDelete } from "@/api/boardapi";
import { likeInsert, likeSelect } from "@/api/boardapi";
import { mapState } from "vuex";

export default {
  name: "BoardLikeButton",
  data() {
    return {
      isLike: false,
      content_id: Number,
    };
  },
  computed: {
    ...mapState("userStore", ["isLogin", "userinfo"]),
  },
  methods: {
    getLike() {
      likeSelect(
        this.content_id,
        this.userinfo.id,
        ({ data }) => {
          if (data != 0) this.isLike = true;
        },
        (err) => {
          alert(err);
        }
      );
    },
    insLike() {
      likeInsert(
        this.content_id,
        this.userinfo.id,
        ({ data }) => {
          if (data != 0) this.isLike = true;
          this.$EventBus.$emit("board-reload");
        },
        (err) => {
          alert(err);
        }
      );
    },
    delLike() {
      likeDelete(
        this.content_id,
        this.userinfo.id,
        ({ data }) => {
          if (data != 0) this.isLike = false;
          this.$EventBus.$emit("board-reload");
        },
        (err) => {
          alert(err);
        }
      );
    },
  },
  created() {
    this.content_id = this.$route.params.content_id;
    this.getLike();
  },
};
</script>

<style></style>
