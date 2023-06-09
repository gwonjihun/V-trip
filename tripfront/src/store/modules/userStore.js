import { userInfo } from "@/api/userapi";
import { login, logout } from "@/api/userapi";
import { HttpStatusCode } from "axios";
import jwt_decode from "jwt-decode";

export default {
  namespaced: true,
  state: {
    isLogin: false,
    userinfo: {
      id: "",
      name: "",
      password: "",
      email: "",
      user_type: "",
      nickname: "",
      point: 0,
    },
  },
  getters: {},
  mutations: {
    SET_IS_LOGIN(state, isLogin) {
      state.isLogin = isLogin;
    },
    SET_USERINFO(state, userinfo) {
      state.userinfo = userinfo;
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
          commit("SET_IS_LOGIN", true);
          commit("SET_USERINFO", data.userinfo);
          window.sessionStorage.setItem("access-token", data["access-token"]);
          // location.reload();
        },
        () => {
          alert("login error");
        }
      );
    },
    doLogout({ commit }) {
      console.log("logout");
      logout(
        () => {
          commit("SET_IS_LOGIN", false);
          commit("SET_USERINFO", {});
          window.sessionStorage.removeItem("access-token");
          // location.reload();
        },
        () => {
          alert("logout error");
        }
      );
    },
    async getUserInfo({ commit }) {
      let token = window.sessionStorage.getItem("access-token");
      let decode_token = jwt_decode(token);
      console.log(decode_token);
      await userInfo(
        decode_token.id,
        ({ data }) => {
          commit("SET_USERINFO", data);
        },
        () => {
          alert("get userinfo error");
        }
      );
    },
  },
  modules: {},
};
