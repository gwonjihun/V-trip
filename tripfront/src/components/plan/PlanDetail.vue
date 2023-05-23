<template>
  <b-container>
    <search-var v-if="ismodify"></search-var>
    <kakao-map :planlist="trips"></kakao-map>

    <plan-nav :plan="plan" :ismodify="ismodify"></plan-nav>
    <!-- plan-nav에는 여행의 큰 데이터 -->
    <plan-info :plan_init="plan" :init_list="plan_list"></plan-info>
    <!-- 여행의 세부데이터 전달하고 -->
    <b-button v-b-toggle.my-collapse>사용자 입력</b-button>
    <b-collapse id="my-collapse">
      <user-search v-if="ismodify"></user-search>
    </b-collapse>
  </b-container>
</template>

<script>
import { HttpStatusCode } from "axios";
import { mapState } from "vuex";
import { detail } from "@/api/planapi";
import KakaoMap from "@/components/map/KakaoMap.vue";
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
      plan: {
        plan_id: 0,
        title: "",
        writerid: "",
        reads: 0,
        createat: "",
        updateat: "",
        deleteat: "",
        endDate: "",
        startDate: "",
        comment_num: 0,
        like_num: 0,

        nickname: "",
      },
      //이게 여행 경로들
      planlist: [],
      ismodify: false, //수정 권한 여부 
      plan_list: [
      ],
      send_data: [] // 이건 plan nav 에 전달용
    };
  },
  computed: {
    ...mapState("userStore", ["userinfo"]),
  },
  methods: {
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
          trip_list: []
        });
      }
      for (var a of this.planlist) {
        var temp = {};
        temp.content_id = a.content_id;
        temp.addr1 = a.addr1;
        temp.first_image = a.first_image;
        temp.latitude = a.longitude;
        temp.longitude = a.latitude;
        temp.plan_id = a.plan_id;
        temp.sido_code = a.sido_code;
        temp.title = a.title;
        this.plan_list[a.days - 1].trip_list.push(temp);
      }
    },
    // planupdate: function () {
    // if (!this.isLogin) {
    //     alert("로그인 후에 이용해 주세요");
    //     return;
    //   }
    //   const pl = {
    //     endDate: this.plan_init.end_date,
    //     startDate: this.plan_init.start_date,
    //     title: this.plan_init.title,
    //     share: this.plan_init.share,
    //   }
    //   this.target = { plan: pl, planlist: [] };
    //   console.log(this.target.plan);
    //   console.log(typeof (this.target.plan));
    //   this.target.plan.sidocode = this.plans[0].trip_list[0].sidocode;
    //   this.target.plan.writerid = this.userinfo.id;
    //   for (let i = 0; i < this.plans.length; i++) {
    //     console.log(i);
    //     for (let j = 0; j < this.plans[i].trip_list.length; j++) {
    //       let a = {
    //         content_id: this.plans[i].trip_list[j].content_id,
    //         days: this.plans[i].day,
    //         index: j,
    //       }; //여기는 plandetail값이 들어가야한다. 
    //       this.target.planlist.push(a);
    //     }
    //   }
    //   let msg = "등록을 실패하였습니다. 로그인을 확인해주세요.";
    //   console.log(this.target);
    //   updateDetail(this.plan.plan_id, this.target, () => {
    //     console.log("등록을 성공했습니다.");
    //     this.$router.push("/plan");
    //   },
    //     (err) => {
    //       console.log("에러발생 : " + err);
    //       console.log(msg);
    //     });
    //   console.log(this.target);

    // },
    async getTitle() {
      console.log(this.planlist);
      for (let i = 0; i < this.planlist.length; i++) {
        // this.planlist.forEach((data) => {
        console.log(this.planlist[i]);
        await search_title(this.planlist[i].content_id, ({ data }) => {
          console.log("title result");
          console.log(data);
          this.planlist[i].addr1 = data.addr1;
          this.planlist[i].title = data.title;
          this.planlist[i].latitude = data.latitude;
          this.planlist[i].longitude = data.longitude;
          this.planlist[i].first_image = data.first_image;
          this.planlist[i].sido_code = data.sido_code;
          // this.planlist[i].push(data);
          console.log("data");
          console.log(this.planlist[i]);
        }, () => { console.log("에러발생"); });
      }

    },
    async getDetail() {
      await detail(this.$route.params.plan_id, ({ data, status }) => {
        if (status == HttpStatusCode.NoContent) {
          alert("삭제되었거나 존재하지 않는 글입니다.");
          this.$router.push("/plan");
        }
        this.plan = data.plan;
        this.plan.endDate = this.plan.endDate.split(" ")[0];
        this.plan.startDate = this.plan.startDate.split(" ")[0];
        this.planlist = data.planlist;
        console.log("여기가 오니?");
      });
      //이제 여기서 plannav랑 planinfo에 세부 정보를 보내주기 위한 props를 설정해준다.
      /*
      1. plannav에서 사용하던 데이터 구조는 
contentid: 가 들어가있지
end_date:"2020-02-03"
share:"0"
start_date:"2020-02-02"
title:"2323"

plans : [{
day : string,
trip_list : {
  content_id : "",
  sido_code : int,
  title : ""
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
    this.$EventBus.$on("plan-reload", () => {
      this.getDetail();
    });
    await this.getTitle();
    console.log("그럼 여기는?");
    await this.setData();//여기서 필요한 데이터 양식으로 전환한다.
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
