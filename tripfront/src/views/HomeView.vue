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
      <b-carousel
        v-model="slide"
        :interval="4000"
        controls
        indicators
        background="#ababab"
        img-width="100"
        img-height="100"
      >
        <b-carousel-slide v-for="place in hotplaces" v-bind:key="place.content_id">
          <template #img>
            <b-container class="hot-place" :id="'hotplace-' + place.content_id">
              <b-img rounded :src="place.first_image" />
            </b-container>
          </template>
          <template>
            <h2>{{ place.title }}</h2>
            <p>{{ place.addr1 }}</p>
            <b-popover :target="'hotplace-' + place.content_id" triggers="hover">
              <template #title>{{ place.title }}</template>
              {{ place.overview }}
            </b-popover>
          </template>
        </b-carousel-slide>
      </b-carousel>
      <!-- <div class="img-space"> -->
      <!-- 여기서 사진들을 불러와서 넣어준다. -->
      <!-- <b-container class="hot-place w-50 p-1" v-for="place in hotplaces" v-bind:key="place.content_id"> -->
      <!-- <b-img rounded :src="place.first_image" /> -->
      <!-- </b-container> -->
      <!-- </div> -->
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
      slide: 0,
    };
  },
  created() {
    let msg = "조회을 실패했습니다.";
    hotplace(
      (res) => {
        this.hotplaces = res.data;
        this.slide = this.hotplaces.length;
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
.container.main {
  min-height: calc(100vh - 4.5rem - 270px);
}

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
  max-height: 50vh;
}

.jumbotron {
  height: 270px;
  background-image: url("@/assets/summer.jpg");
  color: rgb(0, 0, 0);
  border-radius: 0;
}
.hot-place :hover {
  opacity: 0.5;
}
.hot-place .jumbotron :hover {
  color: blue;
}

h1 {
  font-family: DOSPilgiMedium;
}
</style>
