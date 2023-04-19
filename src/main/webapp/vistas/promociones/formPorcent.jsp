
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


			<c:set var="costoActivo" value="readonly"></c:set>
			<c:set var="descuentoActivo" value="readonly"></c:set>

			<div class="col-sm-1">
				<label for="formGroupExampleInput" class="form-label">ID</label>
				<input readonly required type="text" name="id" id="id" class="form-control" 
					placeholder="Nombre" value="${promocion.getIdProducto()}">
			</div>

			<div class="col-md-3">
				<label for="formGroupExampleInput" class="form-label">Nombre</label>
				<input required type="text" name="nombre" id="nombre" class="form-control" 
					placeholder="Nombre" value="${promocion.nombre}">
			</div>
			
			<div class="col-md-4">
				<label for="promocion" class="form-label">Clase de Promocion</label>
				<select disabled name="tipoPromo" required class="form-select"
					id="tipoPromocion"  aria-label="TipoPromocion">
					<c:choose>
						<c:when test="${promocion.getClass().name.equals('modelo.PromocionAxB')}">
							<c:set var="clase" value="Promocion AxB"></c:set>
						</c:when>
						<c:when test="${promocion.getClass().name.equals('modelo.PromocionAbsoluta')}">
							<c:set var="clase" value="Promocion Absoluta"></c:set>
							<c:set var="costoActivo" value=""></c:set>
						</c:when>
						<c:when test="${promocion.getClass().name.equals('modelo.PromoPorcentual')}">
							<c:set var="clase" value="Promocion Porcentual"></c:set>
							<c:set var="descuentoActivo" value=""></c:set>
						</c:when>
						</c:choose>
					
					<option value="" selected><c:out value="${clase}"></c:out></option>

					<c:forEach items="${tiposPromo}" var="tipoPromo" varStatus="myIndex">

						<option value="${myIndex.count}"><c:out value="${tipoPromo}"></c:out></option>
					</c:forEach>
				</select>
			</div>
			
			<div class="col-md-4">
				<label for="formGroupExampleInput" class="form-label">Tipo</label>
				<input readonly  min="0" required type="text" name="tipo" value="${promocion.tipo}" class="form-control" 
					id="tipo" placeholder="0" >
			</div>
					
		
		<div class="col-md-6">
				<label  class="form-label">Atraccion 1</label> 
				<select required class="form-select" id="atr1" name="atraccion1" aria-label="Atraccion1">
					<option value="${promocion.atraccionesIncluidas.get(0).idAtraccion}" selected>
					<c:out	value="${promocion.atraccionesIncluidas.get(0).nombre}"></c:out></option>
					<c:forEach items="${listaAtracciones}" var="atraccion" varStatus="myIndex">
						<c:if test="${atraccion.tipo.equals(promocion.tipo)}">
						<option value="${myIndex.count}"><c:out
						value="${myIndex.count}- ${atraccion.nombre} (${atraccion.tipo})"></c:out></option>
					</c:if>
					</c:forEach>
				</select>
			</div>

			<div class="col-md-6">
				<label  class="form-label">Atraccion 2</label> 
				<select required class="form-select" id="atr2" name="atraccion2" aria-label="Atraccion2">
					<option value="${promocion.atraccionesIncluidas.get(1).idAtraccion}" selected>
					<c:out	value="${promocion.atraccionesIncluidas.get(1).nombre}"></c:out></option>
					<c:forEach items="${listaAtracciones}" var="atraccion" varStatus="myIndex">
						<c:if test="${atraccion.tipo.equals(promocion.tipo)}">
						<option value="${myIndex.count}"><c:out
						value="${myIndex.count}- ${atraccion.nombre} (${atraccion.tipo})"></c:out></option>
					</c:if>
					</c:forEach>
				</select>
			</div>

					
			<div class="col-md-3">
				<label for="formGroupExampleInput" class="form-label">Costo</label>
				<input <c:out value="${costoActivo}"></c:out> type="number" min="0" required type="text" name="costo" value="${promocion.costo}" class="form-control" 
					id="costo" placeholder="0" >
			</div>
			
			<div class="col-md-3">
				<label for="formGroupExampleInput" class="form-label">Duracion</label>
				<input readonly type="number" min="0" required type="text" name="duracion" value="${promocion.duracion}" class="form-control" 
					id="costo" placeholder="0" >
			</div>
			
			<div class="col-md-3">
				<label for="formGroupExampleInput" class="form-label">Cupo</label>
				<input readonly type="number" min="0" required type="text" name="cupo" value="${promocion.cupo}" class="form-control" 
					id="cupo" placeholder="0" >
			</div>
			
			<div class="col-md-3">
				<label for="formGroupExampleInput" class="form-label">Descuento</label>
				<input <c:out value="${descuentoActivo}"></c:out> type="number" min="0" required type="text" name="descuento" value="${promocion.descuento}" class="form-control" 
					id="descuento" placeholder="0" >
			</div>
			
			<div>
				<button type="submit" class="btn btn-primary">Guardar</button>
				<a onclick="window.history.back();" class="btn btn-secondary"
				role="button">Cancelar</a>
			</div>
		
	</div>

</body>
</html>