package servicios;

import java.util.ArrayList;

import modelo.Producto;
import persistencia.genericos.DAOFactory;
import persistencia.genericos.ItinerarioDAO;

public class ItinerarioService {

	public ArrayList<Producto> listar(Long id) {
		ItinerarioDAO itinerarioDAO = DAOFactory.getItinerarioDAO();
		return itinerarioDAO.itinerarioDelUsuarioPorId(id);
	}

}
