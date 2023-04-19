package modelo;

import java.util.ArrayList;
import java.util.Map;

public class PromocionAxB extends Producto {
//CODIGO NUEVO POR ENZO
		private long idPromocion;
		private Map<String, String> errores;
		
		public PromocionAxB(String nombre, TipoAtraccion tipo, Atraccion a1, Atraccion a2, Atraccion a3) {
			super(nombre, tipo, 0, 0);
			super.setCosto(a1.getCosto() + a2.getCosto());
			super.setDuracion(a1.getDuracion() + a2.getDuracion() + a3.getDuracion());
			super.setPromo(true);
			super.setDescuento(0);
			this.atraccionesIncluidas.add(a1);
			this.atraccionesIncluidas.add(a2);
			this.atraccionesIncluidas.add(a3);
		}
		
		public PromocionAxB(long idPromocion, String nombre, TipoAtraccion tipoAtraccion, ArrayList<Atraccion> atraccionesIncluidas , int costo, double duracion) {
			super(idPromocion, nombre, tipoAtraccion, costo, duracion, "","");
			//super.setCosto(atraccionesIncluidas.get(0).getCosto() + atraccionesIncluidas.get(1).getCosto());
			//super.setDuracion(atraccionesIncluidas.get(0).getDuracion() + atraccionesIncluidas.get(1).getDuracion() + atraccionesIncluidas.get(1).getDuracion());
			super.setPromo(true);
			super.setDescuento(0);
			this.atraccionesIncluidas= atraccionesIncluidas;
			this.idPromocion = idPromocion;
		}
		// se usa en toProducto  PromocionDAOImpl
		public PromocionAxB(long idPromocion, String nombre, TipoAtraccion tipoAtraccion, ArrayList<Atraccion> atraccionesIncluidas, 
				int costo, int descuento, double duracion,  String descripcion, String imgURL, Boolean estaActivo) {
			super(idPromocion, nombre, tipoAtraccion, costo, duracion,  descripcion,  imgURL);
			//super.setCosto(atraccionesIncluidas.get(0).getCosto() + atraccionesIncluidas.get(1).getCosto());
			//super.setDuracion(atraccionesIncluidas.get(0).getDuracion() + atraccionesIncluidas.get(1).getDuracion() + atraccionesIncluidas.get(1).getDuracion());
			super.setPromo(true);
			super.setDescuento(0);
			super.setEstaActivo(estaActivo);
			this.atraccionesIncluidas= atraccionesIncluidas;
			this.idPromocion = idPromocion;
		}
		
		
		
		
		
		public PromocionAxB(String nombre, Atraccion a1, Atraccion a2, Atraccion a3) {
			super(nombre, null, 0, 0);
			super.setCosto(a1.getCosto() + a2.getCosto());
			super.setDuracion(a1.getDuracion() + a2.getDuracion() + a3.getDuracion());
			super.setPromo(true);
			super.setDescuento(0);
			this.atraccionesIncluidas.add(a1);
			this.atraccionesIncluidas.add(a2);
			this.atraccionesIncluidas.add(a3);
		}


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
			return "Id: " + super.getIdProducto() + " -->[Promocion: " + super.getTipo() + "]" + "'" + super.getNombre() + "', Costo: " + super.getCosto()
					+ " Duracion: " + super.getDuracion() + ".\n" + "    -Atracciones Incluidas: ["
					+ atraccionesIncluidas.get(0).getNombre() + ", " + atraccionesIncluidas.get(1).getNombre() + ", "
					+ atraccionesIncluidas.get(2).getNombre() + "]";
		}

		@Override
		public void mostrarPorPantalla() {
			System.out.printf("%-22s%-14s%-8d%-10.1f%-7s", getNombre(), getTipo(), this.getCosto(), getDuracion(), getCupo());
			System.out.println(
					"[" + getAtraccionesIncluidas().get(0).getNombre() + ", " + getAtraccionesIncluidas().get(1).getNombre()
							+ ", " + getAtraccionesIncluidas().get(2).getNombre() + "]");
		}

	}

	
	/**VIEJO SI ANDA BORRAR
	private long idPromocion;
	
	public PromocionAxB(String nombre, TipoAtraccion tipo, Atraccion a1, Atraccion a2, Atraccion a3) {
		super(nombre, tipo, 0, 0);
		super.setCosto(a1.getCosto() + a2.getCosto());
		super.setDuracion(a1.getDuracion() + a2.getDuracion() + a3.getDuracion());
		super.setPromo(true);
		this.atraccionesIncluidas.add(a1);
		this.atraccionesIncluidas.add(a2);
		this.atraccionesIncluidas.add(a3);
	}

    // se usa en toProducto en Promocion DAOImpl
	public PromocionAxB(long idPromocion, String nombre, TipoAtraccion tipoAtraccion, ArrayList<Atraccion> atraccionesIncluidas , int costo, double duracion) {
		super(idPromocion, nombre, tipoAtraccion, costo, duracion);
		//super.setCosto(atraccionesIncluidas.get(0).getCosto() + atraccionesIncluidas.get(1).getCosto());
		//super.setDuracion(atraccionesIncluidas.get(0).getDuracion() + atraccionesIncluidas.get(1).getDuracion() + atraccionesIncluidas.get(1).getDuracion());
		super.setPromo(true);
		this.atraccionesIncluidas= atraccionesIncluidas;
		this.idPromocion = idPromocion;
		
	}



	@Override
	public String toString() {
		return "PromocionAxB [idPromocion=" + idPromocion + ", esPromo=" + esPromo + ", atraccionesIncluidas="
				+ atraccionesIncluidas + "]";
	}


	@Override
	public void mostrarPorPantalla() {
		System.out.printf("%-22s%-14s%-8d%-10.1f%-7s", getNombre(), getTipo(), this.getCosto(), getDuracion(), getCupo());
		System.out.println(
				"[" + getAtraccionesIncluidas().get(0).getNombre() + ", " + getAtraccionesIncluidas().get(1).getNombre()
						+ ", " + getAtraccionesIncluidas().get(2).getNombre() + "]");
	}
**/

