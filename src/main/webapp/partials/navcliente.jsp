<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- comienza seccion header -->

<header>
    <div id="menu-bar" class="fas fa-bars"></div>

    <a href="http://localhost:8080/AppWebTierraMedia/vistas/clientes.jsp" class="logo"><span>P</span>anel de Clientes</a>
    

    <nav class="navbar">   
    <a class="nav-link" href="http://localhost:8080/AppWebTierraMedia/atracciones/cliente.do">Atracciones</a>  
     <a class="nav-link" href="http://localhost:8080/AppWebTierraMedia/comprar.do?id=${usr.idUsuario}">Comprar</a>  
     <!--   <a class="nav-link " aria-current="page" href="http://localhost:8080/AppWebTierraMedia/itinerario.do?id=${usr.idUsuario}">Ver mi itinerario</a> --> 

</nav>

   <div class= "icons">
      <i class="fas fa-user" id="login-btn"></i>
   </div>

</header>

<!-- termina seccion header -->

<!-- login form container -->

<div class="login-form-container">

   <i class="fas fa-times" id="form-close"></i>

   <form action="">
   <h3>Usuario</h3>
   
	<c:choose>
		    <c:when test="${usuario == 'Invitado'}">
		          ${usuario}
		          <a class="btn" href="login.jsp">Iniciar sesion</a>
		          			
		         </c:when>
		         <c:otherwise>

		            <p>Nombre: ${usr.nombre}</p>
		             <p>Preferencia: ${usr.preferencia}</p>
		          	<p>Presupuesto: ${usr.presupuesto}</p>
		          	<p>Tiempo: ${usr.tiempoDisponible}</p>
		          	<a class="btn" href="http://localhost:8080/AppWebTierraMedia/logout">Cerrar sesion</a>
		          </c:otherwise>
		          		  
		        </c:choose>
   

   </form>

</div>