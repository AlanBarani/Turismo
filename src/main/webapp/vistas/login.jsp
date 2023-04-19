<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<script defer src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
	<script defer src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
<title>Login</title>	
</head>
<body>
	<div class="container " >
		<div class= "row justify-content-center text-center weigth= 400" >
			<div class="col-4 ">
			<h2>Login</h2>
			<form action="//localhost:8080/AppWebTierraMedia/login" method="post" >
			  <div class="mb-3">
			    <label for="inputEmail" class="form-label">Email</label>
			    <input type="email" class="form-control" id="exampleInputEmail1" name="email"  aria-describedby="emailHelp">
			  </div>
			  
			  <div class="mb-3">
			    <label for="inputPassword" class="form-label">Password</label>
			    <input type="password" class="form-control" id="inputPassword" name="password" >
			  </div>
  
			  <button type="submit" class="btn btn-primary">Ingresar</button>
			  <br><br>
			  <a href="clientes.jsp">Ingresar como invitado</a>
			</form>
			<% String flash = (String) request.getAttribute("flash"); %>
			<% if (flash != null) {%>
			<%= flash%>
			<% }%>			
				
			</div>

		</div>
	</div>

</body>
</html>
