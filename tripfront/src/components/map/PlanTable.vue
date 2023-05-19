<template>
  <div>
    <b-form @change="dateset">
      <b-form-datepicker v-model="start_date"></b-form-datepicker>
      <b-form-datepicker v-model="end_date"></b-form-datepicker>
    </b-form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      start_date: Date,
      end_date: Date,

    }
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