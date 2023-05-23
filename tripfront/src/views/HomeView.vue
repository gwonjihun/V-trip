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
    <b-jumbotron class="mb-0 title">
      <template #header>V-Trip</template>
      <template #lead>
        {{ message }}
      </template>
    </b-jumbotron>
    <b-container class="main pt-5">
      <h3>최근 떠오르는 지역들</h3>
      <div class="img-space">
        <!-- 여기서 사진들을 불러와서 넣어준다. -->
        <b-container class="w-50 p-1" v-for="place in hotplaces" v-bind:key="place.content_id">
          <b-img rounded :src="place.first_image" />
        </b-container>
      </div>
    </b-container>
  </div>
</template>

<script>
import { hotplace } from "@/api/tripapi.js";
export default {
  data() {
    return {
      message: "당신의 즐거운 여행을 위한 사이트",
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
  background-image: url("@/assets/summer.jpg");
  color: rgb(0, 0, 0);
  border-radius: 0;
}
.container :hover {
  opacity: 0.5;
}
.container .jumbotron :hover {
  color: blue;
}

h1 {
  font-family: DOSPilgiMedium;
}
</style>
