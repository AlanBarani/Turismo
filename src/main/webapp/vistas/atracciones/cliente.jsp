<%@ page language="java" contentType="text/html; charset=Utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Tipo de atracciones</title>

<!-- font awesome cdn link -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

<!-- custom ccs file link -->
	<style>
     <%@ include file="../../css/atraccionesCliente.css"%>
</style>





</head>
<body>
<jsp:include page="/partials/navcliente.jsp"></jsp:include>


	<!-- seccion home empieza -->
<section class="home" id="home">
<main class="container">


			<h1>Atracciones de la Tierra Media</h1>


		<table class="content-table">
			<thead>
				<tr>
					<th>Atracciones</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${atracciones}" var="atraccion">
					<tr>
					<td><br><h3><c:out value="${atraccion.nombre}"></c:out></strong></h3></br>
					<img src="https://drive.google.com/uc?export=download&id=${atraccion.img}" alt="${atraccion.nombre}">
						
							<p>${atraccion.descripcion}</p></td>
						
						 
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
         <a href="https://github.com/ErCanaro" target="_blank" rel="noopener noreferrer">Enzo Bulacio</a>
         <a href="https://github.com/JosCorSgo" target="_blank" rel="noopener noreferrer">Jose Cordoba</a>
         <a href="https://github.com/PaulaSuescun" target="_blank" rel="noopener noreferrer">Paula Suescun</a>
         <a href="https://github.com/AlanBarani" target="_blank" rel="noopener noreferrer">Alan Barani</a>
         <a href="https://github.com/Mauricio12Sanchez" target="_blank" rel="noopener noreferrer">Mauro Sanchez</a>
         <a href="https://github.com/AvalosEmanuel" target="_blank" rel="noopener noreferrer">Ema Avalos</a>
         
      </div>
       
      <div class="box">
      
  
      <img src="<c:url value='../img/lecodefooter.png'/>" alt=.../>
      
      
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
	
	
	
	
	<!-- custom js file link -->
<script src="../js/scriptatraccionescliente.js"></script>
	
	
	
	
</body>
</html>