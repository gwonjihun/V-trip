<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ include
file="./include/header.jsp" %>

<main id="main">
  <!-- ======= About Section ======= -->
  <section id="about" class="about">
    <div class="container row mx-auto" data-aos="fade-up">
      <div class="section-title">
        <div>
          <h2>내 정보</h2>
        </div>
      </div>
      <div class="col-lg-8 mx-auto">
        <span class="float-end m-1">
          <a href="#" data-bs-toggle="modal" data-bs-target="#alterModal">내정보수정</a>
          <a href="${root}/user/delete?id=${userinfo.id}" class="text-danger ms-1">회원탈퇴</a>
        </span>
        <table class="table table-striped table-bordered table-hover">
            <tr>
              <th>아이디</th>
              <td>${userinfo.id}</td>
            </tr>
            <tr>
              <th>이름</th>
              <td>${userinfo.name}</td>
            </tr>
            <tr>
              <th>이메일</th>
              <td>${userinfo.email}</td>
            </tr>
            <tr>
              <th>닉네임</th>
              <td>${userinfo.nickname}</td>
            </tr>
            <tr>
              <th>포인트</th>
              <td>${userinfo.point}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </section>
</main>
<div class="modal" id="alterModal">
  <div class="modal-dialog">
    <div class="modal-content">
      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title align-content-center">내정보수정</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>

      <div class="modal-body">
        <form method="post" id="alterfm" action="${root}/user/update">
          <label class="m-1">ID</label>
          <div class="input-group mb-3">
            <input
              type="text"
              name="id"
              class="form-control"
              placeholder="ID"
              aria-label="Text input with checkbox"
              value="${userinfo.id}"
              readonly
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
              value="${userinfo.name}"
              readonly
            />
          </div>
          <label class="m-1">password</label>
          <div class="input-group mb-3">
            <input
              type="text"
              name="password"
              class="form-control"
              placeholder="password"
              aria-label="Text input with checkbox"
              value="${userinfo.password}"
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
              value="${userinfo.email}"
            />
          </div>
          <label class="m-1">nickname</label>
          <div class="input-group mb-3">
            <input
              type="text"
              id="nickname"
              name="nickname"
              class="form-control"
              placeholder="nickname"
              aria-label="Text input with checkbox"
              value="${userinfo.nickname}"
            />
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" id="btn-alter" class="btn btn-dark" data-bs-dismiss="modal">수정</button>
      </div>
    </div>
  </div>
</div>

<script>
  document.querySelector("#btn-alter").addEventListener("click", function () {
    document.querySelector("#alterfm").submit();
  });
</script>
<%@ include file="./include/footer.jsp" %>
