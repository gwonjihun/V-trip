<template>
  <b-table striped hover no-border-collapse :fields="fields" :items="plans" @row-clicked="clickRow">
    <template #cell(index)="data">
      {{ data.index + start }}
    </template>
  </b-table>
</template>

<script>
export default {
  name: "PlanListTable",
  props: {
    plans: [],
    start: Number,
  },
  data() {
    return {
      fields: [
        {
          key: "index",
          label: "번호",
        },
        {
          key: "title",
          label: "제목",
        },
        {
          key: "sidoname",
          label: "여행지역",
        },
        {
          key: "writerid",
          label: "작성자",
        },
        {
          key: "reads",
          label: "조회수",
        },
        {
          key: "likes",
          label: "좋아요수",
        },
        {
          key: "createat",
          label: "작성일",
        },
      ],
    };
  },
  methods: {
    async clickRow(item) {
      console.log(item.plan_id);
      // await updateReads(
      //   item.plan_id,
      //   () => {},
      //   (err) => console.log(err)
      // );
      this.$router.push({ name: `planDetail`, params: { plan_id: item.plan_id } });
    },
  },
};
</script>

<style></style>
