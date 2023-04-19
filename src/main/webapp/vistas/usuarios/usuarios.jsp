<%@ page language="java" contentType="text/html; charset=Utf-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<meta charset="UTF-8">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<script defer src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
	<script defer src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>

<title>Usuarios</title>
</head>
<body>
	<h2>Pagina de Usuarios</h2>
	<div class="d-flex" >
		<div class="card col-sm-4" >
		<!-- ---- columna form ----- -->
			<div class="card-body" >
				<form>
					<div class="form-group">
						<label>campo</label>
						<input type="" name=""  id="" class="form-control">
					</div>
					<div class="form-group">
						<label>campo</label>
						<input type="" name=""  id="" class="form-control">
					</div>
					<div class="form-group">
						<label>campo</label>
						<input type="" name=""  id="" class="form-control">
					</div>
					<div class="form-group">
						<label>campo</label>
						<input type="" name=""  id="" class="form-control">
					</div>
					<div class="form-group">
						<label>campo</label>
						<input type="" name=""  id="" class="form-control">
					</div>
					<input type="submit" name="accion"  value="Agregar" class="btn btn-danger">
					<input type="submit" name="accion"  value="Actualizar" class="btn btn-primary">
				</form>
			</div>
		<!-- ---- fin columna form ----- -->
		</div>

		

		<div class="col-sm-8" >
		<!-- ---- columna tabla listado usuarios ----- -->
			<table class="table table-hover" >
				<thead>
					<tr>
						<th>Id</th>
						<th>Nombre</th>
						<th>Presupuesto</th>
						<th>Tiempo</th>
						<th>Preferencia</th>
						<th>Admin</th>
						<th>Estado</th>
						<th>Accion</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listaUsuarios}" var="usr" >
						<tr>
							<td><c:out value="${usr.idUsuario}"/> </td>
							<td><c:out value="${usr.nombre}"></c:out></td>
							<td><c:out value="${usr.presupuesto}"></c:out></td>
							<td><c:out value="${usr.tiempoDisponible}"></c:out></td>
							<td><c:out value="${usr.preferencia}"></c:out></td>
							<!-- -------------------- -->
							<c:if test="${usr.esAdmin == true }">
								<td><span class="bg-greee" >Administrador</span></td>
							</c:if>
							<c:if test="${usr.esAdmin == false }">
								<td><span class="bg-red" >Cliente</span></td>
							</c:if>
								<!-- -------------------- -->
							<c:if test="${usr.estaActivo == true }">
								<td><span class="bg-greee" >Activo</span></td>
							</c:if>
							<c:if test="${usr.estaActivo == false }">
								<td><span class="bg-red" >Inactivo</span></td>
							</c:if>
							<td>
								<a href="#" Class="btn btn-primary" >Editar  </a>
								<a href="#"Class="btn btn-danger">Desactivar</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>				
			</table>
		<!-- ---- fin columna tabla listado usuarios ----- -->
		</div>	
	</div>
</body>
</html>