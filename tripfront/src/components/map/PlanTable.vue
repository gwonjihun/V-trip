<template>
  <div>
     <b-form @submit="createplan">
    <b-form-input type="date" v-model="plan_init.start_date"/> 
    <b-form-input type="date" v-model="plan_init.end_date"/> 
            <b-form-input type="text" v-model="plan_init.title" placeholder="여행 제목을 입력해주세요"/>
      <b-button type="submit">검색</b-button>
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
      plan_init:{
      title : "",
      start_date: "",
      end_date: "",
      },
      //아래 데이터는 여행 경로 데이터 구조

    }
  },
  methods: {
    createplan(event) {
      event.preventDefault();
      /*
      1. 2개의 date변수 값을 먼저확인해준다 만약 1개가 없으면 
      */
     if(this.plan_init.title===""){
      alert("여행 제목을 입력해주세요!");
      return;
     }
     if(this.plan_init.start_date=="" && this.plan_init.end_date=="" ){
      alert("여행 일정을 입력해주세요!");
      return;
     }
     console.log();
    if(this.plan_init.start_date=="" ){
      this.plan_init.start_date = this.plan_init.end_date;
      this.plan_init.end_date=this.plan_init.start_date;
     }
    if(this.plan_init.end_date=="" ){
      this.plan_init.end_date=this.plan_init.start_date;
     }
     if(this.plan_init.end_date<this.plan_init.start_date){
      var temp =this.plan_init.end_date;
      this.plan_init.end_date = this.plan_init.start_date;
      this.plan_init.start_date = temp;
     }

     this.$emit("planinit",this.plan_init);

    },
  },
};
</script>

<style></style>