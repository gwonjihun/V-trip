<template>
  <b-container class="main">
    <h2 style="padding-bottom: 5px">여행 계획</h2>
    <!-- <b-container class="mt-5"> -->
    <b-row>
      <b-col>
        <kakao-map :trips="tripList" @plan="handleplan">
          <search-var @tripList="handletriplist"></search-var>
        </kakao-map>
      </b-col>
      <b-col lg="4" class="mt-3 mt-lg-0">
        <plan-table @planinit="handleplaninit"></plan-table>
        <plan-list v-show="iscreate" :plan_init="plan_info" :plan="trip"></plan-list>
      </b-col>
    </b-row>
    <!-- </b-container> -->
  </b-container>
</template>

<script>
import KakaoMap from "@/components/map/KakaoMap.vue";
import SearchVar from "@/components/map/SearchVar.vue";
import PlanTable from "@/components/map/PlanTable.vue";
import PlanList from "@/components/map/PlanList.vue";
export default {
  components: { KakaoMap, PlanTable, SearchVar, PlanList },
  name: "PlanView",
  data() {
    return {
      iscreate: false,
      tripList: [],
      trip: {},
      plan_info: {
        title: "",
        start_date: "",
        end_date: "",
      },
    };
  },
  methods: {
    handletriplist(trips) {
      const temp = trips;
      this.tripList = temp;
    },
    handleplan(plan) {
      if (this.iscreate) {
        const temp = { ...plan };
        this.trip = temp;
      } else {
        alert("여행 일정을 생성해 주세요.");
      }
    },
    handleplaninit(plan_init) {
      const temp = plan_init;
      this.plan_info = temp;
      this.iscreate = true;
    },
  },
};
</script>

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
