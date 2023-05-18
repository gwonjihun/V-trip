import { login, logout } from "@/api/userapi";
import { HttpStatusCode } from "axios";

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
    async doLogin({ commit }, user) {
      await login(
        user,
        ({ data, status }) => {
          if (status == HttpStatusCode.NoContent) {
            alert("아이디와 패스워드를 확인해주세요");
            return;
          }
          commit("SET_USERID", data.id);
          commit("SET_USERINFO", data);
        },
        () => {
          alert("login error");
        }
      );
    },
    doLogout({ commit }) {
      logout(
        () => {
          commit("SET_USERID", "");
          commit("SET_USERINFO", {});
        },
        () => {
          alert("logout error");
        }
      );
    },
  },
  modules: {},
};
