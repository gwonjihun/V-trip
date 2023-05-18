import { apiInstance } from "./index.js";

const api = apiInstance();

// async function login(user, success, fail) {
//   await api.post("/user/login", JSON.stringify(user)).then(success).catch(fail);
// }

async function list(success, fail) {
  await api.get(`/plan`).then(success).catch(fail);
}

async function detail(plan_id, success, fail) {
  await api.get(`/plan/${plan_id}`).then(success).catch(fail);
}
async function regist(map, success, fail) {
  await api.post(`/plan`, JSON.stringify(map)).then(success).catch(fail);
}
async function deletePlan(plan_id, map, success, fail) {
  await api.delete(`/plan/${plan_id}`, JSON.stringify(map)).then(success).catch(fail);
}
async function updateDetail(plan_id, map, success, fail) {
  await api.put(`/plan/${plan_id}`, JSON.stringify(map)).then(success).catch(fail);
}

export { list, detail, regist, deletePlan, updateDetail };
