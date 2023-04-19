<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/partials/head.jsp"></jsp:include>
</head>
<body>
	
	<main class="container">

		<c:if test="${atraccion != null && !atraccion.isValid()}">
			<div class="alert alert-danger">
				<p>Se encontraron errores al crear la atracción.</p>
			</div>
		</c:if>

		<form action="/AppWebTierraMedia/atracciones/create.do" method="post">
			<jsp:include page="/vistas/atracciones/formCreate.jsp"></jsp:include>
		</form>
	</main>
</body>
</html>