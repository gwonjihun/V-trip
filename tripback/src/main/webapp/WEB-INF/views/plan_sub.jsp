<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

  <%@ include file="./include/header.jsp" %>
    <main id="main">
      <!-- ======= Portfolio Section ======= -->
      <section id="resume" class="resume">
        <div class="container" data-aos="fade-up">
          <div class="section-title">
            <h2>나만의 여행 계획을 세워보세요!</h2>
          </div>
        </div>
        <div class="container">
          <div class="row">
            <div class="col-lg-3">
              <h3 class="resume-title">여행 위치를 정해보세요</h3>
              <div class="resume-item">
                <h5>여행 위치 1</h5>
                <div id="p1" class="input-group mb-3">
                  <input type="text" id="place1" class="form-control" placeholder="Place 1" aria-label="Place 1"
                    aria-describedby="button-addon2" />
                  <button class="btn btn-outline-secondary" type="button" id="button-addon1">확인</button>
                  <div id="in1"></div>
                </div>
              </div>
              <div class="resume-item">
                <h5>여행 위치 2</h5>
                <div id="p2" class="input-group mb-3">
                  <input type="text" id="place2" class="form-control" placeholder="Place 2" aria-label="Place 2"
                    aria-describedby="button-addon2" />
                  <button class="btn btn-outline-secondary" type="button" id="button-addon2">확인</button>
                </div>
              </div>
              <div class="resume-item">
                <h5>여행 위치 3</h5>
                <div id="p3" class="input-group mb-3">
                  <input type="text" id="place3" class="form-control" placeholder="Place 3" aria-label="Place 3"
                    aria-describedby="button-addon2" />
                  <button class="btn btn-outline-secondary" type="button" id="button-addon3">확인</button>
                </div>
              </div>
            </div>

            <div class="col-lg-9">
              <div class="map_wrap">
                <form class="my-3" onsubmit="return false;" role="search">
                  <%-- <form class="my-3" method="post" action="info" role="search">--%>
                    <div class="d-flex mb-3">
                      <%-- <input type="hidden" name="action" value="select">--%>
                        <select id="search-area" class="form-select me-2" style="width: 30%">
                          <option value="0" selected>지역선택</option>
                        </select> <select id="search-content-id" name="search_content_id" class="form-select me-2"
                          style="width: 40%">
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
                          aria-label="검색어" style="width: 40%" />
                        <button id="btn-search" class="btn btn-outline-success"
                          style="width: 10%; height: 100%;">검색</button>
                    </div>

                </form>
                <div id="map" class="mt-3" style="width: 100%; height: 400px"></div>

              </div>

            </div>
          </div>

          <div clas="row">
            <a href="index.html"><button class="btn btn-dark">제출</button></a>
          </div>
        </div>
      </section>
    </main>
    <!-- End #main -->
    <script type="text/javascript"
      src="//dapi.kakao.com/v2/maps/sdk.js?appkey=a89eda6c702665409c98e615cd2f9837&libraries=services,clusterer,drawing"></script>

    <script>
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

    </script>
    <%@ include file="./include/footer.jsp" %>