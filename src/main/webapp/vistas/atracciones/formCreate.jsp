<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="modal-body">
	<div class="mb-3">
		<label for="nombre" class="col-form-label">Nombre:</label> <input
			type="text" class="form-control" id="name" name="nombre" required
			value="${atraccion.nombre}">
	</div>


	<!-- ALERTA PEGUE ALGO ACÁ -->
	<div class="mb-3">
		<label for="tipo" class="form-label">Tipo de atraccion:</label> <select
			class="form-select" name="tipo" aria-label="Default select example">
			<c:forEach items="${tiposAtracciones}" var="tipo">
				<option value="${tipo}">${tipo}</option>
			</c:forEach>
		</select>
	</div>


	<div class="mb-3">
		<label for="img"
			class='col-form-label ${atraccion.errors.get("img") != null ? "is-invalid" : "" }'>Imagen:</label>
		<input class="form-control" type="text" id="img" name="img" required
			value="${atraccion.img}"></input>
		<div class="invalid-feedback">
			<c:out value='${atraccion.errors.get("img")}'></c:out>
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
			<input type="text" class="form-control" id="descripcion"
				name="descripcion" required value="${atraccion.descripcion}">
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