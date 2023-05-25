<template>
  <div>
    <div id="map">
      <slot></slot>
    </div>
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
      plan_markers: [],
      search_markers: [],
      infoWindows: [],
      result: {
        addr: "",
        content_id: "",
        title: "",
      },
      plan_info: [],
      line: [],
    };

  },

  props: {
    trips: {},
    input_plan_info: [],
    isfinish: { type: Boolean, deep: true },
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

        this.search_loadMaker();
      } else {
        this.deleteMarker();
      }
    },
    isfinish() {
      console.log("kakao checking watch");
      console.log(kakao);
      this.plan_loadMaker();
    },
    plan_info: {
      handler() {
        this.positions = [];
        console.log("watch plan_info", this.plan_info);
        // this.temp();
      },
      deep: true,
    },
    input_plan_info: {
      hander() {
        console.log("watch input_plan_info", this.input_plan_info);
      },
      deep: true,
    },
  },

  created() { },

  mounted() {
    // api 스크립트 소스 불러오기 및 지도 출력
    console.log("마운트 시작");
    console.log(window.kakao);
    // console.log(window.kakao.maps)
    if (window.kakao) {
      kakao.maps.load(this.loadMap);
      console.log("load map으로 시작");
    } else {
      this.loadScript();
      console.log("loadscript로 시작");
    }

    // this.loadMaker();
  },

  methods: {
    temp() {
      console.log("plan_info change");
      console.log(this.plan_info);

      if (this.plan_info.length > 0) {
        console.log(window.kakao.maps.LatLng);
        this.plan_info.forEach((plan) => {
          let obj = JSON.parse(JSON.stringify(plan));
          console.log(plan, obj);
          console.log(obj === plan);
          console.log(obj == plan);
          console.log(obj.latitude, obj.longitude);
          obj.latlng = new kakao.maps.LatLng(obj.latitude, obj.longitude);
          console.log(typeof plan.latitude);
          this.positions.push(obj);
        });
        console.log(this.positions);
      }
      // this.loadMaker();
    },
    // api 불러오기
    loadScript() {
      const script = document.createElement("script");
      script.type = "text/javascript";
      script.id = "plan";
      script.src = "//dapi.kakao.com/v2/maps/sdk.js?appkey=377be3df509834a1bb6080cfbb519dba&autoload=false";
      /* global kakao */
      script.onload = () => window.kakao.maps.load(this.loadMap);

      document.head.appendChild(script);

      // this.loadMaker();
    }, // 이거는 고정
    // 맵 출력하기
    loadMap() {
      const container = document.getElementById("map");
      const options = {
        center: new window.kakao.maps.LatLng(36.480701, 127.570667),
        level: 8,
      };
      console.log(container);
      this.map = new window.kakao.maps.Map(container, options);
      this.plan_loadMaker();
    },
    search_loadMaker() {
      // 현재 표시되어있는 marker들이 있다면 marker에 등록된 map을 없애준다.
      console.log("1111");
      // console.log(this.trips);
      this.search_deleteMarker();
      console.log("2222");
      // 마커 이미지를 생성합니다
      //   const imgSrc = require("@/assets/map/markerStar.png");
      // 마커 이미지의 이미지 크기 입니다
      //   const imgSize = new kakao.maps.Size(24, 35);
      //   const markerImage = new kakao.maps.MarkerImage(imgSrc, imgSize);

      // 마커를 생성합니다
      this.search_markers = [];
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
              <div><img src = "${position.first_image != "" ? position.first_image : "http://creculture.com/theme/crecult/skin/latest/img_youpop/noimage.png"}" style="width:100px;heigh:100px; "></div>
              <div>
                ${position.title}<br>
                ${position.addr1}
              </div>
            </div>
            <div class="info-window-button">
              <button id ="planadd" class="btn btn-primary"> 방문하기</button>
              <button id = "insta" type="button" class="btn"><img src="https://search.pstatic.net/sunny/?src=https%3A%2F%2Fwww.instagram.com%2Fstatic%2Fimages%2Fico%2Ffavicon.ico%2F36b3ee2d91ed.ico&type=f30_30_png_expire24"></button>
              <button id = "naver" type="button" class="btn"><img src="https://search.pstatic.net/sunny/?src=https%3A%2F%2Fwww.naver.com%2Ffavicon.ico%3F1&type=f30_30_png_expire24"></button>
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
            this.result.first_image = position.first_image;
            console.log(this.result);
            console.log("계획 추가 데이터 전송 emit 발생");
            this.$emit("trip", this.result); // 여기서 이제 plan-table vue로 저장될 데이터 전송

            console.log("!@#########");
            console.log(this.result);
          };

          // console.log(infowindow.getContent());
        });

        this.search_markers.push(marker);
        this.infoWindows.push(infowindow);
      });
      console.log("마커수 ::: " + this.search_markers.length);
      //마커를 등록하면서
      // 4. 지도를 이동시켜주기
      // 배열.reduce( (누적값, 현재값, 인덱스, 요소)=>{ return 결과값}, 초기값);

      // 지도 중심을 부드럽게 이동시킵니다
      // 만약 이동할 거리가 지도 화면보다 크면 부드러운 효과 없이 이동합니다
      this.map.panTo(this.positions[0].latlng);
    },
    plan_loadMaker() {
      console.log("plan load marker")
      this.plan_deleteMarker();
      this.plan_info = this.input_plan_info;
      this.temp();

      // 마커를 생성합니다
      this.plan_markers = [];
      console.log("line을 위한 positions조회")
      console.log(this.positions);
      console.log("불러온 장소 개수 : " + this.positions.length);
      for (let i = 0; i < this.plan_info.length; i++) {
        this.line.push(new kakao.maps.LatLng(this.plan_info[i].latitude, this.plan_info[i].longitude));
      }
      console.log(this.line);
      var polyline = new kakao.maps.Polyline({
        path: this.line, // 선을 구성하는 좌표배열 입니다
        strokeWeight: 4, // 선의 두께 입니다
        strokeColor: '#FF7E7E', // 선의 색깔입니다
        strokeOpacity: 1, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
        strokeStyle: 'solid' // 선의 스타일입니다
      });

      // 지도에 선을 표시합니다 
      polyline.setMap(this.map);


      this.positions.forEach((position) => {
        var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_red.png', // 마커이미지의 주소입니다    
          imageSize = new kakao.maps.Size(40, 49), // 마커이미지의 크기입니다
          imageOption = { offset: new kakao.maps.Point(27, 69) }; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

        // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);

        const marker = new window.kakao.maps.Marker({
          image: markerImage,
          map: this.map, // 마커를 표시할 지도
          position: position.latlng, // 마커를 표시할 위치
          clickable: true, //마커클릭시 지도 클릭이벤트 방지
          zIndex: 3
          //   image: markerImage, // 마커의 이미지
        });

        var iwContent = `
          <div class="info-window">
            <div class="info-window-info">
              <div><img src = "${position.first_image != "" ? position.first_image : "http://creculture.com/theme/crecult/skin/latest/img_youpop/noimage.png"}" style="width:100px;heigh:100px; "></div>
              <div>
                ${position.title}<br>
                ${position.addr1}
              </div>
            </div>
            <div class="info-window-button">
              <button id = "insta" type="button" class="btn"><img src="https://search.pstatic.net/sunny/?src=https%3A%2F%2Fwww.instagram.com%2Fstatic%2Fimages%2Fico%2Ffavicon.ico%2F36b3ee2d91ed.ico&type=f30_30_png_expire24"></button>
              <button id = "naver" type="button" class="btn"><img src="https://search.pstatic.net/sunny/?src=https%3A%2F%2Fwww.naver.com%2Ffavicon.ico%3F1&type=f30_30_png_expire24"></button>
              <button id = "close" type="button" class="btn btn-danger">닫기</button>
            </div>
          </div>
          `;
        // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다

        const infowindow = new window.kakao.maps.InfoWindow({
          content: iwContent,
        });

        // 마커에 클릭이벤트를 등록합니다
        window.kakao.maps.event.addListener(marker, "click", () => {
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
            let url = position.title.replace(" ", "");
            window.open(`https://search.naver.com/search.naver?query=${url}`);
          };
          let insta = document.getElementById("insta");
          insta.onclick = () => {
            let url = position.title.replace(" ", "");
            window.open(`https://www.instagram.com/explore/tags/${url}`);
          };


          // console.log(infowindow.getContent());
        });

        this.plan_markers.push(marker);
        this.infoWindows.push(infowindow);
      });
      if (this.positions.length > 0) this.map.panTo(this.positions[0].latlng);
    },
    plan_deleteMarker() {
      if (this.plan_markers.length > 0) {
        this.plan_markers.forEach((item) => {
          item.setMap(null);
        });
      }
      for (var i = 0; i < this.infoWindows.length; i++) {
        this.infoWindows[i].close();
      }
    },
    search_deleteMarker() {
      if (this.search_markers.length > 0) {
        this.search_markers.forEach((item) => {
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
