import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "../views/HomeView.vue";
import SearchView from "../views/SearchView.vue";

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
    component: SearchView,
  },
  {
    path: "/plan",
    name: "plan",
    component: SearchView,
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
        path: "regist",
        name: "boardRegist",
        props: {
          type: "register",
        },
        component: () => import("@/components/board/BoardRegist"),
      },
      {
        path: ":content_id",
        name: "boardDetail",
        component: () => import("@/components/board/BoardDetail"),
      },
      {
        path: ":content_id/modify",
        name: "boardModify",
        component: () => import("@/components/board/BoardRegist"),
      },
    ],
  },
  {
    path: "/notice",
    // name: "notice",
    component: () => import("@/views/NoticeView"),
    children: [
      {
        path: "",
        name: "NoticeList",
        component: () => import("@/components/board/BoardList"),
      },
      {
        path: ":content_id",
        name: "noticeDetail",
        component: () => import("@/components/board/BoardDetail"),
      },
    ],
  },
  {
    path: "/user/:id",
    name: "userPage",
    component: () => import("@/views/UserView"),
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
