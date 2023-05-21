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
    <!-- user contents -->
    <user-contents :user_id="id" />
  </b-container>
</template>

<script>
import { userDelete, userInfo, userUpdate } from "@/api/userapi";
import { HttpStatusCode } from "axios";
import { mapActions } from "vuex";
import UserContents from "./UserContents.vue";

export default {
  components: { UserContents },
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
      point: 0,
      modify: false,
    };
  },
  watch: {
    $route() {
      this.getUserInfo();
    },
  },
  methods: {
    ...mapActions("userStore", ["doLogout"]),
    getUserInfo() {
      const user_id = this.$route.params.id;
      userInfo(user_id, ({ data, status }) => {
        if (status == HttpStatusCode.NoContent || data.deleteat != null) {
          alert("존재하지 않거나 탈퇴한 회원입니다");
          this.$router.push("/");
        }
        this.id = data.id;
        this.name = data.name;
        this.password = "";
        this.email = data.email;
        this.nickname = data.nickname;
        this.point = data.point;
      });
    },
    handleDelete() {
      if (confirm("정말로 탈퇴하시겠습니까?")) {
        userDelete(this.id, ({ status }) => {
          if (status == HttpStatusCode.Ok) {
            alert("탈퇴하였습니다");
            this.doLogout();
            this.$router.push("/");
          } else {
            alert("탈퇴 중 문제가 발생했습니다");
          }
        });
      }
    },
    handleUpdate() {
      userUpdate(
        {
          id: this.id,
          password: this.password == "" ? null : this.password,
          email: this.email,
          nickname: this.nickname,
        },
        ({ status }) => {
          if (status == HttpStatusCode.NoContent) {
            alert("수정 실패");
            return;
          }
          alert("수정 성공");
          this.modify = false;
          this.getUserInfo();
        },
        (err) => {
          alert(err);
        }
      );
    },
  },
  created() {
    this.getUserInfo();
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
