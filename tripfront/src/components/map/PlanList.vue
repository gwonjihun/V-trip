<template>
  <div>
    {{ plan }}
    {{ plan_init }}
    {{ plans }}
    <div v-for="(trip_lists, index) in plans" :key="index">
      <h2>{{ index + 1 }}일차 여행일정</h2>
      <draggable :list="trip_lists.trip_list" group="test">
        <li v-for="(item, tindex) in plans[index].trip_list" :key="tindex">
          {{ item }}
          <b-button type="button" @click="removeElement(tindex, index)">삭제</b-button>
        </li>
      </draggable>
    </div>
    <div v-if="isplan">
      <b-button type="button" @click="planregist">등록</b-button>
    </div>
  </div>
</template>

<script>
import draggable from "vuedraggable";
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
      target: {},
    };
  },
  methods: {
    planregist: function () {
      for (let day of this.plans) {
        console.log(day);

      }
    },
    removeElement: function (tindex, index) {
      this.$delete(this.plans[index].trip_list, tindex);
    },
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
        this.plans = [];
        for (let i = 0; i < this.day; i++) {
          this.plans.push({
            day: i + 1,
            trip_list: [],
          });
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

<style></style>