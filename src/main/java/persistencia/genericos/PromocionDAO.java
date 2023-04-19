package persistencia.genericos;

import java.util.ArrayList;

import modelo.Producto;

public interface PromocionDAO extends GenericDAO <Producto>{
	
	public abstract Producto buscarPorIdPromocion(Long id);

	public abstract ArrayList<String> listarTiposPromocion();

	public abstract int activar(Long idPromocion);

	public abstract int borrarLogico(Long idPromocion);

}
