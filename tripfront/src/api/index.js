import axios from "axios";

const API_BASE_URL = "http://localhost:8080/trip/tripapi";
const OPEN_AREA_URL =
  "https://apis.data.go.kr/B551011/KorService1/areaCode1?serviceKey=TbajtGmRP5AZsCchDBL%2F79v1wL59nnRBKwCLIAwuHxy8KEW5L6Oa%2BdO9VQgX0Ykzz98jxMwp%2FSZBG9vONpACqw%3D%3D&numOfRows=20&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json";
// axios 객체 생성
function apiInstance() {
  //Back-end api
  const instance = axios.create({
    baseURL: API_BASE_URL,
    headers: {
      "Content-type": "application/json",
    },
  });
  return instance;
}

function planInstance() {
  // Open Api
  const instance = axios.create({
    baseURL: OPEN_AREA_URL,
    headers: {
      "Content-type": "application/json",
    },
  });
  return instance;
}

function kakaoFetch(keyword) {
  return axios.create({
    baseURL: "https://dapi.kakao.com/v2/search/web",
    headers: {
      Authorization: "KakaoAK [api key]",
    },
    params: { query: keyword },
  });
}

export { apiInstance, planInstance, kakaoFetch };
