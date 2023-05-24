<template>
  <b-container class="main">
    <h2>{{ isMyPage ? "마이페이지" : "유저정보" }}</h2>
    <user-info :isMyPage="isMyPage" />
    <!-- test -->
    <!-- <user-search /> -->
  </b-container>
</template>

<script>
import UserInfo from "@/components/user/UserInfo.vue";
import { mapState } from "vuex";
// import UserSearch from "@/components/user/UserSearch.vue";

export default {
  components: { UserInfo },
  name: "UserView",
  computed: {
    ...mapState("userStore", ["userinfo"]),
    isMyPage() {
      return this.userinfo.id === this.$route.params.id;
    },
  },
};
</script>

<style></style>
