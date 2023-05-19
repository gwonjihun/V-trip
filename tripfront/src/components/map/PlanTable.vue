<template>
  <div>
    <b-form @change="dateset">
      <b-form-datepicker v-model="start_date"></b-form-datepicker>
      <b-form-datepicker v-model="end_date"></b-form-datepicker>
    </b-form>
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
      start_date: Date,
      end_date: Date,
    }
  },
  props: {
    plan_list: [{
      day: Number,
      plan_list: [{}]
    }]
  },
  components: {
    draggable
  },
  methods: {
    dateset() {

      /* 
      1. 데이트의 크기를 먼저 비교한다.
      */
    }
  },
}
</script>

<style></style>