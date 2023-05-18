<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="./include/header.jsp"%>
<!-- ======= Hero Section ======= -->
<section id="hero" class="d-flex align-items-center">
	<div class="container d-flex flex-column align-items-center"
		data-aos="zoom-in" data-aos-delay="100">
		<h1>Enjoy Your Trip!</h1>
		<h2>당신의 행복한 여행을 위한 코스를 만들어보세요!</h2>
		<a href="${root}/search" class="btn-about">여행 하러 가기</a>
	</div>
</section>
<!-- End Hero -->
<section id="portfolio" class="portfolio">
	<div class="container" data-aos="fade-up">
		<div class="section-title">
			<h2>지금 떠오르는 지역들</h2>
		</div>

		<div id="hotplace" class="row portfolio-container" data-aos="fade-up"
			data-aos-delay="200">
		</div>
	</div>
	<script type="text/javascript">
      window.onload = function () {
          let areaUrl = "http://localhost:8080/trip/tripapi/hotplace";
          fetch(areaUrl, { method: "get" })
            .then((response) => response.json())
            .then((data) => registPlace(data));
          function registPlace(data) {
        	  document.getElementById("hotplace").innerHTML = "";
            let tripList = ``;;
            
        	data.forEach((place)=>{
        		console.log(place.first_image);
        		 tripList +=`<div class="col-lg-4 col-md-6 portfolio-item filter-app">
                <div class="portfolio-wrap">
                  <img src="`+place.first_image +`" class="img-fluid" alt="" />
                  <div class="portfolio-info">
                    <h4>`+ place.title +`</h4>
                    <p>`+ place.addr1+`</p>
                    <div class="portfolio-links">
                      <a href="#" data-gallery="portfolioGallery" class="portfolio-lightbox" title="App 3"
                        ><i class="bx bx-plus"></i
                      ></a>
                      <a href="portfolio-details.html" class="portfolio-details-lightbox" data-glightbox="type: external" title="Portfolio Details"
                        ><i class="bx bx-link"></i
                      ></a>
                    </div>
                  </div>
                </div>
              </div>`
        	});
        	document.getElementById("hotplace").innerHTML = tripList;
        	document.getElementById("footer").style="display:none";
           
          }
        }
      </script>
</section>

<%@ include file="./include/footer.jsp"%>

