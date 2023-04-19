package persistencia.implementaciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.TipoAtraccion;
import persistencia.genericos.MissingDataException;
import persistencia.genericos.ProveedorDeConeccion;
import persistencia.genericos.TipoAtraccionDAO;

public class TipoAtraccionDAOImpl implements TipoAtraccionDAO {

	@Override
	public int insertar(TipoAtraccion t) {
		try {
			String sql = "INSERT INTO 'TIPOS_ATRACCION' ('DESCRIPCION_TIPO_ATRACCION') VALUES (?)";
			Connection conn = ProveedorDeConeccion.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			// TIPO DE LA BD (TEXT descripcion_tipo_atraccion)
			statement.setString(1, t.name());

			int rows = statement.executeUpdate();

			statement.close();
			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}


	@Override
	public ArrayList<TipoAtraccion> buscarActivos() {
		try {
			String sql = "SELECT * FROM Tipos_atraccion WHERE activo = 1";

			Connection conn = ProveedorDeConeccion.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			ArrayList<TipoAtraccion> tiposAtr = new ArrayList<TipoAtraccion>();
			while (resultados.next()) {
				tiposAtr.add(toTipoAtraccion(resultados));
			}

			statement.close();
			return tiposAtr;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public ArrayList<TipoAtraccion> buscarInactivos() {
		try {
			String sql = "SELECT * FROM Tipos_atraccion WHERE activo = 0";

			Connection conn = ProveedorDeConeccion.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			ArrayList<TipoAtraccion> tiposAtr = new ArrayList<TipoAtraccion>();
			while (resultados.next()) {
				tiposAtr.add(toTipoAtraccion(resultados));
			}

			statement.close();
			return tiposAtr;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public TipoAtraccion toTipoAtraccion(ResultSet resultados) throws SQLException {
		// COLUMNAS DE LA BD: id_tipo_atraccion, descripcion_tipo_atraccion
		Integer id_tipo_atraccion = resultados.getInt(1);
		String descripcion_tipo_atraccion = resultados.getString(2);
		Boolean estaActivo = booleano(resultados.getInt(3));

		TipoAtraccion tipo = TipoAtraccion.valueOf(descripcion_tipo_atraccion);
		tipo.setEstado(estaActivo);

		return tipo;
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

	@Override
	public int contarTodos() {
		try {
			String sql = "SELECT COUNT(1) AS total FROM Tipos_atraccion WHERE activo = 1";
			Connection conn = ProveedorDeConeccion.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			resultados.next();
			int total = resultados.getInt("total");

			statement.close();
			return total;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}

	}

	// no hay nada para actualizar, solo tienen id, nombre(debe ser igual al enum) y
	// estado

	@Override
	public int actualizar(TipoAtraccion t) {
		return 0;
	}

	// Considera 0 = falso (no activo) y 1 = verdadero (activo)
	@Override
	public int borrar(TipoAtraccion t) {
		try {
			String sql = "UPDATE Tipos_atraccion SET activo = 0 WHERE id_tipo_atraccion = ?";
			Connection conn = ProveedorDeConeccion.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setInt(1, t.getID());
			int rows = statement.executeUpdate();

			statement.close();
			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	
	
	@Override
    public ArrayList<TipoAtraccion> buscarTodos() {
        try {
            String sql = "SELECT * FROM Tipos_atraccion";

            Connection conn = ProveedorDeConeccion.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultados = statement.executeQuery();

            ArrayList<TipoAtraccion> tiposAtr = new ArrayList<TipoAtraccion>();
            while (resultados.next()) {
                tiposAtr.add(toTipoAtraccion(resultados));
            }

            statement.close();
            return tiposAtr;
        } catch (Exception e) {
            throw new MissingDataException(e);
        }
    }

	


	@Override
	public int activar(TipoAtraccion t) {
		try {
			String sql = "UPDATE Tipos_atraccion SET activo = 1 WHERE id_tipo_atraccion = ?";
			Connection conn = ProveedorDeConeccion.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setInt(1, t.getID());
			int rows = statement.executeUpdate();

			statement.close();
			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}


}
