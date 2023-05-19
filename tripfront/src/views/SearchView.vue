<template>
  <div>
    <h2 style="padding-bottom: 5px;">지도야 나와랏!</h2>

    <search-var class=".custom_typecontrol" @tripList="handletriplist"></search-var>
    <kakao-map style="width:50%;height:50%;overflow:;" :trips="tripList" @plan="handleplan"></kakao-map>
    <plan-table :plan="plan"></plan-table>
  </div>
</template>

<script>
import KakaoMap from "@/components/map/KakaoMap.vue";
import SearchVar from "@/components/map/SearchVar.vue";
import PlanTable from "@/components/map/PlanTable.vue";
export default {
  components: { KakaoMap, PlanTable, SearchVar },
  name: "SearchView",
  data() {
    return {
      tripList: [{}],
      plan: {},
    }
  },
  methods: {
    handletriplist(trips) {
      this.tripList = trips;
    },
    handleplan(plan) {
      this.plan = plan;
    }
  }

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

.custom_typecontrol {
  position: absolute;
  top: 10px;
  left: 10px;
  overflow: hidden;
  width: 130px;
  height: 30px;
  margin: 0;
  padding: 0;
  z-index: 1;
  font-size: 12px;
  font-family: 'Malgun Gothic', '맑은 고딕', sans-serif;
}

.custom_typecontrol span {
  display: block;
  width: 65px;
  height: 30px;
  float: left;
  text-align: center;
  line-height: 30px;
  cursor: pointer;
}
</style>