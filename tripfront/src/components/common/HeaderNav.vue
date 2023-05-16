<template>
  <header>
    <nav>
      <div class="nav-home"><router-link to="/">trip</router-link></div>
      <div class="nav-menu">
        <router-link to="/">Home</router-link>
        <router-link to="/">Search</router-link>
        <router-link to="/">Plan</router-link>
        <router-link to="/">Post</router-link>
        <router-link to="/">Notice</router-link>
      </div>
      <div class="nav-user">
        <a href="#">Login</a>
        <a href="#">Logout</a>
        <a href="#">Regist </a>
        <a href="#">MyPage </a>
      </div>
    </nav>
  </header>
</template>

<script>
export default {
  name: "HeaderNav",
};
</script>

<style scope>
nav {
  display: flex;
  justify-content: space-between;
}
.nav-home {
  justify-self: start;
}
.nav-menu {
  justify-self: center;
}
.nav-user {
  justify-self: end;
}
a {
  padding: 5px;
}
</style>
