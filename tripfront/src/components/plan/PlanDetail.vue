<template>
  <b-container>
    <b-row>
      <b-col cols="8" align-self="center">
        <h4 class="float-left">{{ plan.title }}</h4>
      </b-col>
      <b-col cols="4">
        <p class="mb-1">작성자: {{ plan.writerid }}</p>
        <p class="mb-1">작성일: {{ plan.createat }}</p>
        <p class="mb-1" v-if="plan.updateat">수정일: {{ plan.updateat }}</p>
      </b-col>
    </b-row>
    <hr />
    <!-- content -->
    <b-row>
      <b-col>
        <b-table :items="planlist" />
      </b-col>
    </b-row>
    <hr />
    <!-- under -->
    <b-row>
      <b-col>
        <div class="float-left">
          <span>조회수: {{ plan.reads }}</span>
          <span>좋아요수: {{ plan.like_num }}</span>
        </div>
      </b-col>
      <b-col>
        <div class="float-right">
          <b-button v-if="userinfo.id == plan.writerid" @click="moveModify">수정하기</b-button>
          <b-button @click="moveList">글목록</b-button>
        </div>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { HttpStatusCode } from "axios";
import { mapState } from "vuex";
import { detail } from "@/api/planapi";

export default {
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
