<template>
  <b-table striped hover no-border-collapse :fields="fields" :items="boards" @row-clicked="clickRow">
    <template #cell(index)="data">
      {{ data.index + start }}
    </template>
    <template #cell(writer)="data">
      <span class="link" @click="moveUserPage(data.item.writername)">
        {{ data.item.writername + "(" + data.item.nickname + ")" }}
      </span>
    </template>
    <template #cell(createat)="data">
      {{ data.item.createat | dateFilter }}
    </template>
  </b-table>
</template>

<script>
import { updateReads } from "@/api/boardapi";

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
          class: "tw10",
        },
        {
          key: "title",
          label: "제목",
        },
        {
          key: "writer",
          label: "작성자",
          class: "tw15",
        },
        {
          key: "reads",
          label: "조회수",
          class: "tw10",
        },
        {
          key: "comment_num",
          label: "댓글수",
          class: "tw10",
        },
        {
          key: "like_num",
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
      console.log(item.content_id);
      let route = this.notice ? "notice" : "board";
      await updateReads(
        item.content_id,
        () => {},
        (err) => console.log(err)
      );
      this.$router.push({ name: `${route}Detail`, params: { content_id: item.content_id } });
    },
    moveUserPage(user_id) {
      this.$router.push({ name: "userPage", params: { id: user_id } }).catch(() => {});
    },
  },
};
</script>

<style scoped></style>

<style>
span.link:hover {
  color: blue;
  cursor: pointer;
}
.tw05 {
  width: 5%;
}
.tw10 {
  width: 10%;
}
.tw15 {
  width: 15%;
}
.tw20 {
  width: 20%;
}
.tw25 {
  width: 25%;
}
.tw30 {
  width: 30%;
}
</style>
