<template>
  <b-table striped hover no-border-collapse :fields="fields" :items="plans" @row-clicked="clickRow">
    <template #cell(index)="data">
      {{ data.index + start }}
    </template>
    <template #cell(writerid)="data">
      <span class="link" @click="moveUserPage(data.item.writerid)">
        {{ data.item.writerid }}
      </span>
    </template>
    <template #cell(createat)="data">
      {{ data.item.createat | dateFilter }}
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
          class: "tw10",
        },
        {
          key: "title",
          label: "제목",
        },
        {
          key: "sidoname",
          label: "여행지역",
          class: "tw10",
        },
        {
          key: "writerid",
          label: "작성자",
          class: "tw10",
        },
        {
          key: "reads",
          label: "조회수",
          class: "tw10",
        },
        {
          key: "likes",
          label: "좋아요수",
          class: "tw10",
        },
        {
          key: "createat",
          label: "작성일",
          class: "tw15",
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
    moveUserPage(user_id) {
      this.$router.push({ name: "userPage", params: { id: user_id } }).catch(() => {});
    },
  },
};
</script>

<style></style>
