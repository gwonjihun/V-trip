import { login, logout } from "@/api/userapi";
import router from "@/router";

export default {
  namespaced: true,
  state: {
    userId: "", // session용 jwt 토큰으로 전환 후 삭제예정
    token: "", // jwt 토큰용 추후 업데이트
    userInfo: {
      id: String,
      name: String,
      password: String,
      email: String,
      user_type: String,
      nickname: String,
      point: Number,
    },
  },
  getters: {
    isLogin(state) {
      return state.userId != "";
    },
  },
  mutations: {
    SET_USERID(state, userId) {
      state.userId = userId;
    },
    SET_TOKEN(state, token) {
      state.token = token;
    },
    SET_USERINFO(state, userInfo) {
      state.userInfo = userInfo;
    },
  },
  actions: {
    doLogin({ commit }, user) {
      login(
        user,
        ({ data }) => {
          commit("SET_USERID", data.id);
          commit("SET_USERINFO", data);
        },
        () => {
          console.log("아이디와 패스워드를 확인해주세요");
        }
      );
    },
    doLogout({ commit }) {
      logout(
        () => {
          commit("SET_USERID", "");
          commit("SET_USERINFO", {});
          router.push("/");
        },
        () => {
          alert("logout error");
        }
      );
    },
  },
  modules: {},
};
