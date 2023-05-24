<template>
  <div>
    <b-form @submit="(e) => { e.preventDefault(); }">
      <b-row>
        <b-col>
      <b-form-input type="date" v-model="start_date" :readonly="!ismodify" />
        </b-col>
      <b-col>
      <b-form-input type="date" v-model="end_date" :readonly="!ismodify" />
      </b-col>
      </b-row>
      <b-form-input class="mt-2" type="text" v-model="title" placeholder="여행 제목을 입력해주세요" :readonly="!ismodify" />

    </b-form>
    <!-- 
    <draggable>
      <transition-group>
        <div v-for="(todoItem, index) in plan_list" v-bind:key="index">
          {{ todoItem.item }}
        </div>
      </transition-group>
    </draggable> -->
  </div>
</template>

<script>
import { toStringByFormatting } from "@/util/utils";

const dayMiliSec = 1000 * 60 * 60 * 24;

export default {
  data() {
    return {
      plan_init: {
        plan_id: "",
        title: "",
        start_date: "",
        end_date: "",
        share: "0",
      },
      title: "",
      plan_days: 1,
      start_date: toStringByFormatting(new Date()),
      end_date: "",
      //아래 데이터는 여행 경로 데이터 구조
    };
  },
  props: {
    plan: {},
    ismodify: { type: Boolean, default: false },
  },
  computed: {
    start_date_obj() {
      return new Date(this.start_date);
    },
    end_date_obj() {
      return new Date(this.end_date);
    },
  },
  watch: {
    plan() {
      this.start_date = this.plan.startDate;
      this.end_date = this.plan.endDate;
      this.title = this.plan.title;
    },
    start_date() {
      this.end_date = toStringByFormatting(new Date(this.start_date_obj.getTime() + this.plan_days * dayMiliSec));
    },
    end_date(newVal, oldVal) {
      let days = (this.end_date_obj.getTime() - this.start_date_obj.getTime()) / dayMiliSec;
      console.log(days);
      if (days == this.plan_days) return;
      let minus = days < 0;
      days = Math.abs(days);

      const msg = `계획 날짜가 줄어들었습니다.
        여행계획이 손실될 수 있습니다.
        진행하시겠습니까?`;
      if (days < this.plan_days && !confirm(msg)) {
        console.log(newVal, oldVal);
        this.end_date = oldVal;
        console.log(this.end_date);
      } else {
        console.log("change");
        this.plan_days = days;
        if (minus) this.start_date = this.end_date;
      }
    },
    plan_days() {
      this.createplan();
    },
    title() {
      this.createplan();
    },
  },
  methods: {
    handleSubmit(event) {
      event.preventDefault();
      this.createplan();
    },
    createplan() {
      // createplan(event) {
      // event.preventDefault();

      this.plan_init.title = this.title;
      this.plan_init.start_date = this.start_date;
      this.plan_init.end_date = this.end_date;
      /*
      1. 2개의 date변수 값을 먼저확인해준다 만약 1개가 없으면 
      */
      // if (this.plan_init.title === "") {
      //   alert("여행 제목을 입력해주세요!");
      //   return;
      // }
      if (this.plan_init.start_date == "" && this.plan_init.end_date == "") {
        alert("여행 일정을 입력해주세요!");
        return;
      }
      console.log();
      if (this.plan_init.start_date == "") {
        this.plan_init.start_date = this.plan_init.end_date;
        this.plan_init.end_date = this.plan_init.start_date;
      }
      if (this.plan_init.end_date == "") {
        this.plan_init.end_date = this.plan_init.start_date;
      }
      if (this.plan_init.end_date < this.plan_init.start_date) {
        var temp = this.plan_init.end_date;
        this.plan_init.end_date = this.plan_init.start_date;
        this.plan_init.start_date = temp;
      }
      this.$emit("planinit", this.plan_init);
      this.start_date = this.plan_init.start_date;
      this.end_date = this.plan_init.end_date;
    },
  },
  created() {
    this.end_date = toStringByFormatting(new Date(Date.now() + dayMiliSec * this.plan_days));
    this.createplan();
  },
};
</script>

<style></style>