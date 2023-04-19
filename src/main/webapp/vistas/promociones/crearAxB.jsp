<%@page import="java.util.List"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
 <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
<jsp:include page="/partials/head.jsp"></jsp:include>
</head>
<body>


	
	
	<script>
		function disableATR() {

		var x = document.getElementById("tipoAtraccion").value;			
		
		if (x != 'AVENTURA') {
			document.getElementById("aventuraAxB").setAttribute("hidden", "true");
		} else {
			document.getElementById("aventuraAxB").removeAttribute("hidden");
		}
		if (x != 'PAISAJES') {
			document.getElementById("paisajesAxB").setAttribute("hidden", "true");
			
		} else {
			document.getElementById("paisajesAxB").removeAttribute("hidden");
		}
		if (x != 'DEGUSTACION') {
			document.getElementById("degustacionAxB").setAttribute("hidden", "true");
		} else{
			document.getElementById("degustacionAxB").removeAttribute("hidden")	
		}

	}

	</script>

	<main class="container">

		<c:if test="${flash != null}">
			<div class="alert alert-danger">
				<p>
					<c:out value="${flash}" />
					<c:if test="${errors != null}">
						<ul>
							<c:forEach items="${errors}" var="entry">
								<li><c:out value="${entry.getValue()}"></c:out></li>
							</c:forEach>
						</ul>
					</c:if>
				</p>
			</div>
		</c:if>



		<div class="bg-light p-4 mb-3 rounded">
			<h1>Listado de usuarios de la Tierra Media</h1>
		</div>
		
		<c:if test="${usr.esAdmin == true}">
		<div class="row g-3">
			<div class="col-md-4">
			<a class="btn btn-primary"
					role="button" onclick="mostrarFormAxB()"> <i class="bi bi-plus-lg"></i>Promocion AxB
				</a>
			</div>
			
			<div class="col-md-4">
				<a class="btn btn-primary"
					role="button" onclick="mostrarFormABS()"> <i class="bi bi-plus-lg"></i>Promocion Absoluta
				</a>
			</div>
			
			<div class="col-md-4">
				<a class="btn btn-primary"
					role="button" onclick="mostrarFormPOR()"> <i class="bi bi-plus-lg"></i>Promocion Porcentual
				</a>
			</div>
			
			</div>
			</c:if>
		</div>
		
	
		
		

		<form name="f1"  id="AxB" class="row g-3 needs-validation" novalidate
			method="POST">
			
			<div class="col-md-12">
				<label for="formGroupExampleInput" class="form-label">Nombre</label>
				<input required type="text" class="form-control" id="nombre"
					placeholder="Nombre">
			</div>
						
			<div class="col-md-12">
				<label for="horario" class="form-label">Tipo de Atracciones</label>
				<select name="tiposAtr" required class="form-select" id="tipoAtraccion"
					aria-label="TipoAtraccion" onchange="disableATR()">
					<option value="" selected>Escoge...</option>
					<c:forEach items="${listaTiposAtraccion}" var="tipoAtraccion">
						<option value="${tipoAtraccion}"><c:out
								value="${tipoAtraccion}"></c:out></option>
					</c:forEach>
				</select>
			</div>

<div id="aventuraAxB" hidden>
			<div class="col-md-4">
				<label  for="atraccion" class="form-label">Atraccion 1</label> 
				<select required class="form-select" id="atraccion1AxB"
					aria-label="Atraccion1">
					<option value="" selected>Escoge...</option>

					<c:forEach items="${listaAtracciones}" var="atraccion">
						<c:if test="${atraccion.tipo == 'AVENTURA'}">
						<option value="${atraccion.costo}"><c:out
						value="${atraccion.nombre}"></c:out></option>
						</c:if>
					</c:forEach>
				</select>
			</div>

			<div class="col-md-4">
				<label for="atraccion" class="form-label">Atraccion 2</label> 
				<select required class="form-select" id="atraccion2AxB"
					aria-label="Atraccion2">
					<option value="" selected>Escoge...</option>
					<c:forEach items="${listaAtracciones}" var="atraccion">
						<c:if test="${atraccion.tipo == 'AVENTURA'}">
						<option value="${atraccion.costo}"><c:out
						value="${atraccion.nombre}"></c:out></option>
						</c:if>
					</c:forEach>
				</select>
			</div>

			<div class="col-md-4">

				<label for="atraccion" class="form-label">Atraccion 3</label> <select
					required class="form-select" id="atraccion3AxB"
					aria-label="Atraccion3">
					<option value="" selected>Escoge...</option>
					
					<c:forEach items="${listaAtracciones}" var="atraccion">
						<c:if test="${atraccion.tipo == 'AVENTURA'}">
						<option value="${atraccion.costo}"><c:out
						value="${atraccion.nombre} ->Costo ${atraccion.costo}"></c:out></option>
						</c:if>
					</c:forEach>
				</select>
			</div>
