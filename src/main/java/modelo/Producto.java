package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class Producto {

	// NUEVO ESCRITO POR ENZO
	private long IdProducto;
	private String nombre;
	private TipoAtraccion tipo;
	private int costo;
	private double duracion;
	protected boolean esPromo;
	protected ArrayList<Atraccion> atraccionesIncluidas = new ArrayList<Atraccion>();
	private int descuento;
	private String descripcion;
	private String imgURL;
	private Boolean estaActivo;

	public Map<String, String> errores;
	public Map<String, String> errors;

	public Producto(String nombre, TipoAtraccion tipo, int costo, double duracion) {
		this.nombre = nombre;
		this.tipo = tipo;
		this.costo = costo;
		this.duracion = duracion;
	}

	// agregue
	public Producto(String nombre, int costo, double duracion) {
		this.nombre = nombre;
		this.costo = costo;
		this.duracion = duracion;
	}

	// se usa en el constructor de promomocion que viene de toProducto en
	// PromocionDAOImpl
	public Producto(long id, String nombre, TipoAtraccion tipo, double costo, double duracion, 
			String descripcion, String imgURL) {
		this.nombre = nombre;
		this.tipo = tipo;
		this.costo = (int) costo;
		this.duracion = duracion;
		this.IdProducto = id;
		this.descripcion = descripcion;
		this.imgURL = imgURL;

	}

	// nuevoProducto

	public Producto(long id, String nombre, TipoAtraccion tipo, int costo, double duracion) {
		this.nombre = nombre;
		this.tipo = tipo;
		this.costo = (int) costo;
		this.duracion = duracion;
		this.IdProducto = id;
	}

	public void setTipo(TipoAtraccion tipo) {
		this.tipo = tipo;
	}

	public int getCosto() {
		return costo;
	}

	public void setCosto(int costo) {
		this.costo = costo;
	}

	public double getDuracion() {
		return duracion;
	}

	public void setDuracion(double duracion) {
		this.duracion = duracion;
	}

	public int getCupo() {
		int cupo1 = Integer.MAX_VALUE;
		for (Atraccion atr : atraccionesIncluidas) {
			if (atr.getCupo() < cupo1) {
				cupo1 = atr.getCupo();
			}
		}
		return cupo1;
	}

	public boolean esPromo() {
		return esPromo;
	}

	public void setPromo(boolean isPromo) {
		this.esPromo = isPromo;
	}

	public String imprimirEnArchivo() {
		return this.nombre + "," + this.tipo + "," + this.costo + "," + this.duracion;
	}

	public abstract void mostrarPorPantalla();

	@Override
	public String toString() {
		return "ID : " + IdProducto + ", Nombre: " + nombre + ", Tipo: " + tipo + ", Costo: " + costo + ", Duracion: "
				+ duracion;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		return Objects.equals(nombre, other.nombre);
	}

	public int getDescuento() {
		return descuento;
	}

	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getImgURL() {
		return imgURL;
	}

	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}

	public String getNombre() {
		return nombre;
	}

	public TipoAtraccion getTipo() {
		return tipo;
	}

	public long getIdProducto() {
		return IdProducto;
	}

	public ArrayList<Atraccion> getAtraccionesIncluidas() {
		return atraccionesIncluidas;
	}

	// agregue
	public boolean isValid() {
		validate();
		return errors.isEmpty();
	}

	// agregue
	public void validate() {
		errors = new HashMap<String, String>();

		if (costo <= 0) {
			errors.put("costo", "Debe ser positivo");
		}
		if (duracion <= 0) {
			errors.put("duracion", "Debe ser positivo");
		}
	}

	public boolean esValido() {
		validar();
		return errores.isEmpty();
	}

	public void validar() {
		errores = new HashMap<String, String>();

		if (costo < 0) {
			errors.put("costo", "Debe ser positivo");
		}
		if (duracion < 0) {
			errors.put("duraoion", "Debe ser positivo");
		}

		for (int i = 0; i < this.atraccionesIncluidas.size() - 1; i++) {
			if (this.atraccionesIncluidas.get(i + 1).equals(atraccionesIncluidas.get(i)) || !this.atraccionesIncluidas
					.get(i + 1).getTipo().equals(this.atraccionesIncluidas.get(i).getTipo())) {
				errores.put("Atracciones", "Deben ser del mismo tipo y no deben estar repetidas");
			}
			;
		}
	}

	public String generarDescripcion() {
		String cadena = "";
		if (this.esPromo) {
			for (Atraccion atraccion : atraccionesIncluidas) {
				cadena = cadena + atraccion.getNombre() + ".   ";
			}
			return "Esta promocion incluye: " + cadena;
		}
		for (Atraccion atraccion : atraccionesIncluidas) {
			cadena = cadena + atraccion.getNombre() + ": " + atraccion.getDescripcion();
		}
		return cadena;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Boolean estaActivo() {
		return estaActivo;
	}

	public void setEstaActivo(Boolean estaActivo) {
		this.estaActivo = estaActivo;
	}

}
