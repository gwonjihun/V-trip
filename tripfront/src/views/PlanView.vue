<template>
  <b-container class="main">
    <h2>여행계획 게시판</h2>
    <router-view
      :plan-pgno="pgno"
      :search-key="key"
      :search-word="word"
      :search-sidocode="sidocode"
      @change-option="handleOption"
    />
  </b-container>
</template>

<script>
export default {
  name: "PlanView",
  data() {
    return {
      pgno: 1,
      key: "title",
      word: "",
      sidocode: 0,
    };
  },
  methods: {
    handleOption(option) {
      this.pgno = option.pgno;
      this.key = option.key;
      this.word = option.word;
      this.sidocode = option.sidocode;
    },
  },
};
</script>

<style></style>
