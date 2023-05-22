<template>
  <div id="app">
    <header-nav />
    <b-container>
      <br />
      <router-view />
    </b-container>
  </div>
</template>

<script>
import HeaderNav from "./components/common/HeaderNav.vue";
export default {
  components: { HeaderNav },
  name: "App",
};
</script>

<style>
@font-face {
  font-family: "DOSPilgiMedium";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2304-2@1.0/DOSPilgiMedium.woff2") format("woff2");
  font-weight: normal;
  font-style: normal;
}

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
  background-color: #42b983;
  min-height: 60px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>

<style scoped>
.container {
  background-color: aliceblue;
  min-height: calc(100vh - 60px);
}
</style>
