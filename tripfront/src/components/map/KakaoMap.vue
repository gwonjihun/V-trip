<template>
  <div>
    <div id="map"></div>
  </div>
</template>

<script>
export default {
  name: "KakaoMap",
  components: {},
  data() {
    return {
      map: null,
      positions: [],
      markers: [],
    };
  },
  /*
    positions에서는 map
  */
  /* 
    [{
      content : "
      <div><img>1번 이미지가 없으면 default이미지가 로드되도록</div>
      <div>제목</div><br>
      <div>장소</div>"
    }]
  */
  props: {
    trips: [],
  },
  watch: {
    trips() {
      console.log("지역들", this.trips);
      this.positions = [];
      this.trips.forEach((trip) => {
        let obj = {};
        obj.title = trip.statNm;
        obj.latlng = new kakao.maps.LatLng(trip.lat, trip.lng);

        this.positions.push(obj);
      });
      this.loadMaker();
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
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?appkey=377be3df509834a1bb6080cfbb519dba&autoload=false";
      /* global kakao */
      script.onload = () => window.kakao.maps.load(this.loadMap);

      document.head.appendChild(script);
    }, // 이거는 고정
    // 맵 출력하기
    loadMap() {
      const container = document.getElementById("map");
      const options = {
        center: new window.kakao.maps.LatLng(33.450701, 126.570667),
        level: 3,
      };
      this.map = new window.kakao.maps.Map(container, options);
      this.positions = [{
        "title": "카카오 테스트용",
        "latlng": new window.kakao.maps.LatLng(33.450701, 126.570667),
      }];

      this.loadMaker();
      //   this.loadMaker();
    }, // 이거도 고정
    // 지정한 위치에 마커 불러오기
    loadMaker() {
      // 현재 표시되어있는 marker들이 있다면 marker에 등록된 map을 없애준다.
      console.log("1111");
      this.deleteMarker();
      console.log("2222");
      // 마커 이미지를 생성합니다
      //   const imgSrc = require("@/assets/map/markerStar.png");
      // 마커 이미지의 이미지 크기 입니다
      //   const imgSize = new kakao.maps.Size(24, 35);
      //   const markerImage = new kakao.maps.MarkerImage(imgSrc, imgSize);

      // 마커를 생성합니다
      this.markers = [];
      this.positions.forEach((position) => {
        const marker = new kakao.maps.Marker({
          map: this.map, // 마커를 표시할 지도
          position: position.latlng, // 마커를 표시할 위치
          clickable: true //마커클릭시 지도 클릭이벤트 방지
          //   image: markerImage, // 마커의 이미지
        });

        var iwContent = '<div style="padding:5px;"><div><div>히히히히히히</div></div>' +
          '<img alt="close" width="14" height="13" src="http://t1.daumcdn.net/localimg/localimages/07/mapjsapi/2x/bt_close.gif"' +
          'style="position: absolute; right: 5px; top: 5px; cursor: pointer;"></div>'; // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다

        const infowindow = new kakao.maps.InfoWindow({
          // map: this.map,
          content: iwContent,

        });

        // 마커에 클릭이벤트를 등록합니다
        kakao.maps.event.addListener(marker, 'click', () => {
          // 마커 위에 인포윈도우를 표시합니다
          // console.log("!!!!자 이제 infowindows가 만들어 질꺼야!!!");

          // infowindow.setZIndex(888);
          infowindow.open(this.map, marker);
          // console.log(infowindow.getContent());
        });

        this.markers.push(marker);
      });
      console.log("마커수 ::: " + this.markers.length);
      //마커를 등록하면서 
      // 4. 지도를 이동시켜주기
      // 배열.reduce( (누적값, 현재값, 인덱스, 요소)=>{ return 결과값}, 초기값);
      const bounds = this.positions.reduce(
        (bounds, position) => bounds.extend(position.latlng),
        new kakao.maps.LatLngBounds()
      );
      console.log(bounds);
      this.map.setBounds(bounds);
    },
    deleteMarker() {
      console.log("마커 싹 지우자!!!", this.markers.length);
      if (this.markers.length > 0) {
        this.markers.forEach((item) => {
          console.log(item);
          item.setMap(null);
        });
      }
    },
  },
};
</script>

<style scoped>
#map {
  width: 70%;
  height: 400px;
}
</style>
