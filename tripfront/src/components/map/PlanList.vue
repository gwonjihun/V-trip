<template>
  <div>
    <!-- {{ plan }} -->
    <!-- {{ plan_init }} -->
    <div v-for="(trip_lists, index) in plans" :key="index">
      <h4>{{ index + 1 }}일차 여행일정</h4>
      <b-list-group>
        <draggable :list="trip_lists.trip_list" group="test">
          <b-list-group-item v-for="(item, tindex) in plans[index].trip_list" :key="tindex">
            {{ item.title }}
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
      plans: [],
      day: Number,
      idx: Number,
      target: {
        plan: {},
        planlist: [],
      },
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
    plan: {
      title: "",
      content_id: "",
      addr: "",
    },
    plan_init: {
      title: "",
      start_date: "",
      end_date: "",
      share: "0",
    },
  },

  watch: {
    plan_init: {
      handler() {
        var start_arr = this.plan_init.start_date.split("-");
        console.log(typeof this.plan_init.start_date);
        console.log(this.plan_init.start_date);

        var end_arr = this.plan_init.end_date.split("-");
        console.log(end_arr);
        console.log(start_arr);
        var start = new Date(start_arr[0], start_arr[1], start_arr[2]);
        var end = new Date(end_arr[0], end_arr[1], end_arr[2]);
        var btMs = end.getTime() - start.getTime();
        this.day = btMs / (1000 * 60 * 60 * 24) + 1;
        console.log(this.btDay);
        if (this.plans.length > 0) {
          console.log(1);
          //여기는 기존 일정이 있는경우
          if (this.plans.length < this.day) {
            for (let i = this.plans.length + 1; i <= this.day; i++) {
              this.plans.push({
                day: i,
                trip_list: [],
              });
            }
          } else {
            this.plans.splice(this.day, this.plans.length - this.day);
          }
        } else {
          for (let i = 1; i <= this.day; i++) {
            this.plans.push({
              day: i,
              trip_list: [],
            });
          }
        }
        this.isplan = true;
      },
      deep: true,
    },

    plan: {
      handler() {
        console.log("plan이 들어왔다");
        if (this.plans.length < 1) {
          const plan = {
            day: 1,
            trip_list: [{ ...this.plan }],
          };
          this.plans.push(plan);
        } else {
          const plan = { ...this.plan };
          this.plans[0].trip_list.push(plan);
        }
        console.log("!!!");
      },
      deep: true,
    },
  },
};
</script>

<style scoped>
.list-group {
  min-height: 1rem;
  border: 1px solid;
}
</style>
