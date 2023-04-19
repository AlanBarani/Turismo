package persistencia.genericos;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.TipoAtraccion;

public interface TipoAtraccionDAO extends GenericDAO<TipoAtraccion> {


	public abstract int activar(TipoAtraccion t);

	public abstract TipoAtraccion toTipoAtraccion(ResultSet resultados) throws SQLException;

	public abstract ArrayList<TipoAtraccion> buscarActivos();

	public abstract ArrayList<TipoAtraccion> buscarInactivos();

}
