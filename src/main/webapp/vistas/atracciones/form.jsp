<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="modal-body">
<div class="mb-3">


<div class="mb-3">
		<label for="idAtraccion"
			class='col-form-label ${atraccion.errors.get("idAtraccion") != null ? "is-invalid" : "" }'>IdAtraccion:</label>
		<input class="form-control" type="number" id="idAtraccion"  readonly name="idAtraccion" 
			required value="${atraccion.idAtraccion}" ></input>
		<div class="invalid-feedback">
			<c:out value='${atraccion.errors.get("idAtraccion")}'></c:out>
		</div>
	</div>

		<label for="nombre" class="col-form-label">Nombre:</label> <input
			type="text" class="form-control" id="name" name="nombre" required
			value="${atraccion.nombre}">
	</div>

   <div class="mb-3">
		<label for="tipo"
			class='col-form-label ${atraccion.errors.get("tipo") != null ? "is-invalid" : "" }'>Tipo:</label>
		<input class="form-control" type="text" id="cost" readonly name="tipo"
			required value="${atraccion.tipo}"></input>
		<div class="invalid-feedback">
			<c:out value='${atraccion.errors.get("tipo")}'></c:out>
		</div>

	<div class="mb-3">
		<label for="costo"
			class='col-form-label ${atraccion.errors.get("costo") != null ? "is-invalid" : "" }'>Costo:</label>
		<input class="form-control" type="number" id="cost" name="costo"
			required value="${atraccion.costo}"></input>
		<div class="invalid-feedback">
			<c:out value='${atraccion.errors.get("costo")}'></c:out>
		</div>
	</div>

	<div class="mb-3">
		<label for="duracion"
			class='col-form-label ${atraccion.errors.get("duracion") != null ? "is-invalid" : "" }'>Duración:</label>
		<input class="form-control" type="number" id="duracion"
			name="duracion" required value="${atraccion.duracion}"></input>
		<div class="invalid-feedback">
			<c:out value='${atraccion.errors.get("duracion")}'></c:out>
		</div>
	</div>

	<div class="mb-3">
		<label for="cupo"
			class='col-form-label ${atraccion.errors.get("cupo") != null ? "is-invalid" : "" }'>Cupo:</label>
		<input class="form-control" type="number" id="cupo" name="cupo"
			required value="${atraccion.cupo}"></input>
		<div class="invalid-feedback">
			<c:out value='${atraccion.errors.get("cupo")}'></c:out>
		</div>
	</div>

<div class="mb-3">
<label for="descripcion" class="col-form-label">Descripcion:</label>
<input type="text" class="form-control" id="descripcion" name="descripcion" required
	value="${atraccion.descripcion}">
	<div class="invalid-feedback">
			<c:out value='${atraccion.errors.get("descripcion")}'></c:out>
		</div>
</div>


</div>

<div>
	<button type="submit" class="btn btn-primary">Guardar</button>
	<a onclick="window.history.back();" class="btn btn-secondary"
		role="button">Cancelar</a>
</div>
