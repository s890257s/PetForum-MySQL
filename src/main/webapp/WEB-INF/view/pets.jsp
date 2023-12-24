<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${webName}-所有寵物</title>

<jsp:include page="${component}/common_imports.jsp" />

</head>
<body>
	<jsp:include page="${component}/header.jsp" />

	<main>
		<section class="py-3 text-center container">
			<div class="row py-lg-5">
				<div class="col-lg-6 col-md-8 mx-auto">
					<p class="lead text-muted">
						(以下由ChatGPT產生)<br>加入我們的寵物社交平台，閱讀飼養心得、瀏覽可愛的寵物相片，與其他寵物愛好者分享您的寵物故事和照片。透過按讚、收藏和分享相簿等功能，建立更多寵物社交關係，探索寵物世界！
					</p>

				</div>
			</div>
		</section>

		<div class="album py-5 bg-light">
			<div class="container">
				<div
					class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3 text-center"
					id="petHome">

					<c:forEach items="${pets}" var="p">

						<div class="col">
							<div class="card shadow-sm">
								<img src="${p.pPhotoBase64 }" class="w-100" />
								<p class="card-text fs-3">${p.pName }</p>
								<div class="d-flex justify-content-between align-items-center">

									<div class="m-3">
										<a href="#">${p.member.mName }</a>
									</div>

									<c:if test="${loggedInMember!=null }">
										<button type="button"
											class="btn btn-sm btn-outline-secondary m-2 likeBTN"
											petID="${p.pID }">
											<c:if test="${p.liked }">
												<i class="fa-solid fa-heart" style="color: red"></i>
											</c:if>
											<c:if test="${!p.liked }">
												<i class="fa-regular fa-heart"></i>
											</c:if>
										</button>
									</c:if>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>

	</main>

	<jsp:include page="${component}/footer.jsp" />

	<script type="text/javascript">
		const likeBTNs = document.querySelectorAll(".likeBTN");
		
		for(let b of likeBTNs){
			b.addEventListener("click",function(){
				const petID = b.getAttribute("petID");
				fetch("ToggleLike.do?pID="+petID).then(rs=>rs.text()).then(status=>{
					if(status=="like!"){
						b.innerHTML='<i class="fa-solid fa-heart" style="color: red"></i>'
					}
					if(status=="unlike!"){
						b.innerHTML='<i class="fa-regular fa-heart"></i>'
					}
				})
			})
		}
		
	</script>

</body>
</html>