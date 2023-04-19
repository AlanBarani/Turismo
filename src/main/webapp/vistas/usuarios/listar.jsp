<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">
<head>


	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>listar</title>

<!-- font awesome cdn link -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

<!-- custom ccs file link -->
	<style>
     <%@ include file="../../css/listar.css"%>
</style>




</head>
<body>
<jsp:include page="/partials/navadmin.jsp"></jsp:include>
<!-- termina seccion header -->


<!-- seccion home empieza -->
<section class="home" id="home">



	<div class="top-row" id="top-row">

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

		<c:if test="${usr.esAdmin == true}">
			<div class="mb-3">
				<a href="crearusuario.do" class="btn btn-primary"
					role="button"> <i class="fas fa-plus"></i>Agregar usuario
				</a>
			</div>
		</c:if>
		</div>

		<table class="content-table">
			<thead>
				<tr>
					<th>ID</th>
					<th>Nombre</th>
					<th>Dinero</th>
					<th>Tiempo</th>
					<th>Preferencia</th>
					<th>Estado</th>
					<th>Rol</th>
					<th>Accion</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<c:forEach items="${listaUsuarios}" var="usuario">
					<!-- <tr>-->
						<td><c:out value="${usuario.idUsuario}"></c:out></td>
						<td><strong><c:out value="${usuario.nombre}"></c:out></strong></td>
						<td><c:out value="${usuario.presupuesto}"></c:out></td>
						<td><c:out value="${usuario.tiempoDisponible}"></c:out></td>
						<td><c:out value="${usuario.preferencia}"></c:out></td>
						<c:if test="${usuario.estaActivo == true }">
							<td><span class="bg-green" >Activo</span></td>
						</c:if>
						<c:if test="${usuario.estaActivo == false }">
							<td><span class="bg-red" >Inactivo</span></td>
						</c:if>
						<c:if test="${usuario.esAdmin == true }">
							<td><span class="bg-greee" >Administrador</span></td>
						</c:if>
						<c:if test="${usuario.esAdmin == false }">
							<td><span class="bg-red" >Cliente</span></td>
						</c:if>

						<td><c:if test="${usr.esAdmin == true}">
								<a href="editarusuario.do?id=${usuario.idUsuario}" 	class="btn-e" role="button"><i class="fas fa-pencil-alt">Editar</i></a>
								<c:if test="${usuario.estaActivo == true }">
									<a href="desactivarusuario.do?id=${usuario.idUsuario}"
										class="btn-a" role="button"><i
										class="fas fa-times-circle">Desactivar</i></a>
								</c:if>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	<!-- </div> -->
	

</section>
<!-- seccion home termina -->
	
	
<!-- footer section starts -->

<section class="footer">
   <div class="box-container">
      
      
       <div class="box">
         <h3>github</h3>
         <a href="https://github.com/ErCanaro" target="_blank" rel="noopener noreferrer">Enzo Bulacio</a>
         <a href="https://github.com/JosCorSgo" target="_blank" rel="noopener noreferrer">Jose Cordoba</a>
         <a href="https://github.com/PaulaSuescun" target="_blank" rel="noopener noreferrer">Paula Suescun</a>
         <a href="https://github.com/AlanBarani" target="_blank" rel="noopener noreferrer">Alan Barani</a>
         <a href="https://github.com/Mauricio12Sanchez" target="_blank" rel="noopener noreferrer">Mauro Sanchez</a>
         <a href="https://github.com/AvalosEmanuel" target="_blank" rel="noopener noreferrer">Ema Avalos</a>
         
      </div>
         
      <div class="box">
      
  
      <img src="<c:url value='img/lecodefooter.png'/>" alt=.../>
      
      
      </div>
      
       <div class="box">
         <h3>linkedin</h3>
         <a href="https://www.linkedin.com/in/alan-jes%C3%BAs-barani-9a021a227" target="_blank" rel="noopener noreferrer">Alan Barani</a>
         <a href="https://www.linkedin.com/in/alan-jes%C3%BAs-barani-9a021a227" target="_blank" rel="noopener noreferrer">Ema Avalos</a>
         <a href="#">Paula Suescun</a>
         <a href="#">Enzo Bulacio</a>
         <a href="#">Jose Cordoba</a>
         <a href="#">Mauro Sanchez</a>
      </div>
      
   </div>
   
   <h1 class="credit">creado por <span>Le Code</span></h1>

</section>


<!-- footer section ends -->




<!-- termina seccion header -->

<!-- custom js file link -->
<script src="../js/scriptadministradores.js"></script>	


</body>
</html>