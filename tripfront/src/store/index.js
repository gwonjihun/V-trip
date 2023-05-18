import Vue from "vue";
import Vuex from "vuex";
import { createVuexPersistedState } from "vue-persistedstate";

Vue.use(Vuex);

import userStore from "./modules/userStore";
import planStore from "./modules/planStore";
import boardStore from "./modules/boardStore";

export default new Vuex.Store({
  state: {},
  getters: {},
  mutations: {},
  actions: {},
  plugins: [
    createVuexPersistedState({
      key: "trip",
      storage: window.sessionStorage,
    }),
  ],
  modules: {
    userStore,
    planStore,
    boardStore,
  },
});