</div>

<div id="paisajesAxB" hidden>
			<div class="col-md-4">
				<label  for="atraccion" class="form-label">Atraccion 1</label> 
				<select required class="form-select" id="atraccion1AxB"
					aria-label="Atraccion1">
					<option value="" selected>Escoge...</option>

					<c:forEach items="${listaAtracciones}" var="atraccion">
						<c:if test="${atraccion.tipo == 'PAISAJES'}">
						<option value="${atraccion.costo}"><c:out
						value="${atraccion.nombre}"></c:out></option>
						</c:if>
					</c:forEach>
				</select>
			</div>

			<div class="col-md-4">
				<label for="atraccion" class="form-label">Atraccion 2</label> 
				<select required class="form-select" id="atraccion2AxB"
					aria-label="Atraccion2">
					<option value="" selected>Escoge...</option>
					<c:forEach items="${listaAtracciones}" var="atraccion">
						<c:if test="${atraccion.tipo == 'PAISAJES'}">
						<option value="${atraccion.costo}"><c:out
						value="${atraccion.nombre}"></c:out></option>
						</c:if>
					</c:forEach>
				</select>
			</div>

			<div class="col-md-4">

				<label for="atraccion" class="form-label">Atraccion 3</label> <select
					required class="form-select" id="atraccion3AxB"
					aria-label="Atraccion3">
					<option value="" selected>Escoge...</option>
					
					<c:forEach items="${listaAtracciones}" var="atraccion">
						<c:if test="${atraccion.tipo == 'PAISAJES'}">
						<option value="${atraccion}"><c:out
						value="${atraccion.nombre} ->Costo ${atraccion.costo}"></c:out></option>
						</c:if>
					</c:forEach>
				</select>
			</div>
</div>

<div id="degustacionAxB" hidden>
			<div class="col-md-4">
				<label  for="atraccion" class="form-label">Atraccion 1</label> 
				<select required class="form-select" id="atraccion1AxB"
					aria-label="Atraccion1">
					<option value="" selected>Escoge...</option>

					<c:forEach items="${listaAtracciones}" var="atraccion">
						<c:if test="${atraccion.tipo == 'AVENTURA'}">
						<option value="${atraccion.costo}"><c:out
						value="${atraccion.nombre}"></c:out></option>
						</c:if>
					</c:forEach>
				</select>
			</div>

			<div class="col-md-4">
				<label for="atraccion" class="form-label">Atraccion 2</label> 
				<select required class="form-select" id="atraccion2AxB"
					aria-label="Atraccion2">
					<option value="" selected>Escoge...</option>
					<c:forEach items="${listaAtracciones}" var="atraccion">
						<c:if test="${atraccion.tipo == 'AVENTURA'}">
						<option value="${atraccion.costo}"><c:out
						value="${atraccion.nombre}"></c:out></option>
						</c:if>
					</c:forEach>
				</select>
			</div>

			<div class="col-md-4">

				<label for="atraccion" class="form-label">Atraccion 3</label> <select
					required class="form-select" id="atraccion3AxB"
					aria-label="Atraccion3">
					<option value="" selected>Escoge...</option>
					
					<c:forEach items="${listaAtracciones}" var="atraccion">
						<c:if test="${atraccion.tipo == 'AVENTURA'}">
						<option value="${atraccion.costo}"><c:out
						value="${atraccion.nombre} ->Costo ${atraccion.costo}"></c:out></option>
						</c:if>
					</c:forEach>
				</select>
			</div>
</div>

<div class="mb-3">
				<button type="submit" class="btn btn-primary">Agregar Promoción</button>
			</div>
		</form>
</body>
</html>