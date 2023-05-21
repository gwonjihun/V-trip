<template>
  <div>
    <b-form @submit="search">
      <b-form-select v-model="sidocode" :options="sido_options"></b-form-select>
      <b-form-select v-model="kind" :options="kind_options"></b-form-select>
      <b-form-input v-model="word">
      </b-form-input>
      <b-button type="submit">검색</b-button>
    </b-form>
  </div>
</template>

<script>
import { search, sido } from "@/api/tripapi.js";
export default {
  data() {
    return {
      word: "",
      kind: 0,
      kind_options: [
        { value: "0", text: '관광지 선택' },
        { value: "12", text: '관광지' },
        { value: "14", text: '문화시설' },
        { value: "15", text: '축제공연행사' },
        { value: "25", text: '여행코스' },
        { value: "28", text: '레포츠' },
        { value: "32", text: '숙박' },
        { value: "38", text: '쇼핑' },
        { value: "39", text: '음식점' },
      ],
      sidocode: 0,
      sido_options: [
        { value: 0, text: '시군구 선택' },
      ],
      trips: []
    }
  },
  created() {
    let msg = "조회을 실패했습니다.";
    sido((res) => {

      var arr = res.data.response.body.items.item;
      console.log(arr);
      arr.forEach((item) => {
        this.sido_options.push({
          "value": item.code, "text": item.name,
        });
      });
    },

      (err) => {
        alert(msg);
        console.log("에러발생 : " + err);
      });
  },
  methods: {
    search(e) {
      let msg = "조회을 실패했습니다.";
      e.preventDefault();

      var params = {
        "sido_code": this.sidocode,
        "content_type_id": this.kind,
        "title": this.word,
      };
      /* back params : int sido_code ,@RequestParam int content_type_id,@RequestParam String title */
      search(params, ({ data }) => {
        this.trips = data;
        console.log(this.trips);
        if (this.trips.length > 0) this.$emit("tripList", this.trips);
        else this.$emit("tripList", []);
      },
        (err) => {
          alert(msg);
          console.log("에러발생 : " + err);
        });
      this.reset();
    },
    reset() {
      this.word = "";
      this.kind = 0;
      this.sidocode = 0;
    }
  },
}
</script>

<style></style>