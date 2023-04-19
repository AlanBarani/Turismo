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

	
		

		<form class="row g-3"  action="/AppWebTierraMedia/promociones/editarAbsol.do" method="post">
			<input type="text" name="id" value="${promocion.idProducto}">
			<jsp:include page="/vistas/promociones/formAbsol.jsp"></jsp:include>
		</form>
	</main>
</body>
</html>
