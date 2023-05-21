<template>
  <div>
    {{ plan }}
    {{ plan_init }}
    {{ plans }}
    <div v-for="(trip_lists, index) in plans" :key="index">
      <div>{{ trip_lists.day }}</div>
      <div>{{ index }}</div>
      <h2>여행일정</h2>
      <div v-for="(item, tindex) in trip_lists.trip_list" :key="tindex">
        {{ item }}
        <td>생성</td>
      </div>
    </div>
    <!-- 이렇게 쓰거나 -->

    <div></div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      plans: [],
      day: Number,
      idx: Number,
      target: {},
    
    };

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
      hand() {
        
        var start_arr = this.plan_init.start_date.split("-");
        console.log(typeof this.plan_init.start_date);
        console.log(this.plan_init.start_date);

        var end_arr = this.plan_init.end_date.split("-");
        console.log(end_arr);
        console.log(start_arr);
        // var start = new Date(start_arr[0], start_arr[1], start_arr[2]);
        // var end = new Date(end_arr[0], end_arr[1], end_arr[2]);
        // var btMs = end.getTime() - start.getTime();
        // // this.btDay = btMs / (1000 * 60 * 60 * 24) + 1;
        // console.log(btMs);
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

<style>
</style>