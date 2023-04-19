package servicios;

import java.util.HashMap;
import java.util.Map;

import modelo.Atraccion;
import modelo.Usuario;
import persistencia.genericos.AtraccionDAO;
import persistencia.genericos.DAOFactory;
import persistencia.genericos.UsuarioDAO;

public class ComprarAtraccionService {

	AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
	UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();

	public Map<String, String> buy(Long usuarioId, Long atraccionId) {
		Map<String, String> errors = new HashMap<String, String>();

		Usuario usuario = usuarioDAO.buscarPorIdUsuario(usuarioId);
		Atraccion atraccion = atraccionDAO.buscarPorIdAtraccion(atraccionId);

		if (!atraccion.canHost(1)) {
			errors.put("atraccion", "No hay cupo disponible");
		}
		if (!usuario.puedePermitirse(atraccion)) {
			errors.put("usuario", "No tienes dinero suficiente");
		}
		if (!usuario.puedeAsistir(atraccion)) {
			errors.put("usuario", "No tienes tiempo suficiente");
		}

		if (errors.isEmpty()) {
			usuario.agregarAlItinerario(atraccion);
			atraccion.host(1);
			atraccionDAO.actualizar(atraccion);
			usuarioDAO.actualizar(usuario);
		}

		return errors;

	}

}
