<template>
  <b-tabs>
    <b-tab title="게시판">
      <board-list :board-pgno="b_pgno" :search-key="b_key" :search-word="user_id" @change-option="b_handleOption" />
    </b-tab>
    <b-tab title="여행계획">
      <plan-list
        :plan-pgno="p_pgno"
        :search-key="p_key"
        :search-word="user_id"
        :search-sidocode="sidocode"
        @change-option="p_handleOption"
      />
    </b-tab>
  </b-tabs>
</template>

<script>
import BoardList from "../board/BoardList.vue";
import PlanList from "../plan/PlanList.vue";

export default {
  name: "UserContents",
  components: { BoardList, PlanList },
  props: {
    user_id: String,
  },
  data() {
    return {
      b_pgno: 1,
      b_key: "writername",
      b_word: "",

      p_pgno: 1,
      p_key: "writerid",
      p_word: "",
      sidocode: 0,
    };
  },
  methods: {
    b_handleOption(option) {
      this.b_pgno = option.pgno;
      this.b_key = option.key;
      this.b_word = option.word;
    },
    p_handleOption(option) {
      this.p_pgno = option.pgno;
      this.p_key = option.key;
      this.p_word = option.word;
      this.sidocode = option.sidocode;
    },
  },
};
</script>

<style></style>
