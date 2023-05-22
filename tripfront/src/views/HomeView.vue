<template>
  <!-- <div class="container">
    <br />
    <div class="text-center">
      <h1>{{ message }}</h1>
    </div>
    <br />
    <user-search></user-search>
  </div> -->
  <div>
    <br />
    <b-jumbotron>
      <template #header>SSAFY USER</template>
      <template #lead>
        {{ message }}
      </template>
    </b-jumbotron>
    <br />
    <div>
      <div>
        <h3>최근 떠오르는 지역들</h3>
      </div>
      <div class="img-space">
        <!-- 여기서 사진들을 불러와서 넣어준다. -->
        <b-container class="w-50 p-1" v-for="place in hotplaces" v-bind:key="place.content_id">
          <b-img rounded :src="place.first_image" />
        </b-container>
      </div>
    </div>
  </div>
</template>

<script>
import { hotplace } from "@/api/tripapi.js";
export default {
  data() {
    return {
      message: "사용자 정보 사이트에 오신것을 환영합니다.",
      hotplaces: [],
    };
  },
  created() {
    let msg = "조회을 실패했습니다.";
    hotplace(
      (res) => {
        this.hotplaces = res.data;
      },
      (err) => {
        alert(msg);
        console.log("에러발생 : " + err);
      }
    );
  },
};
</script>
<style scoped>
.text-center {
  text-align: center;
}

.img-space {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  align-items: center;
  flex-direction: row;
  align-content: flex-start;
}
img {
  max-width: 100%;
}

.jumbotron {
  background-image: url("@/assets/main.jpg");
  color: red;
}
.container :hover {
  opacity: 0.5;
}
.container .jumbotron :hover {
  color: blue;
}
</style>
