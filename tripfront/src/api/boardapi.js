import { apiInstance } from "./index.js";

const api = apiInstance();

async function list(success, fail) {
  await api.get("/board").then(success).catch(fail);
}
async function notice(success, fail) {
  await api.get("/board/notice").then(success).catch(fail);
}
async function boardOption(param, success, fail) {
  await api.get("/board/option", { params: param }).then(success).catch(fail);
}
async function listDetail(content_id, success, fail) {
  await api.get(`/board/${content_id}`).then(success).catch(fail);
}
async function updateReads(content_id, success, fail) {
  await api.get(`/board/${content_id}/reads`).then(success).catch(fail);
}
async function insert(board, success, fail) {
  await api.post("/board", JSON.stringify(board)).then(success).catch(fail);
}
async function update(board, success, fail) {
  await api.put("/board", board).then(success).catch(fail);
}
async function delte(content_id, success, fail) {
  await api.delete(`/board/${content_id}`).then(success).catch(fail);
}
async function commentList(content_id, success, fail) {
  await api.get(`/board/${content_id}/comment`).then(success).catch(fail);
}
async function commentPost(content_id, comment, success, fail) {
  await api.post(`/board/${content_id}/comment`, JSON.stringify(comment)).then(success).catch(fail);
}
async function commentUpdate(content_id, comment, success, fail) {
  await api.put(`/board/${content_id}/comment`, JSON.stringify(comment)).then(success).catch(fail);
}
async function commentDelete(content_id, comment, success, fail) {
  await api.delete(`/board/${content_id}/comment`, JSON.stringify(comment)).then(success).catch(fail);
}
async function likeSelect(content_id, user_id, success, fail) {
  await api.get(`/board/${content_id}/like?user_id=${user_id}`).then(success).catch(fail);
}
async function likeInsert(content_id, user_id, success, fail) {
  await api.post(`/board/${content_id}/like?user_id=${user_id}`).then(success).catch(fail);
}
async function likeDelete(content_id, user_id, success, fail) {
  await api.delete(`/board/${content_id}/like?user_id=${user_id}`).then(success).catch(fail);
}

export {
  commentPost,
  commentDelete,
  commentList,
  commentUpdate,
  delte,
  insert,
  likeDelete,
  likeInsert,
  likeSelect,
  list,
  listDetail,
  updateReads,
  notice,
  update,
  boardOption,
};
