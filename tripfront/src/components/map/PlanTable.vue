<template>
  <div>
    <b-form @submit="createplan">
      <b-form-input type="date" v-model="start_date" />
      <b-form-input type="date" v-model="end_date" />
      <b-form-input type="text" v-model="title" placeholder="여행 제목을 입력해주세요" />
      <b-button type="submit">생성</b-button>
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
// import draggable from "vuedraggable";

import { toStringByFormatting } from "@/util/utils";

const dayMiliSec = 1000 * 60 * 60 * 24;

export default {
  data() {
    return {
      plan_init: {
        title: "1",
        start_date: "2020-02-02",
        end_date: "2020-02-03",
        share: "0",
      },
      title: "1",
      start_date: toStringByFormatting(new Date()),
      end_date: toStringByFormatting(new Date(Date.now() + dayMiliSec * 1)),
      //아래 데이터는 여행 경로 데이터 구조
    };
  },
  methods: {
    createplan(event) {
      event.preventDefault();

      this.plan_init.title = this.title;
      this.plan_init.start_date = this.start_date;
      this.plan_init.end_date = this.end_date;
      /*
      1. 2개의 date변수 값을 먼저확인해준다 만약 1개가 없으면 
      */
      if (this.plan_init.title === "") {
        alert("여행 제목을 입력해주세요!");
        return;
      }
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
};
</script>

<style></style>
