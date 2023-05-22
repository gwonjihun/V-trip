<template>
  <div>
    <h2>공지사항</h2>
    <router-view
      :board-pgno="pgno"
      :search-key="key"
      :search-word="word"
      @change-option="handleOption"
      :notice="true"
    />
  </div>
</template>

<script>
export default {
  name: "NoticeView",
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
