<template>
  <b-modal :id="modalId" @ok="formRegist">
    <h3 slot="modal-title">Regist</h3>
    <div>
      <b-form>
        <b-form-group label="아이디" label-for="regist-id">
          <b-form-input
            id="regist-id"
            v-model="id"
            type="text"
            placeholder="아이디를 입력하세요"
            required
          ></b-form-input>
        </b-form-group>
        <b-form-group label="이름" label-for="regist-name">
          <b-form-input
            id="regist-name"
            v-model="name"
            type="text"
            placeholder="이름을 입력하세요"
            required
          ></b-form-input>
        </b-form-group>
        <b-form-group label="패스워드" label-for="regist-pw">
          <b-form-input
            id="regist-pw"
            v-model="password"
            type="password"
            placeholder="패스워드를 입력하세요"
            required
          ></b-form-input>
        </b-form-group>
        <b-form-group label="패스워드 확인" label-for="regist-repw">
          <b-form-input
            id="regist-repw"
            v-model="repw"
            type="password"
            placeholder="패스워드를 한번 더 입력하세요"
            required
          ></b-form-input>
        </b-form-group>
        <b-form-group label="이메일" label-for="regist-eamil">
          <b-form-input
            id="regist-email"
            v-model="email"
            type="email"
            placeholder="이메일을 입력하세요"
            required
          ></b-form-input>
        </b-form-group>
        <b-form-group label="닉네임" label-for="regist-nickname">
          <b-form-input
            id="regist-nickname"
            v-model="nickname"
            type="text"
            placeholder="닉네임을 입력하세요"
            required
          ></b-form-input>
        </b-form-group>
      </b-form>
    </div>
    <span slot="modal-ok">회원가입</span>
    <span slot="modal-cancel">취소</span>
  </b-modal>
</template>

<script>
import { userRegist } from "@/api/userapi";

export default {
  name: "RegistForm",
  data() {
    return {
      id: "test",
      name: "test",
      password: "1234",
      repw: "1234",
      email: "test@test.com",
      nickname: "test",
    };
  },
  props: {
    modalId: String,
  },
  methods: {
    formRegist() {
      if (this.id === "" || this.password === "") {
        alert("아이디와 비밀번호를 입력해주세요");
        return;
      }
      if (this.password !== this.repw) {
        alert("비밀번호가 다릅니다.");
        return;
      }
      if (this.email == "") {
        alert("이메일을 입력해주세요");
        return;
      }
      if (this.nickname == "") {
        alert("닉네임을 입력해주세요");
        return;
      }
      console.log(this.id);
      console.log(this.password);
      // axios 통신
      userRegist(
        {
          id: this.id,
          name: this.name,
          password: this.password,
          email: this.email,
          user_type: "user",
          nickname: this.nickname,
        },
        ({ data }) => {
          console.log(data);
          if (data === "success") {
            alert("등록 성공");
          }
          if (data === "duplicated") {
            alert("중복된 아이디입니다");
          }
          if (data === "fail") {
            alert("등록 실패");
          }
        },
        (err) => {
          alert(err);
        }
      );
    },
  },
};
</script>

<style></style>
