<template>
  <div>
    <h2>여행계획 게시판</h2>
    <router-view :board-pgno="pgno" :search-key="key" :search-word="word" @change-option="handleOption" />
  </div>
</template>

<script>
export default {
  name: "PlanView",
  data() {
    return {
      pgno: 1,
      key: "none",
      word: "",
    };
  },
  methods: {
    handleOption(option) {
      this.pgno = option.pgno;
      this.key = option.key;
      this.word = option.word;
    },
  },
};
</script>

<style></style>
