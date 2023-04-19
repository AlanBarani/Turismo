package servicios;

import java.util.ArrayList;
import java.util.Map;

import modelo.Posicion;
import modelo.TipoAtraccion;
import modelo.Usuario;
import persistencia.genericos.DAOFactory;
import persistencia.genericos.UsuarioDAO;


public class UsuarioService {
 	
 	public ArrayList<Usuario> listarTodos() {
 		UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
 	 	ArrayList<Usuario> listaUsuarios =  usuarioDAO.buscarTodos();
    	return listaUsuarios;
	}
	
 	public Usuario login(String email, String password) {
 		UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
     	Usuario usuario =  usuarioDAO.buscarPorEmailyPassword(email, password);
    	return usuario;
	}
 	
 	public Usuario buscarID(long id) {
 		UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
     	Usuario usuario =  usuarioDAO.buscarPorIdUsuario(id);
    	return usuario;
 	}
 	
 	public Usuario actualizar(Long id, String nombre, String email, String pass, 
 			int presupuesto, double tiempo , TipoAtraccion preferencia , 
 			Boolean estaActivo, Boolean esAdmin, Posicion pos) {

		UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
		Usuario usuario = usuarioDAO.buscarPorIdUsuario(id);

		usuario.setNombre(nombre);
		usuario.setEmail(email);
		usuario.setPassword(pass);
		usuario.setPresupuesto(presupuesto);
		usuario.setTiempoDisponible(tiempo);
		usuario.setPreferencia(preferencia);
		usuario.setEstaActivo(estaActivo);
		usuario.setEsAdmin(esAdmin);
		
		if (usuario.esValido()) {
			usuarioDAO.actualizar(usuario);
		}
		return usuario;
	}

 	public int desactivar(Long id) {
		UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
		int resultado = usuarioDAO.desactivar(id);
		return resultado;
	}
 	
 	public Usuario crear(String nombre, String email, String pass, 
 			int presupuesto, double tiempo , TipoAtraccion preferencia , 
 			Boolean estaActivo, Boolean esAdmin, Posicion pos) {

		UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
		Usuario usuario = new Usuario();
		
		usuario.setNombre(nombre);
		usuario.setEmail(email);
		usuario.setPassword(pass);
		usuario.setPresupuesto(presupuesto);
		usuario.setTiempoDisponible(tiempo);
		usuario.setPreferencia(preferencia);
		usuario.setEstaActivo(estaActivo);
		usuario.setEsAdmin(esAdmin);
		usuario.setPosicion(pos);
		
		if (usuario.esValido()) {
			usuarioDAO.insertar(usuario);
		}
		return usuario;
	}

	public String erroresUsuario(Usuario usuario) {
		Map<String, String> mapa = usuario.getErrors();
		String cadena = "",error = "";
		for (String clave:mapa.keySet()) {
		    error = mapa.get(clave);
		    cadena = cadena  +  (" - campo "+clave + ": " + error);
		}
		return cadena;
	}
 	
 	
}
