package persistencia.implementaciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Atraccion;
import modelo.TipoAtraccion;
import persistencia.genericos.AtraccionDAO;
import persistencia.genericos.MissingDataException;
import persistencia.genericos.ProveedorDeConeccion;

public class AtraccionDAOImpl implements AtraccionDAO {


	@Override
	public int actualizar(Atraccion atraccion) {
		try {
			String sql = "UPDATE atracciones SET Nombre = ?, cupo = ?, precio = ?, duracion = ?,descripcion = ?  WHERE id_atracciones = ?";
			Connection conn = ProveedorDeConeccion.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, atraccion.getNombre());
			statement.setInt(2, atraccion.getCupo());
			statement.setDouble(3, atraccion.getCosto());
			statement.setDouble(4, atraccion.getDuracion());
			statement.setLong(5, atraccion.getIdAtraccion());
			statement.setString(5, atraccion.getDescripcion());
			statement.setLong(6, atraccion.getIdAtraccion());
			int rows = statement.executeUpdate();

			statement.close();
			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int actualizarCupo(Atraccion atraccion) {
		try {
			String sql = "UPDATE atracciones SET cupo = ? WHERE id_atracciones = ?";
			Connection conn = ProveedorDeConeccion.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, atraccion.getCupo());
			statement.setLong(2, atraccion.getIdAtraccion());

			int rows = statement.executeUpdate();

			statement.close();
			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int contarTodos() {
		try {
			String sql = "SELECT COUNT(1) AS TOTAL FROM atracciones WHERE activo=1";
			Connection conn = ProveedorDeConeccion.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			resultados.next();
			int total = resultados.getInt("TOTAL");

			return total;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	// nuevosDAOS

	@Override
	public ArrayList<Atraccion> buscarTodos() {
		try {
			String sql = "SELECT * FROM ATRACCIONES";
			Connection conn = ProveedorDeConeccion.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();
			ArrayList<Atraccion> atracciones = new ArrayList<Atraccion>();
			while (resultados.next()) {
				atracciones.add(toAtraccion(resultados));
			}

			statement.close();
			return atracciones;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private Atraccion toAtraccion(ResultSet resultados) throws SQLException {
		Long idAtraccion = resultados.getLong(1);
		String nombre = resultados.getString(2);
		int cupo = resultados.getInt(3);
		int costo = resultados.getInt(4);
		double duracion = resultados.getDouble(5);
		String descripcion = resultados.getString(6);
		TipoAtraccion tipo = TipoAtraccion.obtenerPorID(resultados.getInt(7));
		Boolean estaActivo = booleano(resultados.getInt(8));
		String img = resultados.getString(9);

		return new Atraccion(idAtraccion, nombre, tipo, costo, duracion, cupo, estaActivo, img, descripcion);
	}

	@Override
	public Atraccion buscarPorIdAtraccion(Long IdAtraccion) {
		try {
			String sql = "SELECT * FROM ATRACCIONES where id_atracciones = ?";
			Connection conn = ProveedorDeConeccion.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setLong(1, IdAtraccion);
			ResultSet resultados = statement.executeQuery();

			Atraccion atraccion = null;

			if (resultados.next()) {
				atraccion = toAtraccion(resultados);
			}
			statement.close();
			return atraccion;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

//agregue
	@Override
	public int insertar(Atraccion atraccion) {
		try {
			// ( String nombre, TipoAtraccion tipo, int costo, double duracion, int cupo,
			// String img, String descripcion)
			String sql = "INSERT INTO ATRACCIONES(Nombre, cupo, precio, duracion, id_tipo_atraccion, img, descripcion) VALUES (?,?,?,?,?,?,?)";
			Connection conn = ProveedorDeConeccion.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, atraccion.getNombre());
			statement.setInt(2, atraccion.getCupo());
			statement.setDouble(3, atraccion.getCosto());
			statement.setDouble(4, atraccion.getDuracion());
			statement.setInt(5, atraccion.getTipo().getID());
			statement.setString(6, atraccion.getImg());
			statement.setString(7, atraccion.getDescripcion());

			int rows = statement.executeUpdate();

			statement.close();
			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	// agregue
	private Boolean booleano(int valor) {
		if (valor == 0) {
			return false;
		} else {
			if (valor == 1)
				return true;
		}
		return null;
	}

	// EN DESUSO
	@Override
	public int borrar(Atraccion atraccion) {
		try {
			String sql = "DELETE * FROM  'atracciones' WHERE id_Atracciones = ?";
			Connection conn = ProveedorDeConeccion.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setLong(1, atraccion.getIdAtraccion());
			int rows = statement.executeUpdate();

			statement.close();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	// agregue
	@Override
	public int borrarLogico(Atraccion atraccion) {
		try {
			String sql = "UPDATE 'atracciones' SET activo = 0 WHERE id_Atracciones = ?";
			Connection conn = ProveedorDeConeccion.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setLong(1, atraccion.getIdAtraccion());
			int rows = statement.executeUpdate();

			statement.close();
			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	// agregue
	@Override
	public int activar(Atraccion atraccion) {
		try {
			String sql = "UPDATE 'atracciones' SET activo = 1 WHERE id_Atracciones = ?";
			Connection conn = ProveedorDeConeccion.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setLong(1, atraccion.getIdAtraccion());
			int rows = statement.executeUpdate();
			
			statement.close();
			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	//agregado para Promociones
		@Override
		public ArrayList<String> listarTipos() {
			try {
				String sql = "SELECT Descripcion_tipo_atraccion FROM Tipos_Atraccion;";

				Connection conn = ProveedorDeConeccion.getConnection();
				PreparedStatement statement = conn.prepareStatement(sql);
				ResultSet resultados = statement.executeQuery();

				ArrayList<String> tipos = new ArrayList<String>();
				while (resultados.next()) {
					tipos.add(resultados.getString(1));
				}
				
				statement.close();
				
				return tipos;
			} catch (Exception e) {
				throw new MissingDataException(e);
			}
		}

}
