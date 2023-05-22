import { apiInstance, planInstance } from "./index.js";

const api = apiInstance();
const api_trip = planInstance();
// async function login(user, success, fail) {
//   await api.post("/user/login", JSON.stringify(user)).then(success).catch(fail);
// }
async function search(param, success, fail) {
  await api.get("/info/search", { params: param }).then(success).catch(fail);
}
async function search_title(param, success, fail) {
  await api.get(`/info/search/{param}`, { params: param }).then(success).catch(fail);
}
async function sido(success, fail) {
  await api_trip.get("").then(success).catch(fail);
}
async function hotplace(success, fail) {
  await api.get("/info/hotplace").then(success).catch(fail);
}

export { hotplace, search, sido ,search_title};
