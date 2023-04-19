package servicios;

import java.util.ArrayList;

import modelo.Atraccion;
import modelo.Producto;
import modelo.PromoPorcentual;
import modelo.PromocionAbsoluta;
import modelo.PromocionAxB;
import modelo.TipoAtraccion;
import persistencia.genericos.AtraccionDAO;
import persistencia.genericos.DAOFactory;
import persistencia.genericos.PromocionDAO;

public class PromocionesService {

	public ArrayList<Producto> listar() {
		ArrayList<Producto> promociones = DAOFactory.getPromocionDAO().buscarTodos();
		return promociones;
	}

	public ArrayList<String> listarTiposPromo() {
		ArrayList<String> tiposPromo = DAOFactory.getPromocionDAO().listarTiposPromocion();
		return tiposPromo;
	}

	public Producto crear(Integer tipoPromo, String nombre, String tipo, Atraccion a1, Atraccion a2, Atraccion a3,
			Integer costo, Integer descuento) {
		Producto producto = null;

		
		System.out.println("DESDE CREAR");
		System.out.println(tipoPromo);
		
		if (tipoPromo == 1) {
			producto = new PromocionAxB(nombre, TipoAtraccion.valueOf(tipo), a1, a2, a3);
			if (producto.esValido()) {
				PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
				promocionDAO.insertar(producto);

//				producto = promocionAxB;
				// XXX: si no devuelve "1", es que hubo más errores
			}

		} else if (tipoPromo == 3) {
			producto= new PromocionAbsoluta(nombre, TipoAtraccion.valueOf(tipo), a1, a2, costo);

			if (producto.esValido()) {
				PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
				promocionDAO.insertar(producto);

				//producto = promocionAbsoluta;
			}

		} else if (tipoPromo == 2) {
			producto = new PromoPorcentual(nombre, TipoAtraccion.valueOf(tipo), a1, a2, descuento);
			if (producto.esValido()) {
				PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
				promocionDAO.insertar(producto);

				//producto = promocionPorcentual;
				// XXX: si no devuelve "1", es que hubo más errores
			}
		}

		return producto;
	}
	
	public Producto actualizar(int tipoPromo, Long id, String nombre, String tipo, 
			Atraccion a1, Atraccion a2, Atraccion a3, int costo, int descuento) {
	
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		Producto promocion = promocionDAO.buscarPorIdPromocion(id);
		
		promocion.setNombre(nombre);
		promocion.setTipo(TipoAtraccion.valueOf(tipo));
		promocion.getAtraccionesIncluidas().set(0, a1);
		promocion.getAtraccionesIncluidas().set(1, a2);
		promocion.setDescuento(descuento);
		
		
		if (tipoPromo == 1) {
			promocion.getAtraccionesIncluidas().set(2, a3);
		} else if (tipoPromo == 3) {
			promocion.setCosto(costo);
		
		
		if (promocion.esValido()) {
			promocionDAO.actualizar(promocion);
			// XXX: si no devuelve "1", es que hubo más errores
		}
	}
		return promocion;
}

	public Producto buscarPorId(Long id) {
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		Producto promocion = promocionDAO.buscarPorIdPromocion(id);
		
		return promocion;
		
	}

	public Producto actualizarAxB(Long id, String nombre, Atraccion a1, Atraccion a2, Atraccion a3) {
	
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		Producto promocion = promocionDAO.buscarPorIdPromocion(id);
		
		Producto promocionAxB =  new PromocionAxB(nombre, a1, a2, a3);
		
		promocion.setNombre(promocionAxB.getNombre());
		promocion.getAtraccionesIncluidas().set(0, promocionAxB.getAtraccionesIncluidas().get(0));
		promocion.getAtraccionesIncluidas().set(1, promocionAxB.getAtraccionesIncluidas().get(1));
		promocion.getAtraccionesIncluidas().set(2, promocionAxB.getAtraccionesIncluidas().get(2));
		
		promocion.setCosto(promocionAxB.getCosto());
		promocion.setDuracion(promocionAxB.getDuracion());
		
		
		System.out.println(promocion.esValido());		
		
		if (promocion.esValido()) {
			System.out.println("Desde SERVICE");
			System.out.println(promocion.toString());
			promocionDAO.actualizar(promocion);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return promocion;
	}
	
	public Producto actualizarAbsol(Long id, String nombre,	Atraccion a1, Atraccion a2, int costo) {
	
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		Producto promocion = promocionDAO.buscarPorIdPromocion(id);
		
		Producto promocionAbsol =  new PromocionAbsoluta(nombre, a1, a2, costo);
		
		promocion.setNombre(promocionAbsol.getNombre());
		promocion.getAtraccionesIncluidas().set(0, promocionAbsol.getAtraccionesIncluidas().get(0));
		promocion.getAtraccionesIncluidas().set(1, promocionAbsol.getAtraccionesIncluidas().get(1));
		promocion.setCosto(promocionAbsol.getCosto());
		promocion.setDuracion(promocionAbsol.getDuracion());
		
//		promocion.setNombre(nombre);
//		promocion.getAtraccionesIncluidas().set(0, a1);
//		promocion.getAtraccionesIncluidas().set(1, a2);
//		promocion.setCosto(costo);
		
		if (promocion.esValido()) {
			promocionDAO.actualizar(promocion);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return promocion;
	}

	public Producto actualizarPorcent(Long id, String nombre, Atraccion a1, Atraccion a2, int descuento) {
	
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		Producto promocion = promocionDAO.buscarPorIdPromocion(id);
		
		Producto promoPorcentual =  new PromoPorcentual(nombre, a1, a2, descuento);
		
		System.out.println(promocion.getClass().toString());
		promocion.setNombre(promoPorcentual.getNombre());
		promocion.getAtraccionesIncluidas().set(0, promoPorcentual.getAtraccionesIncluidas().get(0));
		promocion.getAtraccionesIncluidas().set(1, promoPorcentual.getAtraccionesIncluidas().get(1));
		promocion.setCosto(promoPorcentual.getCosto());
		promocion.setDuracion(promoPorcentual.getDuracion());
		
		promocion.setNombre(nombre);
		promocion.getAtraccionesIncluidas().set(0, a1);
		promocion.getAtraccionesIncluidas().set(1, a2);
		promocion.setDescuento(descuento);
		
		System.out.println(promocion.getDescuento());
				
		if (promocion.esValido()) {
			promocionDAO.actualizar(promocion);
			// XXX: si no devuelve "1", es que hubo más errores
		}
		return promocion;
	}
	
	
	public void activar(Long IdPromocion) {
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		promocionDAO.activar(IdPromocion);
	}
	
	public void borrar(Long IdPromocion) {
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		promocionDAO.borrarLogico(IdPromocion);
	}
}
