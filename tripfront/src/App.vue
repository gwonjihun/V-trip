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
@font-face {
  font-family: "Dovemayo_gothic";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2302@1.1/Dovemayo_gothic.woff2") format("woff2");
  font-weight: normal;
  font-style: normal;
}
@font-face {
  font-family: "OAGothic-ExtraBold";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2302@1.0/OAGothic-ExtraBold.woff2") format("woff2");
  font-weight: 800;
  font-style: normal;
}

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

* {
  font-family: Dovemayo_gothic;
  font-weight: 1000;
}

h2 {
  font-family: OAGothic-ExtraBold;
}

nav {
  padding: 30px;
  background-color: #00a6e3;
  min-height: 4.5rem;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #1faed3;
}
</style>

<style scoped>
.container {
  background-color: #e4feff;
  min-height: calc(100vh - 4.5rem);
}
</style>
