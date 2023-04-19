<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
<jsp:include page="/partials/head.jsp"></jsp:include>
</head>
<body>


	<main class="container">

		<c:if test="${attraction != null && !attraction.isValid()}">
			<div class="alert alert-danger">
				<p>Se encontraron errores al crear la atracción.</p>
			</div>
		</c:if>
		
		

		<form class="row g-3 needs-validation" novalidate action="/AppWebTierraMedia/promociones/crear.do" method="post">
			<jsp:include page="/vistas/promociones/formCrear.jsp"></jsp:include>
		</form>
	</main>
</body>
</html>
