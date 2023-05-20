export default {
  namespaced: true,
  state: {
    isUpdate: 0,
  },
  getters: {},
  mutations: {
    UPDATE(state) {
      state.isUpdate += 1;
    },
    RELOAD(state) {
      state.isUpdate = false;
    },
  },
  actions: {
    update({ commit }) {
      commit("UPDATE");
    },
    reload({ commit }) {
      commit("RELOAD");
    },
  },
  modules: {},
};
