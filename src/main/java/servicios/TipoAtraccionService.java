package servicios;

import java.util.List;

import modelo.TipoAtraccion;
import persistencia.genericos.DAOFactory;
import persistencia.genericos.TipoAtraccionDAO;

public class TipoAtraccionService {

	public List<TipoAtraccion> listar() {
		return DAOFactory.getTipoAtraccionDAO().buscarTodos();
	}

	public void darDeBaja(TipoAtraccion tipo) {
		TipoAtraccionDAO DAO = DAOFactory.getTipoAtraccionDAO();
		DAO.borrar(tipo);
	}

	public void darDeAlta(TipoAtraccion tipo) {
		TipoAtraccionDAO DAO = DAOFactory.getTipoAtraccionDAO();
		DAO.activar(tipo);
	}

}
