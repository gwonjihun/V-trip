import { option } from "@/api/boardapi";

export default {
  namespaced: true,
  state: {
    pgno: 1,
    spp: 20,
    count: 0,
    boards: [],
    board: {},
    comments: [],
  },
  getters: {
    start(state) {
      return (state.pgno - 1) * state.spp + 1;
    },
  },
  mutations: {
    SET_PGNO(state, pgno) {
      state.pgno = pgno;
    },
    SET_COUNT(state, count) {
      state.count = count;
    },
    SET_BOARDS(state, boards) {
      state.boards = boards;
    },
    SET_BOARD(state, board) {
      state.board = board;
    },
    SET_COMMENTS(state, comments) {
      state.comments = comments;
    },
  },
  actions: {
    async setBoards({ commit }, param) {
      let pgno = param.pgno | 1;
      commit("SET_PGNO", pgno);
      await option(
        param,
        ({ data }) => {
          commit("SET_COUNT", data.count);
          if (data.boards) {
            commit("SET_BOARDS", data.boards);
          } else {
            console.log("잘못된 페이지 번호 입니다.");
            commit("SET_PGNO", 1);
          }
        },
        () => {
          alert("error");
        }
      );
    },
    setBoard(context, content_id) {
      console.log(content_id);
      // axios
    },
  },
  modules: {},
};
