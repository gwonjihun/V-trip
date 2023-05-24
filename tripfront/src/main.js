import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import { BootstrapVue, IconsPlugin } from "bootstrap-vue";

// Import Bootstrap and BootstrapVue CSS files (order is important)
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";
import { dateFilter, tooLongText } from "./util/filter";

// Make BootstrapVue available throughout your project
Vue.use(BootstrapVue);
// Optionally install the BootstrapVue icon components plugin
Vue.use(IconsPlugin);

Vue.filter("dateFilter", dateFilter);
Vue.filter("tooLongText", tooLongText);

Vue.config.productionTip = false;

Vue.prototype.$EventBus = new Vue();
Vue.prototype.$kakao = () => window.kakao.maps.load(this.loadMap);
new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
