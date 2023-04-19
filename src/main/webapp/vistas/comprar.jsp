<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>comprar</title>
<link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css" />
<!-- font awesome cdn link -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<!-- custom ccs file link -->
    <style>
     <%@ include file="../css/comprar.css"%>
</style>
</head>
<body>
<jsp:include page="/partials/navcliente.jsp"></jsp:include>
	<h2>Pagina de compras de atracciones y promociones</h2>
	<div class="container">



		<!-- services section starts -->
		<section class="services" id="services">
			<h1 class="heading">
				<span>c</span> <span>o</span> <span>m</span> <span>p</span> <span>r</span>
				<span>a</span> <span>r</span>
			</h1>
			<div class="box-container">

				<div class="box">
					<i class="fas fa-money-bill-wave"></i>
					<h3>Presupuesto disponible</h3>
					<h1 class="card-text">${usuario.presupuesto }</h1>
					<a href="#" class="btn btn-primary">Comprar monedas</a>
				</div>

				<div class="box">
					<i class="fas fa-clock"></i>
					<h3>Tiempo disponible</h3>
					<h1 class="card-text">${usuario.tiempoDisponible }</h1>
					<a href="#" class="btn btn-primary">Modificar tiempo</a>
				</div>

				<div class="box">
					<i class="fas fa-star"></i>
					<h3>Atracciones preferidas</h3>
					<h1 class="card-text">${usuario.preferencia }</h1>
					<a href="#" class="btn btn-primary">Modificar preferencia</a>
				</div>
			</div>

		</section>
		<!-- services section ends -->


		<!-- seccion slider comienza -->
		<section class="gallery" id="gallery">
			<h1 class="heading">
				<span>o</span> <span>f</span> <span>e</span> <span>r</span> <span>t</span>
				<span>a</span> <span>d</span> <span>o</span> <span>r</span>
			</h1>


			<div class="swiper review-slider">

				<div class="swiper-wrapper">

					<div class="swiper-slide">

						<div class="box">
							<img src="img/bosque.jpg" alt="">
							<h3>Deslice el carrusel para comprar o hagalo desde la lista
								de abajo</h3>
						</div>

					</div>




					<c:forEach items="${listaProductos}" var="producto">
						<div class="swiper-slide">

							<div class="box">
								<img
									src="https://drive.google.com/uc?export=download&id=${producto.imgURL}"
									alt="${producto.nombre}">

								<h3>${producto.nombre}</h3>
								<p>${producto.descripcion}
								<p>
								<p>
								<h3>
									<span>${producto.costo}</span> monedas y <span>${producto.duracion}</span>
									horas de duracion
								</h3>
								<p>
									<a
										href="confirmarcompra.do?idp=${producto.idProducto }&idu=${usuario.idUsuario}&esp=${producto.esPromo()}"
										class="btn btn-primary"> Comprar </a>
							</div>


						</div>
					</c:forEach>

				</div>
			</div>


		</section>



		<!-- seccion slider termina  -->



		<section class="home" id="home">
			<div class="content">
				<h3>Historial de atracciones compradas</h3>
			</div>
			<main class="container">


				<div class="content-table">
					<!-- ----  HISTORIAL -------- -->
					<table class="table table-stripped table-hover">
						<thead>
							<tr>
								<th>Producto</th>
								<th>Tipo</th>
								<th>Descripcion</th>
								<th>Costo</th>
								<th>Duracion</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${usuario.getItinerario()}" var="item">
								<tr>
									<td><c:out value="${item.nombre}"></c:out></td>
									<c:if test="${item.esPromo() == true }">
										<td>Promoci&oacute;n</td>
									</c:if>
									<c:if test="${item.esPromo() == false }">
										<td>Atracci&oacute;n</td>
									</c:if>
									<c:if test="${item.esPromo() == true }">
										<td><c:out value="${item.generarDescripcion()}"></c:out></td>
									</c:if>
									<c:if test="${item.esPromo() == false }">
										<td><c:out value="${item.descripcion}"></c:out></td>
									</c:if>
									<td><c:out value="${item.costo}"></c:out></td>
									<td><c:out value="${item.duracion}"></c:out></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<!-- ----  FIN HISTORIAL-------- -->



				<div class="content">
					<h3>${listaProductos.size()}productos listados</h3>
				</div>
				<table class="content-table">
					<thead>
						<tr>
							<th>Producto</th>
							<th>Tipo</th>
							<th>Descripcion</th>
							<th>Costo</th>
							<th>Duracion</th>
							<th>Accion</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listaProductos}" var="producto">
							<tr>
								<td><c:out value="${producto.nombre}"></c:out></td>

								<c:if test="${producto.esPromo() == true }">
									<td>Promoci&oacute;n</td>
									<td><c:out value="${producto.generarDescripcion()}"></c:out></td>
								</c:if>

								<c:if test="${producto.esPromo() == false }">
									<td>Atracci&oacute;n</td>
									<td><c:out value="${producto.descripcion}"></c:out></td>
								</c:if>
								<td><c:out value="${producto.costo}"></c:out></td>
								<td><c:out value="${producto.duracion}"></c:out></td>
								<td><a
									href="confirmarcompra.do?idp=${producto.idProducto }&idu=${usuario.idUsuario}&esp=${producto.esPromo()} "
									class="btn btn-light rounded-0" role="button"><i
										class="bi bi-pencil-fill">Comprar</i></a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

			</main>
		</section>


		<!-- footer section starts -->
		<section class="footer">
			<div class="box-container">


				<div class="box">
					<h3>github</h3>
					<a href="https://github.com/ErCanaro" target="_blank"
						rel="noopener noreferrer">Enzo Bulacio</a> <a
						href="https://github.com/JosCorSgo" target="_blank"
						rel="noopener noreferrer">Jose Cordoba</a> <a
						href="https://github.com/PaulaSuescun" target="_blank"
						rel="noopener noreferrer">Paula Suescun</a> <a
						href="https://github.com/AlanBarani" target="_blank"
						rel="noopener noreferrer">Alan Barani</a> <a
						href="https://github.com/Mauricio12Sanchez" target="_blank"
						rel="noopener noreferrer">Mauro Sanchez</a> <a
						href="https://github.com/AvalosEmanuel" target="_blank"
						rel="noopener noreferrer">Ema Avalos</a>

				</div>

				<div class="box">



					<img src="<c:url value='img/lecodefooter.png'/>" alt=... />

				</div>

				<div class="box">
					<h3>linkedin</h3>
					<a
						href="https://www.linkedin.com/in/alan-jes%C3%BAs-barani-9a021a227"
						target="_blank" rel="noopener noreferrer">Alan Barani</a> <a
						href="https://www.linkedin.com/in/alan-jes%C3%BAs-barani-9a021a227"
						target="_blank" rel="noopener noreferrer">Ema Avalos</a> <a
						href="#">Paula Suescun</a> <a href="#">Enzo Bulacio</a> <a
						href="#">Jose Cordoba</a> <a href="#">Mauro Sanchez</a>
				</div>

			</div>

			<h1 class="credit">
				creado por <span>Le Code</span>
			</h1>
		</section>
		<!-- footer section ends -->


		<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>

		<!-- custom js file link -->
		<script src="js/comprar2.js"></script>
</body>
</html>