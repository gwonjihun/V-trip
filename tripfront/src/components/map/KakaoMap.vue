<template>
  <div>
    <div id="map">
      <slot></slot>
    </div>
    <!-- <img src="@/assets/icon/naver.png" /> -->
    <!-- <b-icon icon="instagram"></b-icon> -->
  </div>
</template>

<script>
export default {
  name: "KakaoMap",
  data() {
    return {
      // positions: [],
      map: null,
      positions: [],
      markers: [],
      infoWindows: [],
      result: {
        addr: "",
        content_id: "",
        title: "",
      },
    };
  },

  props: {
    trips: [],
  },
  watch: {
    trips() {
      this.positions = [];
      if (this.trips.length > 0) {
        this.trips.forEach((trip) => {
          let obj = {};
          obj = trip;
          obj.latlng = new kakao.maps.LatLng(trip.latitude, trip.longitude);

          this.positions.push(obj);
        });

        this.loadMaker();
      } else {
        this.deleteMarker();
      }
    },
  },
  created() { },
  mounted() {
    // api 스크립트 소스 불러오기 및 지도 출력
    if (window.kakao && window.kakao.maps) {
      this.loadMap();
    } else {
      this.loadScript();
    }
  },
  methods: {
    // api 불러오기
    loadScript() {
      const script = document.createElement("script");
      script.type = "text/javascript";
      script.src = "//dapi.kakao.com/v2/maps/sdk.js?appkey=377be3df509834a1bb6080cfbb519dba&autoload=false";

      /* global kakao */
      script.onload = () => kakao.maps.load(this.loadMap);

      document.head.appendChild(script);
    }, // 이거는 고정
    // 맵 출력하기
    loadMap() {
      const container = document.getElementById("map");
      const options = {
        center: new window.kakao.maps.LatLng(36.480701, 127.570667),
        level: 12,
      };
      this.map = new window.kakao.maps.Map(container, options);
      this.$EventBus.$emit("kakao-map-load");
    },
    loadMaker() {
      // 현재 표시되어있는 marker들이 있다면 marker에 등록된 map을 없애준다.
      console.log("1111");
      // console.log(this.trips);
      this.deleteMarker();
      console.log("2222");
      // 마커 이미지를 생성합니다
      //   const imgSrc = require("@/assets/map/markerStar.png");
      // 마커 이미지의 이미지 크기 입니다
      //   const imgSize = new kakao.maps.Size(24, 35);
      //   const markerImage = new kakao.maps.MarkerImage(imgSrc, imgSize);

      // 마커를 생성합니다
      this.markers = [];
      console.log("불러온 장소 개수 : " + this.positions.length);
      console.log(this.positions);
      this.positions.forEach((position) => {
        const marker = new kakao.maps.Marker({
          map: this.map, // 마커를 표시할 지도
          position: position.latlng, // 마커를 표시할 위치
          clickable: true, //마커클릭시 지도 클릭이벤트 방지
          //   image: markerImage, // 마커의 이미지
        });

        var iwContent = `
          <div class="info-window">
            <div class="info-window-info">
              <div><img src = "${position.first_image != ""
            ? position.first_image
            : "http://creculture.com/theme/crecult/skin/latest/img_youpop/noimage.png"
          }" style="width:100px;heigh:100px; "></div>
              <div>
                ${position.title}<br>
                ${position.addr1}
              </div>
            </div>
            <div class="info-window-button">
              <button id ="planadd" class="btn btn-primary"> 방문하기</button>
              <button id = "insta" type="button" class="btn"><img src="icon/instagram.png"></button>
              <button id = "naver" type="button" class="btn"><img src="icon/naver.png"></button>
              <button id = "close" type="button" class="btn btn-danger">닫기</button>
            </div>
          </div>
          `;
        // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다

        const infowindow = new kakao.maps.InfoWindow({
          content: iwContent,
        });

        // 마커에 클릭이벤트를 등록합니다
        kakao.maps.event.addListener(marker, "click", () => {
          // 마커 위에 인포윈도우를 표시합니다
          console.log("##############################");
          console.log(marker);
          for (var i = 0; i < this.infoWindows.length; i++) {
            this.infoWindows[i].close();
          }

          console.log(this.result); // 여기까지는 바낀다.
          //     result: {
          //   addr: "",
          //   content_id: "",
          //   title: ""
          // }

          /* 여기서 이제 전달할 tran_data 배열에 값을 저장시켜준다.그리고 idonclick에서 emit이벤트 처리를 저장해놔준다. */
          infowindow.open(this.map, marker);
          let close = document.getElementById("close");
          close.onclick = () => {
            infowindow.close(this.map, marker);
          };
          let naver = document.getElementById("naver");
          naver.onclick = () => {
            console.log("!@#######################");
            console.log(position.title);
            let url = position.title.replace(" ", "");
            console.log(url);
            window.open(`https://search.naver.com/search.naver?query=${url}`);
          };
          let insta = document.getElementById("insta");
          insta.onclick = () => {
            console.log("!@#######################");
            console.log(position.title);
            let url = position.title.replace(" ", "");
            console.log(url);
            window.open(`https://www.instagram.com/explore/tags/${url}`);
          };
          let planadd = document.getElementById("planadd");

          planadd.onclick = () => {
            console.log(position);
            this.result.addr = position.addr;
            this.result.content_id = position.content_id;
            this.result.title = position.title;
            this.result.sidocode = position.sido_code;
            console.log(this.result);

            this.$emit("plan", this.result); // 여기서 이제 plan-table vue로 저장될 데이터 전송

            console.log("!@#########");
            console.log(this.result);
          };

          // console.log(infowindow.getContent());
        });

        this.markers.push(marker);
        this.infoWindows.push(infowindow);
      });
      console.log("마커수 ::: " + this.markers.length);
      //마커를 등록하면서
      // 4. 지도를 이동시켜주기
      // 배열.reduce( (누적값, 현재값, 인덱스, 요소)=>{ return 결과값}, 초기값);

      // 지도 중심을 부드럽게 이동시킵니다
      // 만약 이동할 거리가 지도 화면보다 크면 부드러운 효과 없이 이동합니다
      this.map.panTo(this.positions[0].latlng);
    },
    deleteMarker() {
      console.log("마커 싹 지우자!!!", this.markers.length);
      if (this.markers.length > 0) {
        this.markers.forEach((item) => {
          console.log(item);
          item.setMap(null);
        });
      }
      for (var i = 0; i < this.infoWindows.length; i++) {
        this.infoWindows[i].close();
      }
    },
  },
};
</script>

<style scoped>
#map {
  width: 100%;
  height: 800px;
}
</style>

<style>
.info-window {
  padding: 10px;
  min-width: 200px;
  min-height: 150px;
  border-radius: 5px;
}

.info-window-info {
  display: flex;
  height: calc(100% - 50px);
  /* height: 100%; */
}

.info-window-button {
  height: 50px;
}
</style>
