package persistencia.implementaciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Atraccion;
import modelo.Producto;
import modelo.PromoPorcentual;
import modelo.PromocionAbsoluta;
import modelo.PromocionAxB;
import modelo.TipoAtraccion;
import persistencia.genericos.AtraccionDAO;
import persistencia.genericos.DAOFactory;
import persistencia.genericos.MissingDataException;
import persistencia.genericos.PromocionDAO;
import persistencia.genericos.ProveedorDeConeccion;

public class PromocionDAOImpl implements PromocionDAO {

//Contenido nuevo por Enzo
	@Override
	public ArrayList<Producto> buscarTodos() {
		try {
			String sql = "select * from Promociones";
			Connection conn = ProveedorDeConeccion.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			ArrayList<Producto> producto = new ArrayList<Producto>();
			while (resultados.next()) {
				producto.add(toProducto(resultados));
			}
			statement.close();
			return producto;

		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public Producto buscarPorIdPromocion(Long id) {
		try {
			String sql = "Select * from Promociones where id_promocion = ?";
			Connection conn = ProveedorDeConeccion.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setLong(1, id);
			ResultSet resultados = statement.executeQuery();

			Producto producto = null;

			if (resultados.next()) {
				producto = toProducto(resultados);
			}
			statement.close();
			return producto;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private Producto toProducto(ResultSet resultados) throws SQLException {
		// COLUMNAS DEL RESULTSET: ID_promoCIONES NOMBRE PRECIO DESCUENTO TIPO_ATRACCION
		// ID_TIPO_PROMOCION

		Long idPromocion = resultados.getLong(1);
		String nombre = resultados.getString(2);
		int costo = resultados.getInt(3);
		int descuento = resultados.getInt(4);
		TipoAtraccion tipoAtraccion = TipoAtraccion.valueOf(resultados.getString(5));
		int tipoPromo = resultados.getInt(6);
		String descripcion = resultados.getString(7);
		String imgURL = resultados.getString(8);
		Boolean estaActivo = booleano(resultados.getInt(9));

		ArrayList<Atraccion> atraccionesIncluidas = atraccionesDeLaPromocion(resultados);
		double duracion = obtenerDuracionDelaPromocion(atraccionesIncluidas);
		Producto producto = null;
		if (tipoPromo == 1) {
			producto = new PromocionAxB(idPromocion, nombre, tipoAtraccion, atraccionesIncluidas, costo, descuento,
					duracion, descripcion, imgURL, estaActivo);
		} else if (tipoPromo == 2) {
			producto = new PromoPorcentual(idPromocion, nombre, tipoAtraccion, atraccionesIncluidas, costo, descuento,
					duracion, descripcion, imgURL, estaActivo);
		} else if (tipoPromo == 3) {
			producto = new PromocionAbsoluta(idPromocion, nombre, tipoAtraccion, atraccionesIncluidas, costo, descuento,
					duracion, descripcion, imgURL, estaActivo);
		}

		return producto;
	}

	private double obtenerDuracionDelaPromocion(ArrayList<Atraccion> atraccionesIncluidas) {
		double duracion = 0;
		for (Atraccion atraccion : atraccionesIncluidas) {
			duracion += atraccion.getDuracion();
		}
		return duracion;
	}

	private ArrayList<Atraccion> atraccionesDeLaPromocion(ResultSet resultados) throws SQLException {
		try {
			String sql = "select ID_Atraccon from AtraccionesDeLaPromocion "
					+ "where AtraccionesDeLaPromocion.ID_Promocion = ?";
			Connection conn = ProveedorDeConeccion.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setLong(1, resultados.getLong(1));
			ResultSet result = statement.executeQuery();

			AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();

			ArrayList<Atraccion> producto = new ArrayList<Atraccion>();
			while (result.next()) {
				producto.add(atraccionDAO.buscarPorIdAtraccion(result.getLong(1)));
			}
			statement.close();
			return producto;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int insertar(Producto promocion) {
		// Probar implementar TRIGGERS
		try {
			String sql = "INSERT INTO Promociones (Nombre, Precio, Descuento, tipo_atraccion, "
					+ "id_tipo_promocion, descripcion, imgURL)" + "VALUES (?, ?, ?, ?, ?, ?, ?);";
			String sql2 = "INSERT INTO AtraccionesDeLaPromocion (ID_Promocion, ID_Atraccon) "
					+ "VALUES ((SELECT seq FROM sqlite_sequence WHERE name = 'Promociones'), ?);";

			Connection conn = ProveedorDeConeccion.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setString(1, promocion.getNombre());
			statement.setLong(2, promocion.getCosto());
			statement.setString(4, promocion.getTipo().toString());
			statement.setLong(3, promocion.getDescuento());
			statement.setString(6, promocion.getDescripcion());
			statement.setString(7, promocion.getImgURL());

			if (promocion instanceof PromocionAxB) {
				statement.setInt(5, 1);
			} else if (promocion instanceof PromocionAbsoluta) {
				statement.setInt(5, 3);
			} else if (promocion instanceof PromoPorcentual) {
				statement.setInt(5, 2);
			}

			int rows = statement.executeUpdate();

			for (Atraccion atr : promocion.getAtraccionesIncluidas()) {
				statement = conn.prepareStatement(sql2);
				statement.setLong(1, atr.getIdAtraccion());
				rows = statement.executeUpdate();
			}

			statement.close();
			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}

	}

	@Override
	public int actualizar(Producto promocion) {
		try {
			String sql = "UPDATE Promociones SET Nombre = ?, Precio = ?, Descuento = ?, tipo_atraccion  = ?, "
					+ "id_tipo_promocion = ?,  descripcion = ? , imgURL = ? WHERE ID_Promocion = ?;";
			String sql2 = "INSERT INTO AtraccionesDeLaPromocion (ID_Promocion, ID_Atraccon) VALUES (?, ?);";
			String sql3 = "DELETE FROM AtraccionesDeLaPromocion WHERE ID_Promocion = ?;";

			Connection conn = ProveedorDeConeccion.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setLong(8, promocion.getIdProducto());
			statement.setString(1, promocion.getNombre());
			statement.setLong(2, promocion.getCosto());
			statement.setLong(3, promocion.getDescuento());
			statement.setString(4, promocion.getTipo().toString());
			statement.setString(6, promocion.getDescripcion());
			statement.setString(7, promocion.getImgURL());

			if (promocion instanceof PromocionAxB) {
				statement.setInt(5, 1);
			} else if (promocion instanceof PromocionAbsoluta) {
				statement.setInt(5, 3);
			} else if (promocion instanceof PromoPorcentual) {
				statement.setInt(5, 2);
			}

			int rows = statement.executeUpdate();

			statement = conn.prepareStatement(sql3);
			statement.setLong(1, promocion.getIdProducto());
			rows += statement.executeUpdate();

			for (Atraccion atr : promocion.getAtraccionesIncluidas()) {
				statement = conn.prepareStatement(sql2);
				statement.setLong(1, promocion.getIdProducto());
				statement.setLong(2, atr.getIdAtraccion());

				System.out.println(promocion.getIdProducto() + " - " + atr.getIdAtraccion());
				rows += statement.executeUpdate();
				System.out.println("DESDE ACTUALIZAR");
				System.out.println(promocion.toString());
			}

			System.out.println(rows);
			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int borrar(Producto promocion) {
		// Probar implementar TRIGGERS
		try {
			String sql = "DELETE FROM Promociones WHERE ID_Promocion = ?;";
			String sql2 = "DELETE FROM AtraccionesDeLaPromocion WHERE ID_Promocion = ?;";

			Connection conn = ProveedorDeConeccion.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setLong(1, promocion.getIdProducto());
			int rows = statement.executeUpdate();

			statement = conn.prepareStatement(sql2);
			statement.setLong(1, promocion.getIdProducto());
			rows += statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}

	}

	@Override
	public int contarTodos() {
		try {
			String sql = "SELECT COUNT(*) FROM Promociones;";

			Connection conn = ProveedorDeConeccion.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();

			return (int) rs.getLong(1);
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public ArrayList<String> listarTiposPromocion() {
		try {
			String sql = "SELECT descripciontipo_tipo_promocion FROM Tipos_promociones;";

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

	public int borrarLogico(Long IDpromocion) {
		try {
			String sql = "UPDATE 'promociones' SET activo = 0 WHERE id_promocion = ?";
			Connection conn = ProveedorDeConeccion.getConnection();
			;

			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setLong(1, IDpromocion);
			int rows = statement.executeUpdate();

			statement.close();
			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	public int activar(Long IDpromocion) {
		try {
			String sql = "UPDATE 'promociones' SET activo = 1 WHERE id_promocion = ?";
			Connection conn = ProveedorDeConeccion.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setLong(1, IDpromocion);
			int rows = statement.executeUpdate();
			statement.close();
			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private Boolean booleano(int valor) {
		if (valor == 0) {
			return false;
		} else {
			if (valor == 1)
				return true;
		}
		return null;
	}
	
	
	/**
	 * DESACTUALIZADO, BORRAR LUEGO
	 * 
	 * @Override public ArrayList<Producto> buscarTodos() { try { String sql =
	 *           "select id_promocion, nombre, precio, descuento, tipo_atraccion,
	 *           id_tipo_promocion from Promociones"; Connection conn =
	 *           ProveedorDeConeccion.getConnection(); PreparedStatement statement =
	 *           conn.prepareStatement(sql); ResultSet resultados =
	 *           statement.executeQuery();
	 * 
	 * 
	 *           ArrayList<Producto> producto = new ArrayList<Producto>(); while
	 *           (resultados.next()) {
	 * 
	 *           producto.add(toProducto(resultados)); } statement.close(); return
	 *           producto; } catch (Exception e) { throw new
	 *           MissingDataException(e); }
	 * 
	 *           }
	 * 
	 * 
	 * @Override public Producto buscarPorIdPromocion(Long id) { try { String sql =
	 *           "select id_promocion, nombre, precio, descuento, tipo_atraccion,
	 *           id_tipo_promocion from Promociones where id_promocion = ?";
	 *           Connection conn = ProveedorDeConeccion.getConnection();
	 *           PreparedStatement statement = conn.prepareStatement(sql);
	 *           statement.setLong(1, id); ResultSet resultados =
	 *           statement.executeQuery();
	 * 
	 *           Producto producto = null;
	 * 
	 *           if (resultados.next()) { producto = toProducto(resultados); }
	 *           statement.close(); return producto; } catch (Exception e) { throw
	 *           new MissingDataException(e); } }
	 * 
	 * 
	 *           private Producto toProducto(ResultSet resultados) throws
	 *           SQLException { // COLUMNAS DEL RESULTSET: ID_promoCIONES NOMBRE
	 *           PRECIO DESCUENTO TIPO_ATRACCION ID_TIPO_PROMOCION Long idPromocion
	 *           = resultados.getLong(1); String nombre = resultados.getString(2);
	 *           int costo = resultados.getInt(3); int descuentoPorcentual =
	 *           resultados.getInt(4); TipoAtraccion tipoAtraccion =
	 *           TipoAtraccion.valueOf(resultados.getString(5)); int tipoPromo =
	 *           resultados.getInt(6);
	 * 
	 *           ArrayList<Atraccion> atraccionesIncluidas =
	 *           atraccionesDeLaPromocion(resultados); double duracion =
	 *           obtenerDuracionDelaPromocion(atraccionesIncluidas); Producto
	 *           producto = null; if (tipoPromo == 1) { producto = new
	 *           PromocionAxB(idPromocion, nombre, tipoAtraccion,
	 *           atraccionesIncluidas, costo, duracion); } else if (tipoPromo == 2)
	 *           { producto = new PromoPorcentual(idPromocion, nombre,
	 *           tipoAtraccion, atraccionesIncluidas, descuentoPorcentual, costo,
	 *           duracion); } else if (tipoPromo == 3) { producto = new
	 *           PromocionAbsoluta(idPromocion, nombre, tipoAtraccion,
	 *           atraccionesIncluidas, costo, duracion); }
	 * 
	 *           return producto; }
	 * 
	 * 
	 *           private double obtenerDuracionDelaPromocion(ArrayList<Atraccion>
	 *           atraccionesIncluidas) { double duracion = 0; for (Atraccion
	 *           atraccion : atraccionesIncluidas) { duracion +=
	 *           atraccion.getDuracion(); } return duracion; }
	 * 
	 * 
	 *           private ArrayList<Atraccion> atraccionesDeLaPromocion(ResultSet
	 *           resultados) throws SQLException { try { String sql = "select
	 *           AtraccionesDeLaPromocion.ID_Atraccon " + "from
	 *           AtraccionesDeLaPromocion " + "where
	 *           AtraccionesDeLaPromocion.ID_Promocion = ?"; Connection conn =
	 *           ProveedorDeConeccion.getConnection(); PreparedStatement statement =
	 *           conn.prepareStatement(sql); statement.setLong(1,
	 *           resultados.getLong(1)); ResultSet result =
	 *           statement.executeQuery();
	 * 
	 *           AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
	 * 
	 *           ArrayList<Atraccion> producto = new ArrayList<Atraccion>(); while
	 *           (result.next()) {
	 *           producto.add(atraccionDAO.buscarPorIdAtraccion(result.getLong(1)));
	 *           } statement.close(); return producto; } catch (Exception e) { throw
	 *           new MissingDataException(e); } }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @Override public int insertar(Producto t) { return 0; }
	 * 
	 * 
	 * @Override public int actualizar(Producto t) { return 0; }
	 * 
	 * 
	 * @Override public int borrar(Producto t) { return 0; }
	 * 
	 * 
	 * @Override public int contarTodos() { return 0; }
	 * 
	 * 
	 **/
}
