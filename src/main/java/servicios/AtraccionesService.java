package servicios;

import java.util.ArrayList;

import modelo.Atraccion;
import modelo.TipoAtraccion;
import persistencia.genericos.AtraccionDAO;
import persistencia.genericos.DAOFactory;

public class AtraccionesService {
	
	public ArrayList<String> listarTiposAtraccion() {
		ArrayList<String> listaTiposAtraccion = DAOFactory.getAtraccionDAO().listarTipos();
		return listaTiposAtraccion;
	}
	
	public ArrayList<Atraccion> listarAtracciones() {
		ArrayList<Atraccion> listaAtracciones = DAOFactory.getAtraccionDAO().buscarTodos();
		return listaAtracciones;
	}

	public Atraccion crear(String nombre, TipoAtraccion tipo, int costo, double duracion, int cupo, String img, String descripcion) {
		Atraccion atraccion = new Atraccion(nombre, tipo, costo, duracion, cupo, img, descripcion);
		
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		atraccionDAO.insertar(atraccion);
		
		return atraccion;
	}
}
