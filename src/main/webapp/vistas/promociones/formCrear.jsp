<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<script>
		function disable() {

			var x = document.getElementById("tipoPromocion").value;			
			
			if (x != '1') {
				document.getElementById("atr3").setAttribute("disabled", "true");
			} else {
				document.getElementById("atr3").removeAttribute("disabled");
			}

			if (x != '3') {
				document.getElementById("costo").setAttribute("readonly","true");
			} else {
				document.getElementById("costo").removeAttribute("readonly")
			}

			if (x != '2') {
				document.getElementById("descuento").setAttribute("readonly","true");
			} else {
				document.getElementById("descuento").removeAttribute("readonly")
			}
		}
	</script>


<div class="row g-3 modal-body">
			
			
			<div class="col-sm-1">
				<label for="formGroupExampleInput" class="form-label">ID</label>
				<input readonly required type="text" name="id" id="id" class="form-control" 
					placeholder="ID" value="">
			</div>
			
			<div class="col-md-3">
				<label for="formGroupExampleInput" class="form-label">Nombre</label>
				<input required type="text" name="nombre" id="nombre" class="form-control" 
					placeholder="Nombre" value="">
			</div>
			
			<div class="col-md-4">
				<label for="promocion" class="form-label">Clase de Promocion</label>
				<select name="tipoPromo"  onchange="disable()" required class="form-select"
					id="tipoPromocion"  aria-label="TipoPromocion">
					<option value="" selected>Escoge...</option>

					<c:forEach items="${tiposPromo}" var="tipoPromo" varStatus="myIndex">

						<option value="${myIndex.count}"><c:out value="${tipoPromo}"></c:out></option>
					</c:forEach>
				</select>
			</div>
			
		<div class="col-md-4">
				<label for="horario" class="form-label">Tipo de Atracciones</label>
				<select required class="form-select" name="tiposAtraccion" aria-label="TipoAtraccion">
				<option value="0" selected>Escoge...</option>
				<c:forEach items="${listaTiposAtraccion}" var="tipoAtraccion" varStatus="myIndex">
					<option value="${tipoAtraccion}"><c:out
							value="${myIndex.count} -${tipoAtraccion}"></c:out></option>
				</c:forEach>
				</select>
		</div>
		
		<div class="col-md-4">
				<label  class="form-label">Atraccion 1</label> 
				<select required class="form-select" id="atr1" name="atraccion1" aria-label="Atraccion1">
					<option value="0" selected>Escoge...</option>

					<c:forEach items="${listaAtracciones}" var="atraccion" varStatus="myIndex">
						<option value="${atraccion.getIdAtraccion()}"><c:out
						value="${atraccion.getIdAtraccion()}- ${atraccion.nombre} (${atraccion.tipo})"></c:out></option>
					</c:forEach>
				</select>
			</div>

			<div class="col-md-4">
				<label  class="form-label">Atraccion 2</label> 
				<select required class="form-select" id="atr2" name="atraccion2" aria-label="Atraccion2">
					<option value="0" selected>Escoge...</option>
					<c:forEach items="${listaAtracciones}" var="atraccion" varStatus="myIndex">
						<option value="${atraccion.getIdAtraccion()}"><c:out
						value="${atraccion.getIdAtraccion()}- ${atraccion.nombre} (${atraccion.tipo})"></c:out></option>
					</c:forEach>
				</select>
			</div>

			<div class="col-md-4">
				<label  class="form-label">Atraccion 3</label> <select
					required class="form-select" id="atr3" name="atraccion3" aria-label="Atraccion3">
					<option value="0" selected>Escoge Carajo...</option>
					
					<c:forEach items="${listaAtracciones}" var="atraccion" varStatus="myIndex">
						<option value="${atraccion.getIdAtraccion()}"><c:out
						value="${atraccion.getIdAtraccion()}- ${atraccion.nombre} (${atraccion.tipo})"></c:out></option>
					</c:forEach>
				</select>
			</div>
			
			<div class="col-md-3">
				<label for="formGroupExampleInput" class="form-label">Costo</label>
				<input type="number" min="0" required name="costo" value="0" class="form-control" 
					id="costo" placeholder="0" >
			</div>
			
			<div class="col-md-3">
				<label for="formGroupExampleInput" class="form-label">Descuento</label>
				<input type="number" min="0" required type="text" name="descuento" value="0"  
				id="descuento" class="form-control"	 placeholder="0">
			</div>
		
			<div class="col-md-3">
				<label for="formGroupExampleInput" class="form-label">Duracion</label>
				<input type="number" min="0" required type="text" name="duracion" readonly
				 id="duracion" value="0" class="form-control" placeholder="0" >
			</div>
			
			<div class="col-md-3">
				<label for="formGroupExampleInput" class="form-label">Cupo</label>
				<input type="number" min="0" required type="text" name="cupo" readonly
				id="cupo" value="0" class="form-control" placeholder="0">
			</div>
		
			<div>
				<button type="submit" class="btn btn-primary">Guardar</button>
				<a onclick="window.history.back();" class="btn btn-secondary"
				role="button">Cancelar</a>
			</div>
		
	</div>



</body>
</html>