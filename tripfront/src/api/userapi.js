import { apiInstance } from "./index.js";

const api = apiInstance();

// async function login(user, success, fail) {
//   await api.post(`/user/login`, JSON.stringify(user)).then(success).catch(fail);
// }

async function login(user, success, fail) {
  await api.post("/user/login", user).then(success).catch(fail);
}

function logout(success, fail) {
  api.get("/user/logout").then(success).catch(fail);
}

async function userRegist(user, success, fail) {
  await api.post("/user/regist", JSON.stringify(user)).then(success).catch(fail);
}

async function userUpdate(user, success, fail) {
  await api.put("/user/update", JSON.stringify(user)).then(success).catch(fail);
}

async function userDelete(userid, success, fail) {
  await api.delete(`/user/delete/${userid}`).then(success).catch(fail);
}

async function userInfo(userid, success, fail) {
  await api.get(`/user/${userid}`).then(success).catch(fail);
}

async function selectUserOption(word, success, fail) {
  await api.get(`/user/option?word=${word}&spp=10`).then(success).catch(fail);
}

async function postUserImg(id, img, config, success, fail) {
  await api.post(`/user/img/${id}`, img, config).then(success).catch(fail);
}

function getUserImgUrl(id) {
  return `${api.defaults.baseURL}/userimg/${id}`;
}

export { login, logout, userRegist, userUpdate, userInfo, userDelete, selectUserOption, postUserImg, getUserImgUrl };
