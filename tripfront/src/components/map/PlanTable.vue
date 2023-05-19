<template>
  <div>
    <b-form-datepicker v-model="start_date" @context="dateset"></b-form-datepicker>
    <b-form-datepicker v-model="end_date" @context="dateset"></b-form-datepicker>

    <draggable>

      <transition-group>
        <div v-for="(todoItem, index) in plan_list" v-bind:key="index">
          {{ todoItem.item }}
        </div>
      </transition-group>
    </draggable>

  </div>
</template>

<script>
import draggable from 'vuedraggable';

export default {
  data() {
    return {
      start_date: "",
      end_date: "",
      plan_list: [{
        day: Number,
        plan_list: [{}]
      }]
    }
  },
  props: {
    plan: {},
  },
  components: {
    draggable
  },
  watch: {
    plan: {
      handler() {
        console.log("시계는 와치");
        console.log(this.plan);
      },
      deep: true,
    }
  },
  methods: {
    dateset() {
      console.log("!@#");
      if (this.start_date === null || this.end_date === null) {
        console.log("1");
        return;
      }
      if (this.end_date < this.start_date) {
        console.log("2");
        // var copiedDate = new Date(this.end_date.toString);
        // var temp = new Date(this.start_date.toString);

        console.log(this.end_date);
        console.log(this.start_date);
        return;
      }
      this.createPlan();
      /* 
      1. 데이트의 크기를 먼저 비교한다.
      */
    },
    createPlan() {

    },
  },
}
</script>

<style></style>