<template>
  <b-container>
    <b-row>
      <b-col cols="8" align-self="center">
        <h4 class="float-left">{{ board.title }}</h4>
      </b-col>
      <b-col cols="4">
        <p class="mb-1">작성자: {{ board.nickname }}</p>
        <p class="mb-1">작성일: {{ board.createat }}</p>
        <p class="mb-1" v-if="board.updateat">수정일: {{ board.updateat }}</p>
      </b-col>
    </b-row>
    <hr />
    <!-- content -->
    <b-row>
      <b-col>
        <div>
          <p>{{ board.content }}</p>
        </div>
      </b-col>
    </b-row>
    <board-like-button />
    <hr />
    <!-- under -->
    <b-row>
      <b-col>
        <div class="float-left">
          <span>조회수: {{ board.reads }}</span>
          <span>좋아요수: {{ board.like_num }}</span>
          <span>댓글수: {{ board.comment_num }}</span>
        </div>
      </b-col>
      <b-col>
        <div class="float-right">
          <b-button v-if="userinfo.id == board.writername" @click="moveModify">수정하기</b-button>
          <b-button @click="moveList">글목록</b-button>
          <!-- <b-button @click="toggleComment = !toggleComment">{{ toggleComment ? "댓글닫기" : "댓글보기" }}</b-button> -->
        </div>
      </b-col>
    </b-row>
    <!-- comment -->
    <board-comment :content_id="board.content_id" />
  </b-container>
</template>

<script>
import { listDetail } from "@/api/boardapi";
import { HttpStatusCode } from "axios";
import { mapState } from "vuex";
import BoardComment from "./comment/BoardComment.vue";
import BoardLikeButton from "./BoardLikeButton.vue";

export default {
  components: { BoardComment, BoardLikeButton },
  name: "BoardDetail",
  data() {
    return {
      toggleComment: false,
      board: {
        content_id: 0,
        title: "",
        content: "",
        writername: "",
        reads: 0,
        createat: "",
        updateat: "",
        deleteat: "",

        comment_num: 0,
        like_num: 0,

        nickname: "",
      },
      comments: [],
    };
  },
  computed: {
    ...mapState("userStore", ["userinfo"]),
  },
  methods: {
    getDetail() {
      listDetail(this.$route.params.content_id, ({ data, status }) => {
        if (status == HttpStatusCode.NoContent) {
          alert("삭제되었거나 존재하지 않는 글입니다.");
          this.$router.push("/board");
        }
        this.board = data;
      });
    },
    moveModify() {
      this.$router.push({ name: "boardModify", params: { content_id: this.board.content_id } });
    },
    moveList() {
      this.$router.push({ name: "boardList" });
    },
  },
  created() {
    this.getDetail();
    this.$EventBus.$on("board-reload", () => {
      this.getDetail();
    });
  },
};
</script>

<style scoped>
span {
  margin-right: 1rem;
}
button {
  margin-right: 1rem;
}
pre {
  text-align: start;
}
p {
  white-space: pre;
  text-align: start;
}
</style>
