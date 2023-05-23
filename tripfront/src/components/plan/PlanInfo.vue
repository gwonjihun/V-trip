<template>
  <div>
    <div v-for="(trip_lists, index) in init_list" :key="index">
      <h2>{{ index + 1 }}일차 여행일정</h2>
      <b-list-group>
        <draggable :list="trip_lists.trip_list" group="test">
          <b-list-group-item v-for="(item, tindex) in init_list[index].trip_list" :key="tindex">
            {{ item }}
            <b-button type="button" @click="removeElement(tindex, index)">삭제</b-button>
          </b-list-group-item>
        </draggable>
      </b-list-group>
    </div>
    <div v-if="isplan">
      <b-button type="button" @click="planregist">등록</b-button>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import draggable from "vuedraggable";
import { regist } from "@/api/planapi.js";
export default {
  components: {
    draggable,
  },
  data() {
    return {
      isplan: false,
      plans: [], // -> 여기다가 ㅣㄱ존 list 자료구조를 만들어 준다.
      day: Number,
      idx: Number,
      target: {
        plan: {},
        planlist: [],
      },
      ismodi: Boolean,
    };
  },
  methods: {
    planregist: function () {
      if (!this.isLogin) {
        alert("로그인 후에 이용해 주세요");
        return;
      }
      const pl = {
        endDate: this.plan_init.end_date,
        startDate: this.plan_init.start_date,
        title: this.plan_init.title,
        share: this.plan_init.share,
      };
      this.target = { plan: pl, planlist: [] };
      console.log(this.target.plan);
      console.log(typeof this.target.plan);
      this.target.plan.sidocode = this.plans[0].trip_list[0].sidocode;
      this.target.plan.writerid = this.userinfo.id;
      for (let i = 0; i < this.plans.length; i++) {
        console.log(i);
        for (let j = 0; j < this.plans[i].trip_list.length; j++) {
          let a = {
            content_id: this.plans[i].trip_list[j].content_id,
            days: this.plans[i].day,
            index: j,
          }; //여기는 plandetail값이 들어가야한다.
          this.target.planlist.push(a);
        }
      }
      let msg = "등록을 실패하였습니다. 로그인을 확인해주세요.";
      console.log(this.target);
      regist(
        this.target,
        () => {
          console.log("등록을 성공했습니다.");
          this.$router.push("/plan");
        },
        (err) => {
          console.log("에러발생 : " + err);
          console.log(msg);
        }
      );
      console.log(this.target);
    },
    removeElement: function (tindex, index) {
      this.$delete(this.plans[index].trip_list, tindex);
    },
  },
  computed: {
    ...mapState("userStore", ["isLogin", "userinfo"]),
  },
  props: {
    //이것은 plan info table용
    init_list: {
      plan: {},
      trip_list: [],
    },
    plan_init: {
      title: "",
      start_date: "",
      end_date: "",
      share: "0",
    },

    ismodify: { type: Boolean, require: true, default: false }
  },

  watch: {
    ismodify() {
      this.ismodi = this.ismodify;
    },
    plan_init() {


    },

    plan() {

    },
  },
};
</script>
<style></style>
