import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "../views/HomeView.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: "/search",
    name: "search",
    component: HomeView,
  },
  {
    path: "/plan",
    name: "plan",
    component: HomeView,
  },
  {
    path: "/board",
    // name: "board",
    component: () => import("@/views/BoardView"),
    children: [
      {
        path: "",
        name: "boardList",
        component: () => import("@/components/board/BoardList"),
      },
      {
        path: "detail/:content_id",
        name: "boardDetail",
        component: () => import("@/components/board/BoardDetail"),
      },
      {
        path: "regist",
        name: "boardRegist",
        component: () => import("@/components/board/BoardRegist"),
      },
    ],
  },
  {
    path: "/notice",
    name: "notice",
    component: HomeView,
  },
  {
    path: "/about",
    name: "about",
    component: () => import("../views/AboutView.vue"),
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
