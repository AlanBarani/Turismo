<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    

<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Principal</title>

<!-- font awesome cdn link -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

<!-- custom ccs file link -->
<style>
     <%@ include file="../css/clientes.css"%>
</style>



</head>
<body>

<% String usuario = "Invitado"; %>
<% if (session.getAttribute("nombreUsuario") != null) {%>
<%     usuario = (String)session.getAttribute("nombreUsuario");} %>
<% pageContext.setAttribute("usuario", usuario);%>
<% pageContext.setAttribute("usr", session.getAttribute("usr"));%>

<jsp:include page="/partials/navcliente.jsp"></jsp:include>

<!-- seccion home empieza -->
<section class="home" id="home">


</section>
<!-- seccion home termina -->
	
	
<!-- services section starts -->

<section class="services" id="services">
   <h1 class="heading">
   <span>s</span>
   <span>e</span>
   <span>r</span>
   <span>v</span>
   <span>i</span>
   <span>c</span>
   <span>i</span>
   <span>o</span>
   <span>s</span>
</h1>

  <div class="box-container">
      
      <div class="box">
         <i class="fas fa-hiking"></i>
         <h3><a href="../atracciones/cliente.do">atracciones</a></h3>
         <p>lore ipsum</p>
      </div>

      
      <div class="box">
         <i class="fas fa-shopping-cart"></i>
         <h3><a href="../comprar.do?id=${usr.idUsuario}">comprar</a></h3>
         <p>lore ipsum</p>
      </div>
      
<!--  <div class="box">
         <i class="fas fa-globe-asia"></i>
         <h3><a href="../itinerario.do">itinerario</a></h3>
         <p>lore ipsum</p>
      </div>
   </div>
-->
  
</section>

<!-- services section ends -->


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
      
  
      <img src="../img/lecodefooter.png" />
      
      
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
<script src="../js/scriptclientes.js"></script>

</body>
</html>