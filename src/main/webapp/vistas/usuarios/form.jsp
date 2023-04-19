<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="modal-body">
	<div class="mb-3">
		<label for="nombre" class="col-form-label">Nombre:</label> 
		<input type="text" class="form-control" id="nombre" name="nombre" required value="${usuario.nombre}">
	</div>
	
	<div class="mb-3">
		<label for="email" class="col-form-label">Email:</label> 
		<input type="text" class="form-control" id="email" name="email" required value="${usuario.email}">
	</div>
	
	<div class="mb-3">
		<label for="pass" class="col-form-label">Password:</label> 
		<input type="text" class="form-control" id="pass" name="pass" required value="${usuario.password}">
	</div>	
	
	<div class="mb-3">
		<label for="presupuesto" class="col-form-label">Presupuesto:</label> 
		<input type="text" class="form-control" id="presupuesto" name="presupuesto" required value="${usuario.presupuesto}">
	</div>

	<div class="mb-3">
		<label for="tiempo" class="col-form-label">Tiempo disponible:</label> 
		<input type="text" class="form-control" id="tiempo" name="tiempo" required value="${usuario.tiempoDisponible}">
	</div>
	
	<div class="mb-3">
		<label for="preferencia" class="col-form-label">Preferencia:</label> 
		<select class="form-select" aria-label="Default select example" name="preferencia">
			<c:forEach items="${tiposAtracciones}" var="tipo">
				<option value="${tipo}" ${tipo == usuario.preferencia ? selected}>${tipo}</option>
			</c:forEach>
		</select> 
	</div>
	
	<div class="mb-3">
		<div class="form-check">
			<input class="form-check-input" type="radio" name="estaActivo" id="estaActivo1" value="true" checked> 
			<label class="form-check-label" for="estaActivo1">Activo</label>
		</div>
		<div class="form-check">
			<input class="form-check-input" type="radio" name="estaActivo" id="estaActivo2" value="false"> 
			<label class="form-check-label" for="estaActivo2">Inactivo</label>
		</div>
	</div>
	
	<div class="mb-3">
		<div class="form-check">
			<input class="form-check-input" type="radio" name="esAdmin"	id="esAdmin1" value="true" checked> 
			<label class="form-check-label" for="esAdmin1">Es administrador</label>
		</div>
		<div class="form-check">
			<input class="form-check-input" type="radio" name="esAdmin" id="esAdmin2" value="false" > 
			<label class="form-check-label" for="esAdmin2">No es administrador</label>
		</div>
	</div>
	
	<div>
		<button type="submit" class="btn btn-primary">Guardar</button>
		<a onclick="window.history.back();" class="btn btn-secondary"
			role="button">Cancelar</a>
	</div>

</div>
<!-- 	
		private Long IdUsuario;
		private String nombre;
		private int presupuesto;
		private double tiempoDisponible;
		private TipoAtraccion preferencia;
		private Posicion posicion;
		private String email;
		private String password;
		private boolean estaActivo;
		private boolean esAdmin;	
-->	
