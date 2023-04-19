package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import persistencia.genericos.AtraccionDAO;
import persistencia.genericos.DAOFactory;
import persistencia.genericos.ItinerarioDAO;
import persistencia.genericos.UsuarioDAO;

public class Usuario {
	private Long IdUsuario;
	private String nombre;
	private int presupuesto;
	private double tiempoDisponible;
	private TipoAtraccion preferencia;
	private Posicion posicion;
	private String email;
	private String password;
	private boolean estaActivo;
	private boolean esAdmin;	
	private ArrayList<Atraccion> historialDeAtracciones = new ArrayList<Atraccion>();
	private ArrayList<Producto> itinerario = new ArrayList<Producto>();

	private Map<String, String> errors;

	public Usuario(String nombre, TipoAtraccion preferencia, int presupuesto, double tiempoDisponible) {
		this.nombre = nombre;
		this.preferencia = preferencia;
		this.presupuesto = presupuesto;
		this.tiempoDisponible = tiempoDisponible;
	}

	public Usuario(Long idUsuario, String nombre, int dinero, double tiempo, Posicion posicion, String email , String password, boolean estado, boolean esAdmin, TipoAtraccion preferencia) {
		this.IdUsuario = idUsuario;
		this.nombre = nombre;
		this.preferencia = preferencia;
		this.presupuesto = dinero;
		this.tiempoDisponible = tiempo;
		this.posicion = posicion;
		this.email = email;
		this.password = password;
		this.esAdmin = esAdmin;
		this.estaActivo = estado;
	}



	public Usuario() {
		
	}

	public void aceptarSugerencia(Producto sugerencia) {
		UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO(); 
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		ItinerarioDAO itinerarioDAO = DAOFactory.getItinerarioDAO();
			
		if (this.presupuesto >= sugerencia.getCosto() && this.tiempoDisponible >= sugerencia.getDuracion()
				&& sugerencia.getCupo() > 0 && !this.historialDeAtracciones.contains(sugerencia)) {
			
			this.itinerario.add(sugerencia);
			this.historialDeAtracciones.addAll(sugerencia.getAtraccionesIncluidas());
			this.tiempoDisponible -= sugerencia.getDuracion();
			this.presupuesto -= sugerencia.getCosto();
			
			for (Atraccion atr : sugerencia.getAtraccionesIncluidas()) {
				atr.restarCupo();
				atraccionDAO.actualizarCupo(atr);
			}
			usuarioDAO.actualizar(this);
			if(sugerencia.esPromo()) {
				itinerarioDAO.insertarItemItinerario(this.IdUsuario, sugerencia.getIdProducto(), 2);	
			}else {
				itinerarioDAO.insertarItemItinerario(this.IdUsuario, sugerencia.getIdProducto(), 1);
			}
		}
	}

	
	
	public ArrayList <Atraccion> atraccionesDelItinerario(){
		ArrayList <Atraccion> atracciones = new ArrayList <Atraccion>();
		
		for (Producto producto : this.itinerario) {
			atracciones.addAll(producto.getAtraccionesIncluidas());
		}
		
		return atracciones;
	}
	

	public void itinerarioToString() {
		int costoTotal = 0;
		double duracionTotal = 0;
		for (Producto prod : this.itinerario) {
			costoTotal += prod.getCosto();
			duracionTotal += prod.getDuracion();
		}

		System.out.println("-----------------------------------------------------------------------------");
		System.out.println("*******************************SU ITINERARIO*********************************");
		System.out.println("------------------------ ------------------------- --------------------------");
		System.out.printf("%-12s%-14s%-14s%-13s\n", "Nombre", this.getNombre(), "Preferencia", this.getPreferencia());
		System.out.printf("%-12s%-14d%-14s%-13.1f\n", "Presupuesto", this.getPresupuesto(), "Tiempo Disp.",
				this.getTiempoDisponible());
		System.out.println("-----------------------------------------------------------------------------\n");

		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<< ---Productos Adquiridos--- >>>>>>>>>>>>>>>>>>>>>>>>\n");
		for (int i = 0; i < this.itinerario.size(); i++) {
			System.out.println(i + 1 + "- " + itinerario.get(i) + "\n");

		}

		System.out.println("------------------------ ************************* --------------------------");
		System.out.println("_____________________________________________________________________________");
		System.out.printf("%-12s%-15s%-15d%-17s%-18.1f\n", " ", "COSTO TOTAL= ", costoTotal, "TIEMPO ESTIMADO",
				duracionTotal);
		System.out.println("_____________________________________________________________________________\n");
		System.out.printf("%-28s%-24s\n", " ", "GRACIAS POR SU VISITA");
		System.out.println("------------------------ ************************* --------------------------");

	}

	
	
