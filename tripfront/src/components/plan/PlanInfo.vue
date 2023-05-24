<template>
  <div>
    <div v-for="(trip_lists, index) in plans_data" :key="index">
      <h2>{{ index + 1 }}일차 여행일정</h2>
      <b-list-group>

        <draggable v-if="ismodi" :list="trip_lists.trip_list" group="test">
          <b-list-group-item v-for="(item, tindex) in plans_data[index].trip_list" :key="tindex">
            <b-row align-v="center">
              <b-col cols="12">
                <img :src='item.first_image' style="width: 5%; height: 5%;">

              </b-col>
              <b-col cols="9">
                {{ item.title }}
              </b-col>
              <b-col cols="3">
                <b-button v-show="ismodi" type="button" variant="danger"
                  @click="removeElement(tindex, index)"><b-icon-x /></b-button>
              </b-col>
            </b-row>
          </b-list-group-item>
        </draggable>
        <b-list-group-item v-else v-for="(item, tindex) in plans_data[index].trip_list" :key="tindex">
          <b-row align-v="center">
            <b-col cols="12">
              <img :src="item.first_image" style="width: 5%; height: 5%;">
            </b-col>
            <b-col cols="9">
              {{ item.title }}
            </b-col>
            <b-col cols="3">
              <b-button v-show="ismodi" type="button" variant="danger"
                @click="removeElement(tindex, index)"><b-icon-x /></b-button>
            </b-col>
          </b-row>
        </b-list-group-item>
      </b-list-group>
    </div>
    <div v-if="!ismodi">
      <b-button type="button" @click="modifyflag">수정</b-button>
      <b-button type="button" @click="movelist">목록</b-button>
    </div>
    <div v-if="ismodi">
      <b-button type="button" @click="updateplan">등록</b-button>
      <b-button type="button" @click="movelist">목록</b-button>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import draggable from "vuedraggable";
import { updateDetail } from "@/api/planapi.js";
export default {
  components: {
    draggable,
  },
  data() {
    return {
      //plan : 여행 일자 이름 
      isplan: false,

      plan: {},
      plans_data: [{
        days: Number,
        trip_list: [],
      }
      ],
      target: {
        plan: {},
        trip_list: [],
      },//여행계획을 업데이트할 때 사용할 데이터 변수

      ismodi: false,
    };
  },
  props: {
    //이것은 plan info table용
    //init_list는 저장된 여행 계획에 받아오는 객체
    init_list: { days: Number, trip_list: [], },

    //plannav에서 갱신되는 날짜를 갱신해주는 객체
    plan_init: { title: "", start_date: "", end_date: "", share: "0", },

    //추가될 인풋 데이터
    trip: { type: Object, deep: true, },

    //데이터 수정 권한 플래그
    ismodify: { type: Boolean, require: true, default: false }
  },
  watch: {
    //수정
    ismodify() { this.ismodi = this.ismodify; },

    //여행 경로 데이터 추가
    trip() {
      console.log("trip append list");
      this.plans_data[0].trip_list.push(this.trip);
      console.log(this.plans_data);
    },

    init_list:
    {
      handler() {
        this.plans_data = this.init_list;
      }
      , deep: true,
    },
    plan_init: {
      handler() {
        this.plan.title = this.plan_init.title;
        this.plan.plan_id = this.plan_init.plan_id;
        this.plan.startDate = this.plan_init.startDate;
        this.plan.endDate = this.plan_init.endDate;
        this.plan.share = this.plan_init.share;
      }
      , deep: true,
    }
  },
  methods: {
    //이거 의밈없는 함수임 결국 업데이트로 전환해야하는 함수이다.
    updateplan() {
      if (!this.isLogin) {
        alert("로그인 후에 이용해 주세요");
        return;
      }

      const pl = { ...this.plan };
      this.target = { plan: pl, trip_list: [] };
      this.target.plan.sidocode = this.plans_data[0].trip_list[0].sido_code;
      for (let i = 0; i < this.plans_data.length; i++) {
        console.log(i);
        for (let j = 0; j < this.plans_data[i].trip_list.length; j++) {
          let a = {
            plan_id: Number(this.plan.plan_id),
            content_id: this.plans_data[i].trip_list[j].content_id,
            days: this.plans_data[i].days,
            index: j,
          }; //여기는 plandetail값이 들어가야한다.
          this.target.trip_list.push(a);
        }
      }

      let msg = "등록을 실패하였습니다. 로그인을 확인해주세요.";
      console.log(this.target);
      updateDetail(this.target.plan.plan_id,
        {
          plan: this.target.plan,
          planlist: this.target.trip_list
        },
        () => {
          console.log("등록을 성공했습니다.");
          this.$router.push("/plan");
        },
        (err) => {
          console.log("에러발생 : " + err);
          console.log(msg);
        }
      );
      console.log(this.target);
    },

    removeElement: function (tindex, index) {
      this.$delete(this.plans_data[index].trip_list, tindex);
    },
    movelist() {
      this.$router.push("/plan");
    },
    modifyflag(event) {
      event.preventDefault();
      // if (!this.isLogin) {
      //   alert("로그인 후에 이용해 주세요");
      //   return;
      // }
      this.ismodi = !this.ismodi;
      this.$emit("modifyhandler", this.ismodi);

    },
  },

  computed: {
    ...mapState("userStore", ["isLogin", "userinfo"]),
  },



};
</script>
<style></style>
