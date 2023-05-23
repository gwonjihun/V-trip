<template>
  <b-container class="main">
    <h2>자유게시판</h2>
    <router-view :board-pgno="pgno" :search-key="key" :search-word="word" @change-option="handleOption" />
  </b-container>
</template>

<script>
export default {
  name: "BoardView",
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
