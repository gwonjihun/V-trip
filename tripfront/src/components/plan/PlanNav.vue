<template>
  <div>
    <b-form @submit="createplan">
      <b-form-input type="date" v-model="start_date" />
      <b-form-input type="date" v-model="end_date" />
      <b-form-input type="text" v-model="title" placeholder="여행 제목을 입력해주세요" />
      <b-button type="submit">일정 변경</b-button>

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

export default {
  data() {
    return {
      plan_init: {
        title: "",
        start_date: "",
        end_date: "",
        share: "0"
      },
      title: "1",
      start_date: "2020-02-02",
      end_date: "2020-02-03",
      //아래 데이터는 여행 경로 데이터 구조

    }
  },
  props: {
    plan: {
      createat: "2023-05-23 11:36:12",
      deleteat: null,
      endDate: "2020-02-03 00:00:00",
      likes: 0,
      plan_id: "16",
      reads: 6,
      share: 0,
      sidocode: "37",
      sidoname: "전라북도",
      startDate: "2020-02-02 00:00:00",
      title: "test1",
      updateat: null,
      writerid: "ssafy",


    }
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