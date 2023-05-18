export default {
  namespaced: true,
  state: {
    userId: "ssafy",
    userInfo: {
      id: String,
      name: String,
      password: String,
      email: String,
      user_type: String,
      nickname: String,
      point: Number,
    },
    token: "", // jwt 토큰용 추후 업데이트
  },
  getters: {
    isLogin(state) {
      return state.userId != "";
    },
  },
  mutations: {},
  actions: {},
  modules: {},
};
