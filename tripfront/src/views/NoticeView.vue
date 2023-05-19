<template>
  <div>
    <h3>공지사항</h3>
    <router-view :notice="true" />
  </div>
</template>

<script>
export default {
  name: "NoticeView",
};
</script>

<style></style>
