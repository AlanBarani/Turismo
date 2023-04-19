package modelo;

import java.util.ArrayList;
import java.util.Map;

public class PromocionAbsoluta extends Producto {
		
	//NUEVO POR ENZO
	private long idPromocion;
		private Map<String, String> errores;
		
		public PromocionAbsoluta(String nombre, TipoAtraccion tipo, Atraccion a1, Atraccion a2, int costo) {
			super(nombre, tipo, costo, 0);
			super.setDuracion(a1.getDuracion() + a2.getDuracion());
			super.setPromo(true);
			super.setDescuento(0);
			this.atraccionesIncluidas.add(a1);
			this.atraccionesIncluidas.add(a2);
		}


		public PromocionAbsoluta(long idPromocion, String nombre, TipoAtraccion tipoAtraccion, ArrayList<Atraccion> atraccionesIncluidas,int costo, double duracion) {
			super(idPromocion, nombre, tipoAtraccion, costo, duracion);
			//super.setDuracion(atraccionesIncluidas.get(0).getDuracion() + atraccionesIncluidas.get(1).getDuracion());
			super.setPromo(true);
			super.setDescuento(0);
			this.atraccionesIncluidas= atraccionesIncluidas;
			this.idPromocion = idPromocion;	}

		public PromocionAbsoluta(String nombre, Atraccion a1, Atraccion a2, int costo) {
			super(nombre, null, costo, 0);
			super.setDuracion(a1.getDuracion() + a2.getDuracion());
			super.setPromo(true);
			super.setDescuento(0);
			this.atraccionesIncluidas.add(a1);
			this.atraccionesIncluidas.add(a2);
		}
		// se usa en toProducto  PromocionDAOImpl
		public PromocionAbsoluta(long idPromocion, String nombre, TipoAtraccion tipoAtraccion, ArrayList<Atraccion> atraccionesIncluidas,
				int costo, int descuento, double duracion, String descripcion, String imgURL, Boolean estaActivo) {
			super(idPromocion, nombre, tipoAtraccion, costo, duracion, descripcion, imgURL);
			//super.setDuracion(atraccionesIncluidas.get(0).getDuracion() + atraccionesIncluidas.get(1).getDuracion());
			super.setPromo(true);
			super.setDescuento(0);
			super.setDescripcion(descripcion);
			super.setImgURL(imgURL);
			super.setEstaActivo(estaActivo);
			this.atraccionesIncluidas= atraccionesIncluidas;
			this.idPromocion = idPromocion;	}
		
		

//		public boolean esValido() {
//			validar();
//			return errores.isEmpty();
//		}
	//	
//		public void validar() {
//			errores = new HashMap<String, String>();
	//
//			if (this.getCosto() <= 0) {
//				errores.put("costo", "Debe ser positivo");
//			}
//			if (this.getDuracion() <= 0) {
//				errores.put("duraoion", "Debe ser positivo");
//			}
//				
//			for (int i=0; i< this.atraccionesIncluidas.size()-1; i++) {
//				if (this.atraccionesIncluidas.get(i+1).equals(atraccionesIncluidas.get(i))
//					|| !this.atraccionesIncluidas.get(i+1).getTipo().equals(this.atraccionesIncluidas.get(i).getTipo())) {
//					errores.put("Atracciones", "Deben ser del mismo tipo y no deben estar repetidas");
//				};
//			}
//		}
		
		@Override
		public String toString() {
			return "-->[ID" + super.getIdProducto() + ", Promocion: " + super.getTipo() + "]" + "'" + super.getNombre() + "', Costo: " + super.getCosto()
					+ ", Duracion: " + super.getDuracion() + "\n     -Atracciones Incluidas: ["
					+ atraccionesIncluidas.get(0).getNombre() + ", " + atraccionesIncluidas.get(1).getNombre() + "]";
		}

		@Override
		public void mostrarPorPantalla() {
			System.out.printf("%-22s%-14s%-8d%-10.1f%-7s", getNombre(), getTipo(), this.getCosto(), getDuracion(), getCupo());
			System.out.println("[" + getAtraccionesIncluidas().get(0).getNombre() + ", "
					+ getAtraccionesIncluidas().get(1).getNombre() + "]");
		}

	}

	
	/**VIEJO, SI ANDA BORRAR
	private long idPromocion;

	public PromocionAbsoluta(String nombre, TipoAtraccion tipo, Atraccion a1, Atraccion a2, int costo) {
		super(nombre, tipo, costo, 0);
		super.setDuracion(a1.getDuracion() + a2.getDuracion());
		super.setPromo(true);
		this.atraccionesIncluidas.add(a1);
		this.atraccionesIncluidas.add(a2);
	}

	public PromocionAbsoluta(long idPromocion, String nombre, TipoAtraccion tipoAtraccion,
			ArrayList<Atraccion> atraccionesIncluidas, int costo, double duracion) {
		super(idPromocion, nombre, tipoAtraccion, costo, duracion);
		// super.setDuracion(atraccionesIncluidas.get(0).getDuracion() +
		// atraccionesIncluidas.get(1).getDuracion());
		super.setPromo(true);
		this.atraccionesIncluidas = atraccionesIncluidas;
		this.idPromocion = idPromocion;
	}

	@Override
	public String toString() {
		return "-->[Promocion: " + super.getTipo() + "]" + "'" + super.getNombre() + "', Costo: " + super.getCosto()
				+ ", Duracion: " + super.getDuracion() + "\n     -Atracciones Incluidas: ["
				+ atraccionesIncluidas.get(0).getNombre() + ", " + atraccionesIncluidas.get(1).getNombre() + "]";
	}

	@Override
	public void mostrarPorPantalla() {
		System.out.printf("%-22s%-14s%-8d%-10.1f%-7s", getNombre(), getTipo(), this.getCosto(), getDuracion(),
				getCupo());
		System.out.println("[" + getAtraccionesIncluidas().get(0).getNombre() + ", "
				+ getAtraccionesIncluidas().get(1).getNombre() + "]");
	}
**/

