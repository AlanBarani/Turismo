<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">
<head>


	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Promociones</title>

<!-- font awesome cdn link -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

<!-- custom ccs file link -->
	<style>
     <%@ include file="../../css/promociones.css"%>
</style>






</head>
<body>
<jsp:include page="/partials/navadmin.jsp"></jsp:include>

<!-- seccion home empieza -->
<section class="home" id="home">

	<!-- <main class="container">-->
	<div class="top-row" id="top-row">


     <div class="content">
         <h3>Listado de promociones</h3>


     </div>

		<c:if test="${usr.esAdmin == true}">
			<div class="mb-3">
				<a href="/AppWebTierraMedia/promociones/crear.do" class="btn btn-primary"
					role="button"> <i class="fas fa-plus"></i>Agregar Promocion
				</a>
			</div>
		</c:if>
		
		
		
		
		
<table class="content-table">
			<thead>
				<tr>
					<th>ID</th>
					<th>Nombre</th>
					<th>Precio</th>
					<th>Duracion</th>
					<th>Tipo</th>
					<th>Estado</th>
					<th>Accion</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${promociones}" var="promociones">
					<tr>
						<td><c:out value="${promociones.idProducto}"></c:out></td>
						<td><strong><c:out value="${promociones.nombre}"></c:out></strong></td>
						<td><c:out value="${promociones.costo}"></c:out></td>
						<td><c:out value="${promociones.duracion}"></c:out></td>
						<td><c:out value="${promociones.tipo}"></c:out></td>
						<c:if test="${!promociones.estaActivo()}">
							<td><span>Inactivo</span></td>
							<td><a
								href="/AppWebTierraMedia/promociones/activar.do?id=${promociones.idProducto}"
								class="btn-e" role="button">Activar</a></td>
						</c:if>
						<c:if test="${promociones.estaActivo()}">
							<td><span>Activo</span></td>
							<td><a
								href="/AppWebTierraMedia/promociones/borrar.do?id=${promociones.idProducto}"
								class="btn-a" role="button"><i class="fas fa-times-circle"></i>Desactivar</a></td>
						</c:if>
						<td><c:if test="${usr.esAdmin == true}">
								<c:choose>
									<c:when
										test="${promociones.getClass().name.equals('modelo.PromocionAxB')}">
										<c:set var="href"
											value="/AppWebTierraMedia/promociones/editarAxB.do?id=${promociones.idProducto}"></c:set>
									</c:when>
									<c:when
										test="${promociones.getClass().name.equals('modelo.PromocionAbsoluta')}">
										<c:set var="href"
											value="/AppWebTierraMedia/promociones/editarAbsol.do?id=${promociones.idProducto}"></c:set>
									</c:when>
									<c:when
										test="${promociones.getClass().name.equals('modelo.PromoPorcentual')}">
										<c:set var="href"
											value="/AppWebTierraMedia/promociones/editarPorcent.do?id=${promociones.idProducto}"></c:set>
									</c:when>
								</c:choose>
								<a href="${href}" class="btn btn-light rounded-0" role="button"><i
									class="bi bi-pencil-fill">Editar</i></a>
							</c:if></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
</div>
	<!--</main>-->
	
	</section>
	
	
	
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



	

</body>
</html>