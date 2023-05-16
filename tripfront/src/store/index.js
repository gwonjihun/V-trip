import Vue from "vue";
import Vuex from "vuex";
import { createVuexPersistedState } from "vue-persistedstate";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    userinfo: {
      id: String,
      name: String,
      password: String,
      email: String,
      user_type: String,
      nickname: String,
      point: Number,
    },
  },
  getters: {},
  mutations: {},
  actions: {},
  plugins: [
    createVuexPersistedState({
      key: "trip",
      storage: window.sessionStorage,
    }),
  ],
  modules: {},
});
