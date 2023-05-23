<template>
  <header>
    <b-navbar toggleable="lg">
      <b-navbar-brand to="/" class="logo">V-Trip</b-navbar-brand>
      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav class="m-auto">
          <b-nav-item to="/">Home</b-nav-item>
          <b-nav-item to="/place">Place</b-nav-item>
          <b-nav-item to="/plan">Plan</b-nav-item>
          <b-nav-item to="/board">Board</b-nav-item>
          <b-nav-item to="/notice">Notice</b-nav-item>
        </b-navbar-nav>
        <b-navbar-nav class="nav-login ml-auto ml-lg-0 p-auto">
          <b-nav-item v-if="isLogin" @click="doLogout">Logout</b-nav-item>
          <b-nav-item v-else v-b-modal.modal-login>Login</b-nav-item>
          <b-nav-item v-if="isLogin" @click="moveUserPage">MyPage </b-nav-item>
          <b-nav-item v-else v-b-modal.modal-regist>Regist </b-nav-item>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
    <div v-if="!isLogin">
      <login-form modal-id="modal-login" />
      <regist-form modal-id="modal-regist" />
    </div>
  </header>
</template>

<script>
import { mapActions, mapState } from "vuex";
import LoginForm from "../user/LoginForm.vue";
import RegistForm from "../user/RegistForm.vue";

export default {
  components: { LoginForm, RegistForm },
  name: "HeaderNav",
  computed: {
    ...mapState("userStore", ["isLogin", "userinfo"]),
  },
  methods: {
    ...mapActions("userStore", ["doLogout"]),
    login() {
      console.log("login");
    },
    regist() {
      console.log("regist");
    },
    moveUserPage() {
      this.$router.push({ name: "userPage", params: { id: this.userinfo.id } }).catch(() => {});
    },
  },
};
</script>

<style scoped>
.nav-login {
  width: 20vw;
  justify-content: end;
}

.logo {
  width: 20vw;
  font-family: DOSPilgiMedium;
  font-size: 1.8rem;
}
</style>
