<template>
  <div>
    <h2>공지사항</h2>
    <router-view :notice="true" />
  </div>
</template>

<script>
export default {
  name: "NoticeView",
};
</script>

<style></style>
