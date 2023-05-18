<template>
  <b-container>
    <div class="border">
      <h1>게시물 등록</h1>
      <b-form>
        <b-form-input type="text" id="title" v-model="board.title" placeholder="제목을 입력해주세요"></b-form-input>
        <b-form-textarea type="textarea" id="content" v-model="board.content" placeholder="내용을 입력해주세요"></b-form-textarea>
        <input type="hidden" id="userId" :value="userId" readonly />
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
export default {
  data() {
    return {
      board: {
        title: "",
        content: "",
      },
    };
  },
  props: {
    type: { type: String, require: true, default: "modify" }
  },
  computed: {
    ...mapState("userStore", ["userId"]),
  },

  created() {
    if (this.userId === "") this.$router.push("/board");
    else console.log(`사용자 ID:` + this.userId);
    if (this.type === "modify") {
      console.log(this.$route.query);
      var param = this.$route.query.content_id;
      //var param = this.$route.params.content_id;
      listDetail(param,
        ({ data }) => {
          console.log(data);
          this.board.title = data.title;
          console.log(data.title);
          console.log(this.board.title);
          this.board.content = data.content;
          this.board.content_id = data.content_id;
          this.board.writername = data.writername;
        },
        (err) => {
          console.log(err)
        });
    }
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

      let msg = "등록을 실패했습니다.";


      insert(this.board, (res) => {
        console.log(res);
        console.log(msg);
      },
        (err) => {
          console.log("에러발생 : " + err);
        });
      // alert(msg);
      // this.$router.push("/board");
    },
    modifyBoard() {
      let msg = "수정을 실패했습니다.";

      console.log("수정 요청을 위한 data" + this.board);
      update(
        this.board,
        (res) => {
          console.log(res);
          console.log(msg);
        },
        (err) => {
          console.log("에러발생 : " + err);
        });

      // alert(msg);
      // this.$router.push("/board");
    },

    onDelete(event) {
      event.preventDefault();
      let msg = "수정을 실패했습니다.";

      var param = this.$route.query.content_id;
      //var param = this.$route.params.content_id;
      console.log(param);
      delte(param,
        (res) => {
          console.log(res);
          console.log(msg);
        },
        (err) => {
          console.log("에러발생 : " + err);
        });

      // alert(msg);
      // this.$router.push("/board");
    },
    moveList() {
      this.$router.push("/board");
    }


  }
};
</script>

<style scoped></style>