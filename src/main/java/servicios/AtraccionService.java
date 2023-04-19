package servicios;

import java.util.ArrayList;
import java.util.List;

import modelo.Atraccion;
import modelo.TipoAtraccion;
import persistencia.genericos.AtraccionDAO;
import persistencia.genericos.DAOFactory;

public class AtraccionService {

	public List<Atraccion> list() {
		return DAOFactory.getAtraccionDAO().buscarTodos();
	}

	public Atraccion crear(String nombre, TipoAtraccion tipo, int costo, double duracion, int cupo, String img,
			String descripcion) {

		Atraccion atraccion = new Atraccion(nombre, tipo, costo, duracion, cupo, img, descripcion);

		if (atraccion.isValid()) {
			AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
			atraccionDAO.insertar(atraccion);
		}

		return atraccion;
	}

	public Atraccion actualizar(long idAtraccion, String nombre, int costo, double duracion, int cupo,
			String descripcion) {

		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		Atraccion atraccion = atraccionDAO.buscarPorIdAtraccion(idAtraccion);

		atraccion.setNombre(nombre);
		atraccion.setCosto(costo);
		atraccion.setDuracion(duracion);
		atraccion.setCupo(cupo);
		atraccion.setDescripcion(descripcion);

		if (atraccion.isValid()) {
			atraccionDAO.actualizar(atraccion);
		}

		return atraccion;
	}

	public void borrar(Long idAtraccion) {
		Atraccion atraccion = new Atraccion(idAtraccion);

		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		atraccionDAO.borrarLogico(atraccion);
	}

	public Atraccion buscarId(long IdAtraccion) {
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		return atraccionDAO.buscarPorIdAtraccion(IdAtraccion);
	}

	public void activar(Long id) {
		Atraccion atraccion = new Atraccion(id);
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		atraccionDAO.activar(atraccion);
	}

	//agregado para promociones

	public ArrayList<Atraccion> listarAtracciones() {
		ArrayList<Atraccion> listaAtracciones = DAOFactory.getAtraccionDAO().buscarTodos();
		return listaAtracciones;
	}

	public ArrayList<String> listarTiposAtraccion() {
		ArrayList<String> listaTiposAtraccion = DAOFactory.getAtraccionDAO().listarTipos();
		return listaTiposAtraccion;
	}
}
