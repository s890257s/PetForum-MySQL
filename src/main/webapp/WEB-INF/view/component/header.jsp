<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<header class="p-3 mb-3 border-bottom">
	<div class="container">
		<div
			class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
			<ul
				class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
				<li><a href="${root}/" class="nav-link px-2 link-secondary">寵物論壇</a></li>
				<li><a href="${root}/pets" class="nav-link px-2 link-dark">萌寵們</a></li>
				<li><a href="${root}/profile" class="nav-link px-2 link-dark">個人資訊</a></li>
				<li><a href="${root}/add_pet" class="nav-link px-2 link-dark">新增寵物</a></li>
			</ul>
			<div class="dropdown text-end">
				<c:if test="${loggedInMember==null }">
					<a href="${root}/login">
						<button class="btn btn-primary">會員登入</button>
					</a>
				</c:if>

				<c:if test="${loggedInMember!=null }">
					<a href="${root}/profile">${loggedInMember.mName }</a>
					<img src="${loggedInMember.mPhoto }" style="width: 100px">
					<a href="${root}/Logout.do"><button>登出</button></a>
				</c:if>

			</div>
		</div>
	</div>
</header>