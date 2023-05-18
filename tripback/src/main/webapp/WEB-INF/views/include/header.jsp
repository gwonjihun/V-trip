<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <c:set var="root" value="${pageContext.request.contextPath}"></c:set>
  <head>
    <meta charset="utf-8" />
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />

    <title>SSAFY TRIP</title>
    <meta content="" name="description" />
    <meta content="" name="keywords" />

    <!-- Favicons -->
    <link href="${root}/assets/img/trip.png" rel="icon" />
    <link href="${root}/assets/img/trip.png" rel="earth" />

    <!-- Google Fonts -->
    <link
      href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
      rel="stylesheet"
    />

    <!-- Vendor CSS Files -->
    <link href="${root}/assets/vendor/aos/aos.css" rel="stylesheet" />
    <link href="${root}/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
    <link href="${root}/assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet" />
    <link href="${root}/assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet" />
    <link href="${root}/assets/vendor/glightbox/css/glightbox.min.css" rel="stylesheet" />
    <link href="${root}/assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet" />

    <!-- Template Main CSS File -->
    <link href="${root}/assets/css/style.css" rel="stylesheet" />

    <!-- =======================================================
  * Template Name: Kelly
  * Updated: Mar 10 2023 with Bootstrap v5.2.3
  * Template URL: https://bootstrapmade.com/kelly-free-bootstrap-cv-resume-html-template/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
  </head>

  <body>
    <!-- ======= Header ======= -->
    <header id="header" class="fixed-top">
      <div class="container-fluid d-flex justify-content-between align-items-center">
        <h1 class="logo me-auto me-lg-0"><a href="${root}/">TRIP</a></h1>
        <!-- Uncomment below if you prefer to use an image logo -->
        <!-- <a href="index.html" class="logo"><img src="${root}/assets/img/logo.png" alt="" class="img-fluid"></a>-->

        <nav id="navbar" class="navbar">
          <ul>
            <li><a href="${root}">Home</a></li>
            <li><a href="${root}/search">Search</a></li>
            <li><a href="${root}/plan">Plan</a></li>
            <li><a href="${root}/board">Post</a></li>
            <li><a href="${root}/notice">Notice</a></li>
          </ul>
          <i class="bi bi-list mobile-nav-toggle"></i>
        </nav>
        <!-- .navbar -->

        <div>
          <c:if test="${empty userinfo}">
            <div class="login-before">
              <a href="#" data-bs-toggle="modal" data-bs-target="#LoginModal">Login</a>
              <a href="#" data-bs-toggle="modal" data-bs-target="#RegistModal">Regist</a>
              <!-- <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#myModal">Open modal</button> -->
              <!--  <a href="#" data-bs-toggle="modal" data-bs-target="#RegisterModal">Register</a>-->
            </div>
          </c:if>
          <c:if test="${not empty userinfo }">
            <div class="login-after">
              <a>${userinfo.name }님 환영합니다.&nbsp </a>
              <a href="${root}/user/logout">Logout</a>
              <c:if test="${userinfo.user_type == 'admin' }">
                <a href="${root}/user/admin">&nbsp&nbspAdmin</a>
              </c:if>
              <c:if test="${userinfo.user_type == 'user' }">
                <a href="${root}/user/myPage">&nbsp&nbsp MyPage</a>
              </c:if>
            </div>
          </c:if>
        </div>
      </div>
    </header>
    <!-- End Header -->

    <!-- 로그인 modal -->
    <div class="modal" id="LoginModal">
      <div class="modal-dialog">
        <div class="modal-content">
          <!-- Modal Header -->
          <div class="modal-header">
            <h4 class="modal-title align-content-center">Login</h4>
            <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
          </div>

          <div class="modal-body">
            <form method="post" id="loginfm" action="${root}/user/login">
              <label class="m-1">ID</label>
              <div class="input-group mb-3">
                <input
                  type="text"
                  id="id"
                  name="id"
                  class="form-control"
                  placeholder="ID"
                  aria-label="Text input with checkbox"
                />
              </div>
              <label class="m-1">password</label>
              <div class="input-group mb-3">
                <input
                  type="password"
                  id="password"
                  name="password"
                  class="form-control"
                  placeholder="password"
                  aria-label="Text input with checkbox"
                />
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" id="btn-login" class="btn btn-dark loginmodal">로그인</button>
          </div>
        </div>
      </div>
    </div>

    <!--  회원가입 모달 -->
    <div class="modal" id="RegistModal">
      <div class="modal-dialog">
        <div class="modal-content">
          <!-- Modal Header -->
          <div class="modal-header">
            <h4 class="modal-title align-content-center">회원가입</h4>
            <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
          </div>

          <div class="modal-body">
            <form method="post" id="registfm" action="${root}/user/regist">
              <label class="m-1">ID</label>
              <div class="input-group mb-3">
                <input
                  type="text"
                  id="id-regist"
                  name="id"
                  class="form-control"
                  placeholder="ID"
                  aria-label="Text input with checkbox"
                />
              </div>
              <label class="m-1">name</label>
              <div class="input-group mb-3">
                <input
                  type="text"
                  id="name"
                  name="name"
                  class="form-control"
                  placeholder="name"
                  aria-label="Text input with checkbox"
                />
              </div>
              <label class="m-1">password</label>
              <div class="input-group mb-3">
                <input
                  type="password"
                  id="password-regist"
                  name="password"
                  class="form-control"
                  placeholder="password"
                  aria-label="Text input with checkbox"
                />
              </div>
              <label class="m-1">email</label>
              <div class="input-group mb-3">
                <input
                  type="text"
                  id="email"
                  name="email"
                  class="form-control"
                  placeholder="email"
                  aria-label="Text input with checkbox"
                />
              </div>
              <input type="hidden" name="user_type" value="user" />
              <label class="m-1">nickname</label>
              <div class="input-group mb-3">
                <input
                  type="text"
                  id="nickname"
                  name="nickname"
                  class="form-control"
                  placeholder="nickname"
                  aria-label="Text input with checkbox"
                />
              </div>
              <input type="hidden" name="point" value="0" />
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" id="btn-regist" class="btn btn-dark" data-bs-dismiss="modal">회원가입</button>
          </div>
        </div>
      </div>
    </div>

    <script>
      let blog = document.querySelector("#btn-login");
      blog.addEventListener("click", function () {
        if (!document.querySelector("#id").value) {
          alert("아이디 입력!!");
          return;
        } else if (!document.querySelector("#password").value) {
          alert("비밀번호 입력!!");
          return;
        } else {
          document.querySelector("#loginfm").submit();
        }
      });

      let breg = document.querySelector("#btn-regist");
      breg.addEventListener("click", function () {
        if (!document.querySelector("#id-regist").value) {
          alert("아이디 입력!!");
          return;
        } else if (!document.querySelector("#password-regist").value) {
          alert("비밀번호 입력!!");
          return;
        } else if (!document.querySelector("#email").value) {
          alert("이메일 입력!!");
          return;
        } else if (!document.querySelector("#name").value) {
          alert("이름 입력!!");
          return;
        } else if (!document.querySelector("#nickname").value) {
          alert("닉네임 입력!!");
          return;
        } else {
          document.querySelector("#registfm").submit();
        }
      });

      let msg = "${msg}";
      console.log(msg);
      if (msg) {
        alert(msg);
      }
    </script>
  </body>
</html>