	@Override
	public String toString() {
		return "Nombre: " + nombre + ", Preferencia: " + preferencia + ", Presupuesto: " + presupuesto
				+ ", TiempoDisponible: " + tiempoDisponible+ ", estado: " + estaActivo + ", admin: " + esAdmin;
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
		Usuario other = (Usuario) obj;
		return Objects.equals(nombre, other.nombre);
	}
	
	public Long getIdUsuario() {
		return this.IdUsuario;
	}

	public double getPosX() {
		return this.posicion.getX();
	}

	public double getPosY() {
		return this.posicion.getY();
	}
	
	public Posicion getPosicion() {
		return this.posicion;
	}

	public void setPosicion(Posicion posicion) {
		this.posicion = posicion;
	}

	public void setPresupuesto(int presupuesto) {
		this.presupuesto = presupuesto;
	}

	public ArrayList<Atraccion> getHistorialDeAtracciones() {
		return this.historialDeAtracciones;
	}

	public String getNombre() {
		return this.nombre;
	}

	public TipoAtraccion getPreferencia() {
		return this.preferencia;
	}

	public int getPresupuesto() {
		return this.presupuesto;
	}

	public double getTiempoDisponible() {
		return this.tiempoDisponible;
	}

	public ArrayList<Atraccion> getHistorialDeProductos() {
		return this.historialDeAtracciones;
	}

	public void setHistorialDeAtracciones(ArrayList<Atraccion> historialDeAtracciones) {
		this.historialDeAtracciones = historialDeAtracciones;
	}

	public void setItinerario(ArrayList<Producto> itinerario) {
		this.itinerario = itinerario;
	}

	public ArrayList<Producto> getItinerario() {
		return this.itinerario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String contraseña) {
		this.password = contraseña;
	}

	public boolean getEsAdmin() {
		return esAdmin;
	}

	public void setEsAdmin(boolean esAdmin) {
		this.esAdmin = esAdmin;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setTiempoDisponible(double tiempoDisponible) {
		this.tiempoDisponible = tiempoDisponible;
	}

	public void setPreferencia(TipoAtraccion preferencia) {
		this.preferencia = preferencia;
	}
	
	public boolean getEstaActivo() {
		return estaActivo;
	}

	public void setEstaActivo(boolean estaActivo) {
		this.estaActivo = estaActivo;
	}

	public boolean esValido() {
		validate();
		return errors.isEmpty();
	}
	
	
	
		
	public Map<String, String> getErrors() {
		return this.errors;
	}

	public void validate() {
		errors = new HashMap<String, String>();

		if ( this.nombre == "") {
			errors.put("nombre", "No debe ser vacio");
		}
		if (this.presupuesto <= 0) {
			errors.put("presupuesto", "Debe ser positivo");
		}
		if (this.tiempoDisponible <= 0) {
			errors.put("tiempo", "Debe ser positivo");
		}
		if (this.email == "") {
			errors.put("email", "No debe ser vacio");
		}
		if (this.password == "") {
			errors.put("", "No debe ser vacio");
		}
		
	}
	
	public boolean puedePermitirse(Atraccion atraccion) {
		return atraccion.getCosto() <= this.presupuesto;
	}

	public boolean puedeAsistir(Atraccion atraccion) {
		return atraccion.getDuracion() <= this.tiempoDisponible;
	}
	
	public void agregarAlItinerario(Atraccion atraccion) {
		this.presupuesto -= atraccion.getCosto();
		this.tiempoDisponible -= atraccion.getDuracion();
	}
	
}
