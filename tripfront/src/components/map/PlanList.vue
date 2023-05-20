<template>
  <div>
    {{plan}}
  </div>
</template>

<script>
export default {
  props: {
    plan: [
    //   {
    //     day: String,
    //     trip_list: [],
    //   },
    ],
    plan_init:{
      title : "",
      start_date: "",
      end_date: "",
      },
  },
};
</script>

<style>
</style>