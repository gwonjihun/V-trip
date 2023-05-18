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
        <b-form-group label="패스워드" label-for="regist-pw">
          <b-form-input
            id="regist-pw"
            v-model="password"
            type="password"
            placeholder="패스워드를 입력하세요"
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
export default {
  name: "RegistForm",
  data() {
    return {
      id: "",
      password: "",
    };
  },
  props: {
    modalId: String,
  },
  methods: {
    formRegist() {
      if (this.id === "" || this.password === "") {
        alert("아이디와 비밀번호를 입력해주세요");
      }
      console.log(this.id);
      console.log(this.password);
      // axios 통신
    },
  },
};
</script>

<style></style>
