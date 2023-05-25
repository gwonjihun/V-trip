import { apiInstance } from "./index.js";

const api = apiInstance();

// async function login(user, success, fail) {
//   await api.post("/user/login", JSON.stringify(user)).then(success).catch(fail);
// }

//목록 조회
async function list(success, fail) {
  await api.get(`/plan`).then(success).catch(fail);
}
//상세 조회
async function detail(plan_id, success, fail) {
  await api.get(`/plan/${plan_id}`).then(success).catch(fail);
}
//등록
async function regist(map, success, fail) {
  await api.post(`/plan`, JSON.stringify(map)).then(success).catch(fail);
}
//삭제
async function deletePlan(plan_id, success, fail) {
  await api.delete(`/plan/${plan_id}`).then(success).catch(fail);
}
//수정
async function updateDetail(plan_id, map, success, fail) {
  await api.put(`/plan/${plan_id}`, map).then(success).catch(fail);
}
// 옵션 조회
async function planSelectOption(params, success, fail) {
  await api.get(`/plan/option`, { params }).then(success).catch(fail);
}
async function sharedelete(params, success, fail) {
  await api.post(`/plan/share/delete`, params).then(success).catch(fail);
}
async function sharesearch(params, success, fail) {
  await api.get(`/plan/share/${params}`).then(success).catch(fail);
}
async function shareinsert(params, success, fail) {
  await api.post(`/plan/share`, params).then(success).catch(fail);
}

export { list, detail, regist, deletePlan, updateDetail, planSelectOption, sharedelete, sharesearch, shareinsert };
