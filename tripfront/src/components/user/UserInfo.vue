<template>
  <b-container class="pt-3">
    <b-row>
      <b-col md="4">
        <p class="user-header">아이디</p>
      </b-col>
      <b-col md="8">
        <b-form-input id="userpage-id" v-model="id" readonly></b-form-input>
      </b-col>
    </b-row>
    <b-row>
      <b-col md="4">
        <p class="user-header">이름</p>
      </b-col>
      <b-col md="8">
        <b-form-input id="userpage-name" v-model="name" readonly></b-form-input>
      </b-col>
    </b-row>
    <b-row>
      <b-col md="4">
        <p class="user-header">비밀번호</p>
      </b-col>
      <b-col md="8">
        <b-form-input id="userpage-password" v-model="password" :readonly="!modify"></b-form-input>
      </b-col>
    </b-row>
    <b-row>
      <b-col md="4">
        <p class="user-header">이메일</p>
      </b-col>
      <b-col md="8">
        <b-form-input id="userpage-email" v-model="email" :readonly="!modify"></b-form-input>
      </b-col>
    </b-row>
    <b-row>
      <b-col md="4">
        <p class="user-header">닉네임</p>
      </b-col>
      <b-col md="8">
        <b-form-input id="userpage-nickname" v-model="nickname" :readonly="!modify"></b-form-input>
      </b-col>
    </b-row>
    <b-row align-h="end" v-if="isMyPage & !modify">
      <b-button @click="modify = true">수정하기</b-button>
      <b-button @click="handleDelete" variant="danger">회원탈퇴</b-button>
    </b-row>
    <b-row align-h="end" v-if="isMyPage & modify">
      <b-button @click="handleUpdate" variant="success">수정하기</b-button>
      <b-button @click="modify = false">취소</b-button>
    </b-row>
  </b-container>
</template>

<script>
import { userInfo } from "@/api/userapi";
import { HttpStatusCode } from "axios";

export default {
  name: "UserInfo",
  props: {
    isMyPage: { type: Boolean, default: false },
  },
  data() {
    return {
      id: "",
      name: "",
      password: "",
      email: "",
      nickname: "",
      modify: false,
    };
  },
  methods: {
    handleDelete() {
      console.log("delete");
    },
    handleUpdate() {
      console.log("update");
    },
  },
  created() {
    userInfo(this.$route.params.id, ({ data, status }) => {
      if (status == HttpStatusCode.NoContent) {
        alert("잘못된 아이디입니다");
        this.$router.push("/");
      }
      this.id = data.id;
      this.name = data.name;
      this.email = data.email;
      this.nickname = data.nickname;
    });
  },
};
</script>

<style scoped>
.row {
  align-items: center;
  margin-bottom: 1rem;
}
.user-header {
  text-align: start;
  font-weight: bold;
  margin: 0;
}
button {
  margin-right: 15px;
}
</style>
