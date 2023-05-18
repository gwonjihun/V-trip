<template>
  <b-modal :id="modalId" @ok="formLogin">
    <h3 slot="modal-title">Login</h3>
    <div>
      <b-form>
        <b-form-group label="아이디" label-for="login-id">
          <b-form-input
            id="login-id"
            v-model="id"
            type="text"
            placeholder="아이디를 입력하세요"
            required
          ></b-form-input>
        </b-form-group>
        <b-form-group label="패스워드" label-for="login-pw">
          <b-form-input
            id="login-pw"
            v-model="password"
            type="password"
            placeholder="패스워드를 입력하세요"
            required
          ></b-form-input>
        </b-form-group>
      </b-form>
    </div>
    <span slot="modal-ok">로그인</span>
    <span slot="modal-cancel">취소</span>
  </b-modal>
</template>

<script>
import { mapActions } from "vuex";

export default {
  name: "LoginForm",
  data() {
    return {
      // dev 초기값
      id: "ssafy",
      password: "1234",
    };
  },
  props: {
    modalId: String,
  },
  methods: {
    ...mapActions("userStore", ["doLogin"]),
    async formLogin() {
      if (this.id == "" || this.password == "") {
        alert("아이디와 비밀번호를 입력해주세요");
        return;
      }
      await this.doLogin({
        id: this.id,
        password: this.password,
      });
    },
  },
};
</script>

<style></style>
