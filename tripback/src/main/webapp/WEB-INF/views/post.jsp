<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="./include/header.jsp" %>
<main id="main">
  <!-- ======= About Section ======= -->
  <section id="about" class="about">
    <div class="container" data-aos="fade-up">
      <div class="section-title">
        <div>
          <h2>여행정보공유</h2>
        </div>
      </div>
      <div>
        <c:if test="${not empty userinfo}">
        <a class="btn btn-outline-dark mb-2" role="button" data-bs-toggle="modal"
          data-bs-target="#insert-modal">글쓰기</a>
        </c:if>
      </div>
      <div>
        <table class="table table-striped table-bordered table-hover">
          <thead>
            <tr>
              <th>번호</th>
              <th class="col-8">제목</th>
              <th>작성자</th>
              <th>날짜</th>
              <th>조회수</th>
              <th>댓글수</th>
              <th>좋아요</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="board" items="${list}" varStatus="vs">
              <tr>
                <td>${vs.count}</td>
                <td>
                  <a role="button" data-bs-toggle="modal" data-bs-target="#detail-modal"
                    onclick="(function() {
                      document.querySelector('#detail-title').innerText = '${board.title}';
                      document.querySelector('#detail-name').innerText = '${board.writer.name}';
                      document.querySelector('#detail-content').innerText = `${board.content}`;
                      document.querySelector('#detail-createat').innerText = '${board.createat}';
                      document.querySelector('#detail-reads').innerText = '${board.reads}';
                      document.querySelector('#detail-like-num').innerText = '${board.like_num}';
                      document.querySelector('#detail-comment-num').innerText = '${board.comment_num}';
                      if ('${board.writername}' === '${userinfo.id}') {
                        document.querySelector('#detail-btn-div').innerHTML = `
                          <a href='${root}/board/delete?content_id=${board.content_id}' class='btn btn-danger'>삭제</a>
                        `;
                      } else {
                        document.querySelector('#detail-btn-div').innerHTML = ``;
                      }
                    })()">
                    ${board.title}
                  </a>
                </td>
                <td>${board.writer.name}</td>
                <td>${board.createat}</td>
                <td>${board.reads}</td>
                <td>${board.comment_num}</td>
                <td>${board.like_num}</td>
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

<div class="modal" id="insert-modal">
  <div class="modal-dialog">
    <div class="modal-content">
      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title align-content-center">글쓰기</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>
      <div class="modal-body">
        <form method="post" action="${root}/board/insert">
          <input type="hidden" name="writername" value="${userinfo.id}"/>
          <div class="form-group mb-3">
            <label for="insert-title">제목</label>
            <input type="text" class="form-control" id="insert-title" name="title" />
          </div>
          <div class="form-group">
            <label for="insert-content">내용</label>
            <textarea class="form-control" id="insert-content" name="content" rows="3"></textarea>
          </div>
          <div class="mt-3 text-center">
            <input type="submit" class="btn btn-outline-dark" value="등록" />
          </div>
        </form>
      </div>
    </div>
  </div>
</div>

<div class="modal" id="update-modal">
  <div class="modal-dialog">
    <div class="modal-content">
      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title align-content-center">글수정</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>
      <div class="modal-body">
        <form method="post" action="${root}/board/update">
          <input type="hidden" name="writername" value="${userinfo.id}"/>
          <div class="form-group mb-3">
            <label for="update-title">제목</label>
            <input type="text" class="form-control" id="update-title" name="title" />
          </div>
          <div class="form-group">
            <label for="update-content">내용</label>
            <textarea class="form-control" id="update-content" name="content" rows="3"></textarea>
          </div>
          <div class="mt-3 text-center">
            <input type="submit" class="btn btn-outline-dark" value="수정" />
          </div>
        </form>
      </div>
    </div>
  </div>
</div>

<div class="modal" id="detail-modal">
  <div class="modal-dialog">
    <div class="modal-content">
      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title align-content-center" id="detail-title">해운대 좋아요!!</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>
      <div class="modal-body">
        <table>
          <tr>
            <th>작성자</th>
            <td id="detail-name"></td>
          </tr>
          <tr>
            <th>내용</th>
            <td id="detail-content"></td>
          </tr>
          <tr>
            <th>작성일</th>
            <td id="detail-createat"></td>
          </tr>
          <tr>
            <th>조회수</th>
            <td id="detail-reads"></td>
          </tr>
          <tr>
            <th>좋아요</th>
            <td id="detail-like-num"></td>
          </tr>
          <tr>
            <th>댓글 수</th>
            <td id="detail-comment-num"></td>
          </tr>
        </table>
      </div>
      <div class="modal-footer" id="detail-btn-div"></div>
    </div>
  </div>
</div>

<%@ include file="./include/footer.jsp" %>