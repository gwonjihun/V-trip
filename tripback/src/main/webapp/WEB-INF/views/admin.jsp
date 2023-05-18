<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="./include/header.jsp" %>
  
   <main id="main">
      <!-- ======= About Section ======= -->
      <section id="about" class="about">
        <div class="container" data-aos="fade-up">
          <div class="section-title">
            <div>
              <h2>사용자 정보</h2>
            </div>
          </div>
          <div>
            <table class="table table-striped table-bordered table-hover">
              <thead>
                <tr>
                  <th>아이디</th>
                  <th>이메일</th>
                  <th>이름</th>
                  <th>유저타입</th>
                </tr>
              </thead>
              <tbody>
              <c:forEach var="user" items="${users }">
                <tr>
                  <td>${user.id }</td>
                  <td>${user.email }</td>
                  <td>${user.name }</td>
                  <td>${user.user_type }
                  	<c:if test="${user.user_type=='user' }">
                  	<span class="float-end">
                  		<a href="${root}/user?action=alter&id=${user.id}" class="btn-danger"> 수정 </a>
                  		<a href="${root}/user?action=delete&id=${user.id}" class="btn-danger"> 삭제 </a>
                  	</span>
                  	</c:if>
                  </td>
                </tr>
                </c:forEach>
              </tbody>
            </table>

            <div class="d-flex justify-content-center">
              <div class="text-center">
                <ul class="pagination">
                  <li class="page-item">
                    <a class="page-link" href="#" aria-label="Previous">
                      <span aria-hidden="true" class="text-muted">&laquo;</span>
                    </a>
                  </li>
                  <li class="page-item"><a class="page-link text-muted" href="#">1</a></li>
                  <li class="page-item"><a class="page-link text-muted" href="#">2</a></li>
                  <li class="page-item"><a class="page-link text-muted" href="#">3</a></li>
                  <li class="page-item">
                    <a class="page-link" href="#" aria-label="Next">
                      <span aria-hidden="true" class="text-muted">&raquo;</span>
                    </a>
                  </li>
                </ul>
              </div>
            </div>
          </div>
        </div>
      </section>
    </main>

   
 <%@ include file="./include/footer.jsp" %>

