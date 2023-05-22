<template>
  <b-table striped hover no-border-collapse :fields="fields" :items="plans" @row-clicked="clickRow">
    <template #cell(index)="data">
      {{ data.index + start }}
    </template>
  </b-table>
</template>

<script>
import { updateReads } from "@/api/boardapi";

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
      console.log(item.content_id);
      await updateReads(
        item.content_id,
        () => {},
        (err) => console.log(err)
      );
      console.log(this);
      // this.$router.push({ name: `$planDetail`, params: { writerid: item.content_id } });
    },
  },
};
</script>

<style></style>
