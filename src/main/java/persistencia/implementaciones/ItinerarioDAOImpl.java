package persistencia.implementaciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import modelo.Producto;
import modelo.Usuario;
import persistencia.genericos.AtraccionDAO;
import persistencia.genericos.DAOFactory;
import persistencia.genericos.ItinerarioDAO;
import persistencia.genericos.MissingDataException;
import persistencia.genericos.PromocionDAO;
import persistencia.genericos.ProveedorDeConeccion;

public class ItinerarioDAOImpl implements ItinerarioDAO {

	
	public long obtenerIdItinerarioPorIdUsuario(Long idUsuario) {
		long id = 0;
		try {
			String sql = "SELECT id_itinerario  FROM Item_Itinerario WHERE id_usuario = ?";
			Connection conn = ProveedorDeConeccion.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setLong(1, idUsuario);
			ResultSet resultados = statement.executeQuery();

			if (resultados.next()) {
				id = resultados.getLong(1);
			}
			statement.close();
			return id;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	

	
	public int insertarItemItinerario(long  IdUsuario, long IdProducto, long tipo) {
		try {
			String sql = "INSERT INTO Item_Itinerario (id_usuario, id_producto, tipo_producto ) VALUES (?, ?, ?)";
			Connection conn = ProveedorDeConeccion.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setLong(1, IdUsuario);
			statement.setLong(2, IdProducto);
			statement.setLong(3, tipo);

			int rows = statement.executeUpdate();
			statement.close();
			return rows;
		
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	// este metodo no se usa
	@Override
	public ArrayList<Producto> itinerarioDelUsuarioPorId(Long idUsuario) { 
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		ArrayList<Producto> productos = new ArrayList<Producto>();
		try {
			String sql = "SELECT * FROM Item_Itinerario WHERE id_usuario = ?";
			Connection conn = ProveedorDeConeccion.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setLong(2, idUsuario);
			ResultSet resultados = statement.executeQuery();

			while (resultados.next()) {
				if (resultados.getInt(4) == 1) {
					productos.add(atraccionDAO.buscarPorIdAtraccion(resultados.getLong(3)));
				}else {
					productos.add(promocionDAO.buscarPorIdPromocion(resultados.getLong(3)));
				}
			}
			statement.close();
			return productos;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	
	
	public Usuario itinerarioeHistorialDelUsuario(Usuario usuario) {
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		try {
			String sql = "SELECT * FROM Item_Itinerario WHERE id_usuario = ?";
			Connection conn = ProveedorDeConeccion.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setLong(1,usuario.getIdUsuario());
			ResultSet resultados = statement.executeQuery();

			while (resultados.next()) {
				if (resultados.getLong(4) == 1) {
					usuario.getItinerario().add(atraccionDAO.buscarPorIdAtraccion(resultados.getLong(3)));
				}else if (resultados.getLong(4) == 2) {
					usuario.getItinerario().add(promocionDAO.buscarPorIdPromocion(resultados.getLong(3)));
				}
					usuario.getHistorialDeAtracciones().addAll(usuario.atraccionesDelItinerario());
			}
			statement.close();
			return usuario;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}


	@Override
	public ArrayList<Producto> buscarTodos() {
		return null;
	}

	@Override
	public int contarTodos() {
		return 0;
	}

	@Override
	public int insertar(Producto t) {
		return 0;
	}

	@Override
	public int actualizar(Producto t) {
		return 0;
	}

	@Override
	public int borrar(Producto t) {
		return 0;
	}










}
