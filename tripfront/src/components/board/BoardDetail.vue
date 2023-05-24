<template>
  <b-container>
    <b-row class="mt-5">
      <b-col lg="8">
        <h4 class="title float-left">{{ board.title }}</h4>
      </b-col>
      <b-col lg="4" class="mt-3 mt-lg-0">
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
          <p class="content mt-3 mb-5 mx-3 mx-lg-5">{{ board.content }}</p>
        </div>
      </b-col>
    </b-row>
    <board-like-button />
    <hr />
    <!-- under -->
    <b-row>
      <b-col cols="12" sm="8">
        <div class="float-left mx-3">
          <span>조회수: {{ board.reads }}</span>
          <span>좋아요수: {{ board.like_num }}</span>
          <span>댓글수: {{ board.comment_num }}</span>
        </div>
      </b-col>
      <b-col cols="12" sm="4">
        <div class="float-right">
          <b-button v-if="userinfo.id == board.writername" @click="moveModify">수정하기</b-button>
          <b-button @click="moveList">글목록</b-button>
          <!-- <b-button @click="toggleComment = !toggleComment">{{ toggleComment ? "댓글닫기" : "댓글보기" }}</b-button> -->
        </div>
      </b-col>
    </b-row>
    <!-- comment -->
    <board-comment :content_id="board.content_id" class="mx-1" />
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
  text-align: start;
}

.content {
  white-space: pre;
  text-align: start;
}
.title {
  font-weight: bold;
  font-size: 1.8rem;
}
</style>
