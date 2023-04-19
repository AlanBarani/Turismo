package persistencia.genericos;

import persistencia.implementaciones.AtraccionDAOImpl;
import persistencia.implementaciones.ItinerarioDAOImpl;
import persistencia.implementaciones.PromocionDAOImpl;
import persistencia.implementaciones.TipoAtraccionDAOImpl;
import persistencia.implementaciones.UsuarioDAOImpl;

public class DAOFactory {

	public static UsuarioDAO getUsuarioDAO() {
		return new UsuarioDAOImpl();
	}

	public static AtraccionDAO getAtraccionDAO() {
		return new AtraccionDAOImpl();
	}

	public static PromocionDAO getPromocionDAO() {
		return new PromocionDAOImpl();
	}

	public static ItinerarioDAO getItinerarioDAO() {
		return new ItinerarioDAOImpl();
	}

	
	public static TipoAtraccionDAO getTipoAtraccionDAO() {
		return new TipoAtraccionDAOImpl();
	}
	
	

}