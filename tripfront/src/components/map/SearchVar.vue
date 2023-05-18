<template>
  <div>
    <b-form @submit="search">
      <b-form-select v-model="sidocode" :options="sido_options"></b-form-select>
      <b-form-select v-model="kind" :options="kind_options"></b-form-select>
      <b-form-input v-model = "word">
      </b-form-input>
      <b-button type="submit">제출</b-button>
    </b-form>
  </div>
</template>

<script>
import {search} from "@/api/tripapi.js";
export default {
  data() {
      return {
        word : "",
        kind : 0,
        kind_options:[{value: 0, text: '관광지 선택'}],
        sidocode: 0,
        sido_options: [
          { value: 0, text: '시군구 선택' },

        ]
      }
    },
    methods: {
    search(e){
      let msg = "수정을 실패했습니다.";
      e.preventDefault();

      var params = {
        "sido_code" : this.sidocode,
        "content_type_id" : this.kind,
        "title" : this.word,
      };
      /* back params : int sido_code ,@RequestParam int content_type_id,@RequestParam String title */
      search(params,(res) => {
          console.log(res);
          console.log(msg);
        },
        (err) => {
          console.log("에러발생 : " + err);
        });
    }
    }
}
</script>

<style></style>