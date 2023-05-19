<template>
  <b-table striped hover no-border-collapse :fields="fields" :items="boards" @row-clicked="clickRow">
    <template #cell(index)="data">
      {{ data.index + start }}
    </template>
  </b-table>
</template>

<script>
import { updateReads } from '@/api/boardapi';

export default {
  name: "BoardListTable",
  props: {
    boards: [],
    start: Number,
    notice: { type: Boolean, require: true, default: false },
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
          key: "nickname",
          label: "작성자",
        },
        {
          key: "reads",
          label: "조회수",
        },
        {
          key: "comment_num",
          label: "댓글수",
        },
        {
          key: "like_num",
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
      let route = this.notice ? "notice" : "board";
      await updateReads(
        item.content_id,
        () => {},
        (err) => console.log(err)
      );
      this.$router.push({ name: `${route}Detail`, params: { content_id: item.content_id } });
    },
  },
};
</script>

<style></style>
