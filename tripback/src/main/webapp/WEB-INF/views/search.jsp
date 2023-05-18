<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

  <%@ include file="./include/header.jsp" %>
    <main id="main"> <!-- ======= About Section ======= -->
      <section id="about" class="about">
        <div class="container" data-aos="fade-up">
          <div class="section-title">
            <div>
              <h2>관광지 검색</h2>
            </div>
          </div>

          <form class="my-3" onsubmit="return false;" role="search">
            <%-- <form class="my-3" method="post" action="info" role="search">--%>
              <div class="d-flex mb-3">
                <%-- <input type="hidden" name="action" value="select">--%>
                  <select id="search-area" class="form-select me-2">
                    <option value="0" selected>지역선택</option>
                  </select> <select id="search-content-id" name="search_content_id" class="form-select me-2">
                    <option value="0" selected>관광지 유형</option>
                    <option value="12">관광지</option>
                    <option value="14">문화시설</option>
                    <option value="15">축제공연행사</option>
                    <option value="25">여행코스</option>
                    <option value="28">레포츠</option>
                    <option value="32">숙박</option>
                    <option value="38">쇼핑</option>
                    <option value="39">음식점</option>
                  </select> <input id="search-keyword" class="form-control me-2" type="search" placeholder="검색어"
                    aria-label="검색어" />
                  <button id="btn-search" class="btn btn-outline-success">검색</button>
              </div>

          </form>
          <div id="map" class="mt-3" style="width: 100%; height: 400px"></div>
          <div class="row">

            <table class="table table-striped" id="tests" style="display: none">
              <thead>
                <tr>
                  <th>대표이미지</th>
                  <th>관광지명</th>
                  <th>주소</th>
                  <th>위도</th>
                  <th>경도</th>
                </tr>
              </thead>
              <%--<c:forEach var="attraction" items="${attractions}">
                <tr>
                  <th>
                  </th>
                  <th>
                  </th>
                  <th>
                  </th>
                  <th>
                  </th>
                  <th>${attraction.}
                  </th>
                </tr>
                </c:forEach>--%>
                <tbody id="trip-list"></tbody>
            </table>
          </div>
    </main>
    <!-- End #main -->

    <script type="text/javascript"
      src="//dapi.kakao.com/v2/maps/sdk.js?appkey=a89eda6c702665409c98e615cd2f9837&libraries=services,clusterer,drawing"></script>
    <script>
      //	731bb205381c5e2049be28de45ca7a7a
      let serviceKey = "TbajtGmRP5AZsCchDBL%2F79v1wL59nnRBKwCLIAwuHxy8KEW5L6Oa%2BdO9VQgX0Ykzz98jxMwp%2FSZBG9vONpACqw%3D%3D";
      window.onload = function () {
        let areaUrl = "https://apis.data.go.kr/B551011/KorService1/areaCode1?serviceKey=TbajtGmRP5AZsCchDBL%2F79v1wL59nnRBKwCLIAwuHxy8KEW5L6Oa%2BdO9VQgX0Ykzz98jxMwp%2FSZBG9vONpACqw%3D%3D&numOfRows=20&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json";
        fetch(areaUrl, { method: "GET" })
          .then((response) => response.json())
          .then((data) => makeOption(data, "search-area"));
        function makeOption(data, id) {
          let areas = data.response.body.items.item;
          // console.log(areas);
          let sel = document.getElementById(id);
          areas.forEach((area) => {
            let opt = document.createElement("option");
            opt.setAttribute("value", area.code);
            opt.appendChild(document.createTextNode(area.name));

            sel.appendChild(opt);
          });
        }
      }

      //TbajtGmRP5AZsCchDBL%2F79v1wL59nnRBKwCLIAwuHxy8KEW5L6Oa%2BdO9VQgX0Ykzz98jxMwp%2FSZBG9vONpACqw%3D%3D
      //TbajtGmRP5AZsCchDBL%2F79v1wL59nnRBKwCLIAwuHxy8KEW5L6Oa%2BdO9VQgX0Ykzz98jxMwp%2FSZBG9vONpACqw%3D%3D


      document.getElementById("btn-search").addEventListener("click", () => {

        let areaCode = document.getElementById("search-area").value;
        let contentTypeId = document.getElementById("search-content-id").value;
        let keyword = document.getElementById("search-keyword").value;

        fetch('http://localhost:8080/trip/tripapi/search?sido_code=' + areaCode + '&content_type_id=' + contentTypeId + '&title=' + keyword)
          .then((response) =>
            response.json()
          ).then((data) =>
            makeList(data)
          );
      });

      var positions; // marker 배열.
      function makeList(data) {
        console.log(data);
        if (data.length === 0) {
          alert("검색된 결과가 없습니다.");
          return;
        }
        document.getElementById("trip-list").innerHTML = "";
        document.getElementById("tests").setAttribute("style", "display:");
        let trips = data;
        let tripList = ``;
        positions = [];

        trips.forEach((area) => {
          tripList += `
          <tr onclick="moveCenter("+area.latitude+", "area.longitude+");">
            <td><img src="` + (area.first_image ? area.first_image : area.first_image2) + `"width="100px"></td>
            <td>`+ area.title + `</td>
            <td>`+ area.addr1 + ` ` + area.addr2 + `</td>
            <td>`+ area.latitude + `</td>
            <td>`+ area.longitude + `</td>
          </tr>`;
          console.log(area.first_image, area.title, area.addr1, area.addr2, area.latitude, area.mapx);
          let markerInfo = {
            title: area.title,
            latlng: new kakao.maps.LatLng(area.latitude, area.longitude),
          };
          positions.push(markerInfo);
        });
        document.getElementById("trip-list").innerHTML = tripList;
        console.log(tripList);
        displayMarker();
      }

      // document.querySelector("#search-sido").addEventListener("change", function() {
      //   let gugun = document.querySelector("#search-gugun");
      //   gugun.options.length=1;

      //   let sido = document.querySelector("#search-sido");
      //   let areaCode=sido.options[sido.selectedIndex].value;
      //   console.log(areaCode);
      //   let gugunUrl=`https://apis.data.go.kr/B551011/KorService1/areaCode1?serviceKey=TbajtGmRP5AZsCchDBL%2F79v1wL59nnRBKwCLIAwuHxy8KEW5L6Oa%2BdO9VQgX0Ykzz98jxMwp%2FSZBG9vONpACqw%3D%3D&numOfRows=20&pageNo=1&MobileOS=ETC&MobileApp=AppTest&areaCode=${areaCode}&_type=json`;  
      //   fetch(gugunUrl, {method: "GET"})
      //     .then( (response)=> response.json())
      //     .then((data)=>makeOption(data, "search-gugun"));
      // });
      var mapContainer = document.getElementById("map"), // 지도를 표시할 div
        mapOption = {
          center: new kakao.maps.LatLng(37.500613, 127.036431), // 지도의 중심좌표
          level: 5, // 지도의 확대 레벨
        };

      // 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
      var map = new kakao.maps.Map(mapContainer, mapOption);

      function displayMarker() {
        // 마커 이미지의 이미지 주소입니다
        var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

        for (var i = 0; i < positions.length; i++) {
          // 마커 이미지의 이미지 크기 입니다
          var imageSize = new kakao.maps.Size(24, 35);

          // 마커 이미지를 생성합니다
          var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

          // 마커를 생성합니다
          var marker = new kakao.maps.Marker({
            map: map, // 마커를 표시할 지도
            position: positions[i].latlng, // 마커를 표시할 위치
            title: positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
            image: markerImage, // 마커 이미지
          });
        }

        // 첫번째 검색 정보를 이용하여 지도 중심을 이동 시킵니다
        map.setCenter(positions[0].latlng);
      }

      function moveCenter(lat, lng) {
        map.setCenter(new kakao.maps.LatLng(lat, lng));
      }

      const kakaokey = "";

      window.addEventListener("load", show_map);
      var map;
      function show_map() {
        var mapContainer = document.getElementById("map"), // 지도를 표시할 div
          mapOption = {
            center: new kakao.maps.LatLng(37.500701, 127.036907), // 지도의 중심좌표
            level: 3, // 지도의 확대 레벨
          };

        map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

        // 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
        var mapTypeControl = new kakao.maps.MapTypeControl();

        // 지도에 컨트롤을 추가해야 지도위에 표시됩니다
        // kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
        map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

        // 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
        var zoomControl = new kakao.maps.ZoomControl();
        map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

        // set_markup();
      }

      let festival_key = "CIph4Ep9WZIczZRzxN3VnWaqSnt22CGUzr0ykamQMkhFmozlHUowzXKwYrYJKpNAdkfaBrwZakZoFCoIc9gVkQ%3D%3D";


      // 부산 6 북구 8


      // function set_markup() {
      //   var markerPosition = new kakao.maps.LatLng(37.500701, 127.036907);

      //   // 마커를 생성합니다
      //   var marker = new kakao.maps.Marker({
      //     position: markerPosition,
      //   });

      //   // 마커가 지도 위에 표시되도록 설정합니다
      //   marker.setMap(map);
      // }

    // document.querySelector("#inlineCheckbox1").addEventListener("change", function () {
    //   console.log("!!!");
    // });
    // value에 따라서 fetch함수 url 변경되도록 하는 함수 구현


    </script>
    <%@ include file="./include/footer.jsp" %>