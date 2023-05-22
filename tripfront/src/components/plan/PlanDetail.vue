<template>
  <b-container>
    <search-var v-if="ismodify"></search-var>
    <kakao-map></kakao-map>
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
export default {
  components : {KakaoMap, SearchVar,UserSearch}, 
  name: "PlanDetail",
  data() {
    return {
      plan: {
        plan_id: 0,
        title: "",
        writerid: "",
        reads: 0,
        createat: "",
        updateat: "",
        deleteat: "",

        comment_num: 0,
        like_num: 0,

        nickname: "",
      },
      planlist: [],
      ismodify : false, //수정 권한 여부 
    };
  },
  computed: {
    ...mapState("userStore", ["userinfo"]),
  },
  methods: {
    getDetail() {
      detail(this.$route.params.plan_id, ({ data, status }) => {
        if (status == HttpStatusCode.NoContent) {
          alert("삭제되었거나 존재하지 않는 글입니다.");
          this.$router.push("/plan");
        }
        this.plan = data.plan;
        this.planlist = data.planlist;
      });
    },
    moveModify() {
      this.$router.push({ name: "planModify", params: { plan_id: this.plan.plan_id } });
    },
    moveList() {
      this.$router.push({ name: "planList" });
    },
  },
  created() {
    this.getDetail();
    this.$EventBus.$on("plan-reload", () => {
      this.getDetail();
    });
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
