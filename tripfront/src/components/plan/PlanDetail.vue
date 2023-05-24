<template>
  <!-- <b-container> -->
    <b-row > 
    <b-col >
    <kakao-map v-if="isfinish" :trips="trips" :isfinish="isfinish" :input_plan_info="planlist" @trip="inputtrip">
      <search-var @tripList="searchList" v-if="ismodify"></search-var>
    </kakao-map>
    </b-col>
    <b-col lg="4" class ="mt-3 mt-lg-0">
    <div >
    <plan-nav class ="ml-3 mr-3 mt-3" :plan="plan" :ismodify="ismodify" @planinit="handleplaninit"></plan-nav>
    <user-search  class ="ml-3 mr-3 mb-3 " :ismodify="ismodify"></user-search>
    </div>
    <!-- plan-nav에는 여행의 큰 데이터 -->
    <plan-info :plan_init="plan" :init_list="plan_list" :trip="trip" :ismodify="ismodify"
      @modifyhandler="handelmodify"></plan-info>
    <!-- 여행의 세부데이터 전달하고 -->
    </b-col>
    </b-row>
  <!-- </b-container> -->
</template>

<script>
import { HttpStatusCode } from "axios";
import { mapState } from "vuex";
import { detail } from "@/api/planapi";
import KakaoMap from "@/components/plan/KakaoMap.vue";
import SearchVar from "@/components/map/SearchVar.vue";
import UserSearch from "@/components/user/UserSearch.vue";
import PlanNav from "./PlanNav.vue";
import PlanInfo from "./PlanInfo.vue";
// import { updateDetail } from "@/api/planapi";
import { search_title } from "@/api/tripapi";
export default {
  components: { KakaoMap, SearchVar, UserSearch, PlanNav, PlanInfo },
  name: "PlanDetail",
  data() {
    return {
      //이게 여행 권한
      isfinish: false,
      plan: { plan_id: 0, title: "", writerid: "", reads: 0, createat: "", updateat: "", deleteat: "", endDate: "", startDate: "", comment_num: 0, like_num: 0, nickname: "", },
      //이게 여행 경로들
      planlist: [],
      ismodify: false, //수정 권한 여부
      //세부 경로에 대한 정보를 저장해놨네
      plan_list: [],
      send_data: [], // 이건 plan nav 에 전달용
      //trips -> 여행지 등록으로 인한 추가 정보를 전달하기 위함.
      trips: {},

      //이건 map에서 추가한 여행 정보
      trip: {},
    };
  },
  computed: {
    ...mapState("userStore", ["userinfo"]),
  },
  methods: {
    inputtrip(input_trip) {
      //여기서는 선택한 여행지 추가를 위한 이벤트
      this.trip = { ...input_trip };
      console.log("click", this.trips);
    },
    handleplaninit(plan_init) {
      const temp = plan_init;
      this.plan_info = temp;
      this.iscreate = true;
    },
    handelmodify() {
      this.ismodify = !this.ismodify;
    },
    searchList(trips) {
      this.trips = trips;
      console.log("검색후 결과에 대한 이벤트 처리");
    },
    setData() {
      console.log(this.plan.startDate);
      console.log(typeof this.plan.endDate);
      var start_arr = this.plan.startDate.split(" ")[0].split("-");
      var end_arr = this.plan.endDate.split(" ")[0].split("-");

      console.log(start_arr);
      console.log(end_arr);
      var start = new Date(start_arr[0], start_arr[1], start_arr[2]);
      var end = new Date(end_arr[0], end_arr[1], end_arr[2]);
      var btMs = end.getTime() - start.getTime();
      var day = btMs / (1000 * 60 * 60 * 24) + 1;

      for (let i = 1; i <= day; i++) {
        this.plan_list.push({
          days: i,
          trip_list: [],
        });
      }
      //여기서 초기 데이터 가공 진행
      //plan_list는 여행 경로에 대한 값이고
      for (var a of this.planlist) {
        var temp = {};
        temp.content_id = a.content_id;
        temp.addr1 = a.addr1;
        temp.first_image = a.first_image !== "" ? a.first_image : "https://inssago.net/static/img/notimage.jpg";
        temp.latitude = a.latitude;
        temp.longitude = a.longitude;
        temp.plan_id = a.plan_id;
        temp.sido_code = a.sido_code;
        temp.title = a.title;
        this.plan_list[a.days - 1].trip_list.push(temp);
      }
    },
    async getTitle() {
      console.log(this.planlist);
      for (let i = 0; i < this.planlist.length; i++) {
        // this.planlist.forEach((data) => {
        console.log(this.planlist[i]);
        await search_title(
          this.planlist[i].content_id,
          ({ data }) => {
            console.log("title result");
            console.log(data);
            this.planlist[i].addr1 = data.addr1;
            this.planlist[i].title = data.title;
            this.planlist[i].latitude = Number(data.latitude);
            this.planlist[i].longitude = Number(data.longitude);
            this.planlist[i].first_image = data.first_image;
            this.planlist[i].sido_code = data.sido_code;
            // this.planlist[i].push(data);
            console.log("data");
            console.log(this.planlist[i]);
          },
          () => {
            console.log("에러발생");
          }
        );
      }
      this.isfinish = true;
    },
    async getDetail() {
      //여기선 plan table에 있는 계획 큰 정보
      await detail(this.$route.params.plan_id, ({ data, status }) => {
        if (status == HttpStatusCode.NoContent) {
          alert("삭제되었거나 존재하지 않는 글입니다.");
          this.$router.push("/plan");
        }
        this.plan = data.plan;
        console.log(data);
        this.plan.endDate = this.plan.endDate.split(" ")[0];
        this.plan.startDate = this.plan.startDate.split(" ")[0];
        this.planlist = data.planlist;
        console.log("여기가 오니?");
      });

      //이제 여기서 plannav랑 planinfo에 세부 정보를 보내주기 위한 props를 설정해준다.
      /*
      1. plannav에서 사용하던 데이터 구조는 
contentid: 가 들어가있지
plan_id : ""
end_date:"2020-02-03"
share:"0"
start_date:"2020-02-02"
title:"2323"

plans : [{
day : string,
trip_list : {
  content_id : "",
  sido_code : int,
  title : "",
}
}]
      */
    },
    moveModify() {
      this.$router.push({ name: "planModify", params: { plan_id: this.plan.plan_id } });
    },
    moveList() {
      this.$router.push({ name: "planList" });
    },
  },
  async created() {
    await this.getDetail();
    await this.getTitle();
    console.log("그럼 여기는?");
    await this.setData(); //여기서 필요한 데이터 양식으로 전환한다.
    console.log(this.plan_list.length);
  },
};
</script>
<style scoped>
span {
  margin-right: 1rem;
}

button {
  margin-right: 1rem;
}
</style>
<style>
html,
body {
  width: 100%;
  height: 100%;
  margin: 0;
  padding: 0;
}

.map_wrap {
  position: relative;
  overflow: hidden;
  width: 100%;
  height: 350px;
}

.radius_border {
  border: 1px solid #919191;
  border-radius: 5px;
}
/*
.custom_typecontrol {
  position: absolute;
  top: 10px;
  left: 10px;
  overflow: hidden;
  width: 200px;
  height: 30px;
  margin: 0;
  padding: 0;
  z-index: 2;
  font-size: 12px;
  font-family: "Malgun Gothic", "맑은 고딕", sans-serif;
}

.custom_typecontrol span {
  display: block;
  width: 65px;
  height: 30px;
  float: left;
  text-align: center;
  line-height: 30px;
  cursor: pointer;
} */
</style>

<style scoped>
.main {
  /* margin-top: 0;
  padding-top: 2rem;
  background-color: #e4feff;
  min-height: calc(100vh - 4.5rem); */
  max-width: 100vw;
}
/* .contaier {
  width: 100%;
} */
</style>
