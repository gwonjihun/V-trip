<template>
  <b-container>
    <div>
      <h3>{{ type === "modify" ? "게시물 수정" : "게시물 등록" }}</h3>
      <b-form>
        <b-form-input type="text" id="title" v-model="board.title" placeholder="제목을 입력해주세요"></b-form-input>
        <b-form-textarea
          type="textarea"
          id="content"
          rows="10"
          v-model="board.content"
          placeholder="내용을 입력해주세요"
        ></b-form-textarea>
        <input type="hidden" id="userId" :value="userinfo.id" readonly />
        <b-button type="submit" @click="onSubmit" v-if="this.type === `register`">등록</b-button>
        <template v-else>
          <b-button type="submit" @click="onSubmit">수정</b-button>
          <b-button type="submit" @click="onDelete">삭제</b-button>
        </template>
        <b-button @click="moveList">취소</b-button>
      </b-form>
    </div>
  </b-container>
</template>

<script>
import { mapState } from "vuex";
import { insert, update, listDetail, delte } from "@/api/boardapi.js";
import { HttpStatusCode } from "axios";

const updateMsg = "수정에 성공했습니다.";
const insertMsg = "등록에 성공했습니다.";
const deleteMsg = "삭제에 성공했습니다.";
const forbiddenMsg = "잘못된 유저정보입니다.";
const updateFailMsg = "수정에 실패했습니다.";
const insertFailMsg = "등록에 실패했습니다.";
const deleteFailMsg = "삭제에 실패했습니다.";

export default {
  data() {
    return {
      board: {
        content_id: "",
        title: "",
        content: "",
        writername: "",
      },
    };
  },
  props: {
    type: { type: String, require: true, default: "modify" },
  },
  computed: {
    ...mapState("userStore", ["isLogin", "userinfo"]),
  },
  created() {
    if (!this.isLogin) {
      alert("로그인 후에 이용해 주세요");
      this.moveList();
    }

    if (this.type === "modify") {
      var param = this.$route.params.content_id;
      listDetail(
        param,
        ({ data }) => {
          this.board.title = data.title;
          this.board.content = data.content;
          this.board.content_id = data.content_id;
          this.board.writername = data.writername;
        },
        (err) => {
          console.log(err);
        }
      );
    }

    this.board.writername = this.userinfo.id;
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      // !this.article.userid && ((msg = "작성자 입력해주세요"), (err = false), this.$refs.userid.focus());
      // err && !this.article.subject && ((msg = "제목 입력해주세요"), (err = false), this.$refs.subject.focus());
      // err && !this.article.content && ((msg = "내용 입력해주세요"), (err = false), this.$refs.content.focus());
      console.log("Submit event call");
      if (!err) alert(msg);
      else this.type === "register" ? this.registBoard() : this.modifyBoard();
    },
    registBoard() {
      console.log("등록 요청을 위한 data" + this.board);
      console.log(this.board);
      insert(
        this.board,
        ({ status }) => {
          if (status == HttpStatusCode.Forbidden) {
            alert(forbiddenMsg);
            return;
          }
          if (status == HttpStatusCode.NoContent) {
            alert(insertFailMsg);
            return;
          }
          alert(insertMsg);
        },
        (err) => {
          console.log("에러발생 : " + err);
        }
      );
      this.moveList();
    },
    async modifyBoard() {
      console.log("수정 요청을 위한 data" + this.board);
      await update(
        this.board,
        ({ status }) => {
          if (status == HttpStatusCode.Forbidden) {
            alert(forbiddenMsg);
            return;
          }
          if (status == HttpStatusCode.NoContent) {
            alert(updateFailMsg);
            return;
          }
          alert(updateMsg);
        },
        (err) => {
          console.log("에러발생 : " + err);
        }
      );
      this.moveList();
    },

    onDelete(event) {
      event.preventDefault();
      delte(
        this.$route.params.content_id,
        ({ status }) => {
          if (status == HttpStatusCode.Forbidden) {
            alert(forbiddenMsg);
            return;
          }
          if (status == HttpStatusCode.NoContent) {
            alert(deleteFailMsg);
            return;
          }
          alert(deleteMsg);
        },
        (err) => {
          console.log("에러발생 : " + err);
        }
      );
      this.moveList();
    },
    moveList() {
      this.$router.push("/board");
    },
  },
};
</script>

<style scoped></style>
