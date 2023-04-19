<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/partials/head.jsp"></jsp:include>
</head>
<body>

	<main class="container">
		<div class="alert alert-danger">
			<% String flash = (String) request.getAttribute("flash"); %>
			<% if (flash != null && flash != "") {%>
			<%= flash%>
			<% }%>	
		</div>
		<form action="crearusuario.do" method="post">
			<div class="modal-body">
				<div class="mb-3">
					<label for="nombre" class="col-form-label">Nombre:</label> <input
						type="text" class="form-control" id="nombre" name="nombre"
						required >
				</div>

				<div class="mb-3">
					<label for="email" class="col-form-label">Email:</label> <input
						type="email" class="form-control" id="email" name="email" required>
				</div>

				<div class="mb-3">
					<label for="pass" class="col-form-label">Password:</label> <input
						type="password" class="form-control" id="pass" name="pass" required>
				</div>

				<div class="mb-3">
					<label for="presupuesto" class="col-form-label">Presupuesto:</label>
					<input type="number" class="form-control" id="presupuesto"
						name="presupuesto" min="1" pattern="\d+" required>
				</div>

				<div class="mb-3">
					<label for="tiempo" class="col-form-label">Tiempo disponible:</label> 
					<input type="number" class="form-control" id="tiempo"
						name="tiempo" min="1" pattern="\d+" required>
				</div>

				<div class="mb-3">
					<label for="preferencia" class="col-form-label">Preferencia:</label>
					<select class="form-select" aria-label="Default select example"
						name="preferencia">
						<c:forEach items="${tiposAtracciones}" var="tipo">
							<option value="${tipo}">${tipo}</option>
						</c:forEach>
					</select>
				</div>

				<div class="mb-3">
					<div class="form-check">
						<input class="form-check-input" type="radio" name="estaActivo"
							id="estaActivo1" value="true" checked> <label
							class="form-check-label" for="estaActivo1">Activo</label>
					</div>
					<div class="form-check">
						<input class="form-check-input" type="radio" name="estaActivo"
							id="estaActivo2" value="false"> <label
							class="form-check-label" for="estaActivo2">Inactivo</label>
					</div>
				</div>

				<div class="mb-3">
					<div class="form-check">
						<input class="form-check-input" type="radio" name="esAdmin"
							id="esAdmin1" value="true" checked> <label
							class="form-check-label" for="esAdmin1">Es administrador</label>
					</div>
					<div class="form-check">
						<input class="form-check-input" type="radio" name="esAdmin"
							id="esAdmin2" value="false"> <label
							class="form-check-label" for="esAdmin2">No es
							administrador</label>
					</div>
				</div>

				<div>
					<button type="submit" class="btn btn-primary">Guardar</button>
					<a onclick="window.history.back();" class="btn btn-secondary"
						role="button">Cancelar</a>
				</div>

			</div>
		</form>
	</main>
</body>
</html>
