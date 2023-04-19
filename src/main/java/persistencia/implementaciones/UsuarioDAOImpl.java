package persistencia.implementaciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Posicion;
import modelo.TipoAtraccion;
import modelo.Usuario;
import persistencia.genericos.DAOFactory;
import persistencia.genericos.ItinerarioDAO;
import persistencia.genericos.MissingDataException;
import persistencia.genericos.ProveedorDeConeccion;
import persistencia.genericos.UsuarioDAO;

public class UsuarioDAOImpl implements UsuarioDAO {

	public int insertar(Usuario usuario) {
		
		try {
			String sql = "INSERT INTO USUARIOS (NOMBRE, DINERO, TIEMPO, POSX, POSY, EMAIL, PASS, ESTADO, ESADMIN, ID_TIPO_ATRACCION) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			Connection conn = ProveedorDeConeccion.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, usuario.getNombre());
			statement.setInt(2, usuario.getPresupuesto());
			statement.setDouble(3, usuario.getTiempoDisponible());
			statement.setDouble(4, usuario.getPosX()); 
			statement.setDouble(5, usuario.getPosY()); 
			statement.setString(6, usuario.getEmail());
			statement.setString(7, usuario.getPassword());
			statement.setInt(8, convertirBooleano(usuario.getEstaActivo()));
			statement.setInt(9, convertirBooleano(usuario.getEsAdmin()));
			statement.setLong(10, obtegerIdTipoAtraccion(usuario)); 
			int rows = statement.executeUpdate();

			statement.close();
			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
		
	}


	private long obtegerIdTipoAtraccion(Usuario usuario) {
		long id = 0;
		TipoAtraccion preferencia =  usuario.getPreferencia();
		switch(preferencia) {
			case AVENTURA:    id = 1; 	break;
			case PAISAJES:    id = 2; 	break;
			case DEGUSTACION: id = 3; 	break;
		}
		return id;
	}


	public int actualizar(Usuario usuario) {
		try {
			String sql = "UPDATE USUARIOS SET nombre = ?, dinero = ?, tiempo = ?, PosX = ?, PosY = ?, email = ?, pass = ?, estado = ?, esAdmin = ?, id_tipo_atraccion = ? WHERE id_usuario = ?";
			Connection conn = ProveedorDeConeccion.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, usuario.getNombre());
			statement.setInt(2, usuario.getPresupuesto());
			statement.setDouble(3, usuario.getTiempoDisponible());
			statement.setDouble(4, usuario.getPosX()); 
			statement.setDouble(5, usuario.getPosY()); 
			statement.setString(6, usuario.getEmail());
			statement.setString(7, usuario.getPassword());
			statement.setInt(8, convertirBooleano(usuario.getEstaActivo()));
			statement.setInt(9, convertirBooleano(usuario.getEsAdmin()));
			statement.setLong(10, obtegerIdTipoAtraccion(usuario)); 
			statement.setLong(11, usuario.getIdUsuario());
			int rows = statement.executeUpdate();
			statement.close();
			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	
	private int convertirBooleano(boolean valor) {
		if (valor == true) {
			return 1;			
		} else {
			if (valor == false ) {
				return 0;
			}
		}
		return 0;
	}


	public int desactivar(long id) {
		
		try {
			String sql = "UPDATE USUARIOS SET estado = 0 WHERE id_usuario = ?";
			Connection conn = ProveedorDeConeccion.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setLong(1, id);

			int rows = statement.executeUpdate();

			statement.close();
			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	


	public int borrar(Usuario usuario) {
		
		try {
			String sql = "DELETE FROM USUARIOS WHERE id_usuario = ?";
			Connection conn = ProveedorDeConeccion.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setLong(1, usuario.getIdUsuario());
			int rows = statement.executeUpdate();

			statement.close();
			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public Usuario buscarPorIdUsuario(long idUsuario) {
		try {
			String sql = "SELECT id_usuario, nombre, dinero, tiempo, posx, posy, email, pass, estado, esAdmin, "
					+ "Tipos_atraccion.descripcion_tipo_atraccion "
					+ "FROM Usuarios inner JOIN Tipos_atraccion "
					+ "on Usuarios.id_tipo_atraccion = Tipos_atraccion.id_tipo_atraccion "
					+ "WHERE id_usuario = ?";
		
			Connection conn = ProveedorDeConeccion.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setLong(1, idUsuario);
			ResultSet resultados = statement.executeQuery();

			Usuario user = null;

			if (resultados.next()) {
				user = toUsuario(resultados);
			}

			statement.close();
			return user;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	@Override
	public Usuario buscarPorEmailyPassword(String email, String password) {
		try {
			String sql = "SELECT id_usuario, nombre, dinero, tiempo, posx, posy, email, pass, estado, esAdmin, "
					+ "Tipos_atraccion.descripcion_tipo_atraccion "
					+ "FROM Usuarios inner JOIN Tipos_atraccion "
					+ "on Usuarios.id_tipo_atraccion = Tipos_atraccion.id_tipo_atraccion "
					+ "WHERE email = ? and pass = ?";
			Connection conn = ProveedorDeConeccion.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, email);
			statement.setString(2, password);
			ResultSet resultados = statement.executeQuery();

			Usuario user = null;

			if (resultados.next()) {
				user = toUsuario(resultados);
			}

			statement.close();
			return user;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	

	public int contarTodos() {
		try {
			String sql = "SELECT COUNT(1) AS TOTAL FROM USUARIOS";
			Connection conn = ProveedorDeConeccion.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			resultados.next();
			int total = resultados.getInt("TOTAL");
			
			statement.close();
			return total;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
		
	}
	

	public ArrayList<Usuario> buscarTodos() {
		try {
			String sql = "SELECT id_usuario, nombre, dinero, tiempo, posx, posy, email, pass, estado, esAdmin, "
					+ "Tipos_atraccion.descripcion_tipo_atraccion "
					+ "FROM Usuarios inner JOIN Tipos_atraccion "
					+ "on Usuarios.id_tipo_atraccion = Tipos_atraccion.id_tipo_atraccion ";

			Connection conn = ProveedorDeConeccion.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
			while (resultados.next()) {
				usuarios.add(toUsuario(resultados));
			}

			statement.close();
			return usuarios;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}	
	

	private Usuario toUsuario(ResultSet resultados) throws SQLException {
		/* 		
		1  id_usuario
		2  nombre
		3  dinero
		4  tiempo
		5  posx
		6  posy
		7  email
		8  pass
		9  estado
		10 esAdmin
		11 tipos_atraccion
		*/
		Long idUsuario = resultados.getLong(1);
		String nombre = resultados.getString(2);
		int dinero = resultados.getInt(3);
		double tiempo = resultados.getInt(4);
		double posX = resultados.getInt(5);
		double posY = resultados.getInt(6);
		String email = resultados.getString(7);
		String password = resultados.getString(8);
		Boolean estado = booleano(resultados.getInt(9));
		Boolean esAdmin = booleano(resultados.getInt(10));
		TipoAtraccion preferencia =  TipoAtraccion.valueOf(resultados.getString(11).toUpperCase());
		Usuario usuario = new Usuario(idUsuario, nombre , dinero, tiempo, new Posicion(posX, posY), email, password, estado, esAdmin,  preferencia);

		ItinerarioDAO itinerarioDAO = DAOFactory.getItinerarioDAO();
		itinerarioDAO.itinerarioeHistorialDelUsuario(usuario);

		return usuario;
	}


	private Boolean booleano(int valor) {
		if (valor == 0) {
			return false;			
		} else {
			if (valor == 1 ) return true;
		}
		return null;
	}




	
	
	
}
