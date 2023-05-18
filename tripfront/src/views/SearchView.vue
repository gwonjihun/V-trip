<template>
  <div>
    <h2 style="padding-bottom: 5px;">지도야 나와랏!</h2>
    <search-var></search-var>
    <kakao-map></kakao-map>
    <plan-table></plan-table>
  </div>
</template>

<script>
import KakaoMap from "@/components/map/KakaoMap.vue";
export default {
  components: { KakaoMap },
  name: "SearchView",
};
</script>

<style></style>