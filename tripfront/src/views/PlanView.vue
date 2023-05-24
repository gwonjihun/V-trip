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

<style>
html,
body {
  width: 100%;
  height: 100%;
  margin: 0;
  padding: 0;
}

.map_wrap {
  position: relative;
  overflow: hidden;
  width: 100%;
  height: 350px;
}

.radius_border {
  border: 1px solid #919191;
  border-radius: 5px;
}
/*
.custom_typecontrol {
  position: absolute;
  top: 10px;
  left: 10px;
  overflow: hidden;
  width: 200px;
  height: 30px;
  margin: 0;
  padding: 0;
  z-index: 2;
  font-size: 12px;
  font-family: "Malgun Gothic", "맑은 고딕", sans-serif;
}

.custom_typecontrol span {
  display: block;
  width: 65px;
  height: 30px;
  float: left;
  text-align: center;
  line-height: 30px;
  cursor: pointer;
} */
</style>

<style scoped>
.main {
  /* margin-top: 0;
  padding-top: 2rem;
  background-color: #e4feff;
  min-height: calc(100vh - 4.5rem); */
  max-width: 100vw;
}
/* .contaier {
  width: 100%;
} */
</style>
