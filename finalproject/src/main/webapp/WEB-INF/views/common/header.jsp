<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<link rel="canonical"
	href="https://getbootstrap.com/docs/5.2/examples/album/" />

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous" />
<link rel="stylesheet" href="${path}/resources/csss/root.css" />
</head>
<body>
	<header class="p-3 mb-3 border-bottom">
		<div class="container">
			<div
				class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
				<a href="#"
					class="d-flex align-items-center mb-2 mb-lg-0 text-dark text-decoration-none">
					<img src="https://picsum.photos/id/684/100/50" alt=""> </svg>
				</a>

				<ul
					class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
					<li><a href="#" class="nav-link px-2 link-secondary">루트공유게시판</a>
					</li>
					<li><a href="#" class="nav-link px-2 link-dark">자유게시판</a></li>
					<li><a href="#" class="nav-link px-2 link-dark">공지사항</a></li>
					<li><a href="#" class="nav-link px-2 link-dark">Q&A</a></li>
				</ul>

				<form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3">
					<input type="search" class="form-control" placeholder="#제주도"
						aria-label="Search" />
				</form>

				<div class="dropdown text-end">
					<a href="#"
						class="d-block link-dark text-decoration-none dropdown-toggle"
						id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">
						<img src="https://github.com/mdo.png" alt="mdo" width="32"
						height="32" class="rounded-circle" />
					</a>
					<ul class="dropdown-menu text-small"
						aria-labelledby="dropdownUser1">
						<li><a class="dropdown-item" href="#">마이페이지</a></li>

						<li><hr class="dropdown-divider" /></li>
						<li><a class="dropdown-item" href="#">로그아웃</a></li>
					</ul>
				</div>
			</div>
		</div>
	</header>
</body>
</html>