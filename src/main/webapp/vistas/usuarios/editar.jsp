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
	
		<form action="editarusuario.do" method="post">
			<input type="hidden" name="id" value="${usuario.idUsuario}">
			<div class="modal-body">
				<div class="mb-3">
					<label for="nombre" class="col-form-label">Nombre:</label> <input
						type="text" class="form-control" id="nombre" name="nombre"
						required value="${usuario.nombre}">
				</div>

				<div class="mb-3">
					<label for="email" class="col-form-label">Email:</label> <input
						type="email" class="form-control" id="email" name="email" required
						value="${usuario.email}">
				</div>

				<div class="mb-3">
					<label for="pass" class="col-form-label">Password:</label> <input
						type="password" class="form-control" id="pass" name="pass" required
						value="${usuario.password}">
				</div>

				<div class="mb-3">
					<label for="presupuesto" class="col-form-label">Presupuesto:</label>
					<input type="number" class="form-control" id="presupuesto"
						name="presupuesto" min="1" pattern="\d+" required value="${usuario.presupuesto}">
				</div>

				<div class="mb-3">
					<label for="tiempo" class="col-form-label">Tiempo disponible:</label> 
					<input type="number" class="form-control" id="tiempo"
						name="tiempo" min="1" pattern="\d+" required value="${usuario.tiempoDisponible}">
				</div>

				<div class="mb-3">
					<label for="preferencia" class="col-form-label">Preferencia:</label>
					<select class="form-select" aria-label="Default select example"
						name="preferencia">
						<c:forEach items="${tiposAtracciones}" var="tipo">
							<c:choose>
								<c:when test="${tipo == usuario.preferencia}">
									<option value="${tipo}" selected="selected">${tipo}</option>>
								</c:when>
								<c:otherwise>
									<option value="${tipo}">${tipo}</option>
								</c:otherwise>
							</c:choose>
					</c:forEach>
					</select>
				</div>

				<div class="mb-3"><!-- -----------------radio button esta activo----------------- -->
					<c:choose>
						<c:when test="${usuario.estaActivo == true}">
							<div class="form-check">
								<input class="form-check-input" type="radio" name="estaActivo"
									id="estaActivo1" value="true" checked> <label
									class="form-check-label" for="estaActivo1">Activo</label>
							</div>
						</c:when>
						<c:otherwise>
							<div class="form-check">
								<input class="form-check-input" type="radio" name="estaActivo"
									id="estaActivo1" value="true"> <label
									class="form-check-label" for="estaActivo1">Activo</label>
							</div>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${usuario.estaActivo == false}">
							<div class="form-check">
								<input class="form-check-input" type="radio" name="estaActivo"
									id="estaActivo2" value="false" checked> <label
									class="form-check-label" for="estaActivo2">Inactivo</label>
							</div>
						</c:when>
						<c:otherwise>
							<div class="form-check">
								<input class="form-check-input" type="radio" name="estaActivo"
									id="estaActivo2" value="false"> <label
									class="form-check-label" for="estaActivo2">Inactivo</label>
							</div>						
						</c:otherwise>
					</c:choose>
				</div><!-- -----------------fin radio button esta activo----------------- -->

				<div class="mb-3"> <!-- -----------------radio button es administrador----------------- -->
					<c:choose>
						<c:when test="${usuario.esAdmin == true}">
							<div class="form-check">
								<input class="form-check-input" type="radio" name="esAdmin"
									id="esAdmin1" value="true" checked> <label
									class="form-check-label" for="esAdmin1">Es
									administrador</label>
							</div>
						</c:when>
						<c:otherwise>
							<div class="form-check">
								<input class="form-check-input" type="radio" name="esAdmin"
									id="esAdmin1" value="true"> <label
									class="form-check-label" for="esAdmin1">Es
									administrador</label>
							</div>						

						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${usuario.esAdmin == false}">
							<div class="form-check">
								<input class="form-check-input" type="radio" name="esAdmin"
									id="esAdmin2" value="false" checked> <label
									class="form-check-label" for="esAdmin2">No es
									administrador</label>
							</div>
						</c:when>
						<c:otherwise>
							<div class="form-check">
								<input class="form-check-input" type="radio" name="esAdmin"
									id="esAdmin2" value="false" > <label
									class="form-check-label" for="esAdmin2" >No es
									administrador</label>
							</div>
						</c:otherwise>
					</c:choose>
				</div> <!-- -----------------fin radio button es administrador----------------- -->

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
