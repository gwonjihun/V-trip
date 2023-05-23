<template>
  <b-card>
    <b-container>
      <b-row>
        <b-col cols="10" class="px-0">
          <b-input type="text" v-model="comment" />
        </b-col>
        <b-col cols="2" class="px-0 pl-2">
          <b-button variant="primary" @click="registComment">등록</b-button>
        </b-col>
      </b-row>
    </b-container>
  </b-card>
</template>

<script>
import { commentPost } from "@/api/boardapi";
import { mapActions, mapState } from "vuex";
export default {
  name: "CommentForm",
  props: {
    content_id: Number,
    plink: Number,
  },
  data() {
    return {
      comment: "",
      link: 0,
    };
  },
  computed: {
    ...mapState("userStore", ["userinfo"]),
  },
  created() {
    this.link = this.plink | 0;
  },
  methods: {
    ...mapActions("boardStore", ["update"]),
    registComment() {
      if (this.userinfo.id == "") {
        alert("로그인 후에 이용 가능합니다.");
        return;
      }
      if (this.comment == "") {
        alert("댓글을 입력해주세요");
        return;
      }
      commentPost(
        this.content_id,
        {
          comment: this.comment,
          writername: this.userinfo.id,
          content_id: this.content_id,
          link: this.link,
        },
        () => {
          this.update();
          this.$EventBus.$emit("board-reload");
        },
        (err) => {
          alert(err);
        }
      );
    },
  },
};
</script>

<style scoped>
button {
  width: 100%;
}
</style>
