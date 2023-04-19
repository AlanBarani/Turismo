package controlador.usuarios;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Posicion;
import modelo.TipoAtraccion;
import modelo.Usuario;
import persistencia.genericos.DAOFactory;
import persistencia.genericos.TipoAtraccionDAO;
import servicios.TipoAtraccionService;
import servicios.UsuarioService;

@WebServlet("/crearusuario.do")
public class CrearUsuarioServlet extends HttpServlet {

	private static final long serialVersionUID = -5912774995689358982L;
	private UsuarioService usuarioService;
	private TipoAtraccionService tipoAtraccionService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		this.usuarioService = new UsuarioService();
		this.tipoAtraccionService = new TipoAtraccionService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		List<TipoAtraccion> tiposAtracciones =  tipoAtraccionService.listar();
		req.setAttribute("tiposAtracciones", tiposAtracciones);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/vistas/usuarios/crear.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Long id             = (long) 0;
		String nombre       = req.getParameter("nombre");
		String email        = req.getParameter("email");
		String pass         = req.getParameter("pass");
		Integer presupuesto = Integer.parseInt(req.getParameter("presupuesto"));
		double tiempo       = Double.parseDouble(req.getParameter("tiempo"));
		TipoAtraccion preferencia = TipoAtraccion.valueOf(req.getParameter("preferencia"));
		Boolean estaActivo  = Boolean.valueOf(req.getParameter("estaActivo"));
		Boolean esAdmin     = Boolean.valueOf(req.getParameter("esAdmin"));
		Posicion pos        = new Posicion(1,1);
		

		Usuario usuarioCreado = usuarioService.crear(nombre, email, 
				pass, presupuesto, tiempo ,preferencia , estaActivo, esAdmin, pos);
		
		if (usuarioCreado.esValido()) {
			resp.sendRedirect("//localhost:8080/AppWebTierraMedia/listarusuarios.do");
			
		} else {
			List<TipoAtraccion> tiposAtracciones =  tipoAtraccionService.listar();
			
			req.setAttribute("tiposAtracciones", tiposAtracciones);
			req.setAttribute("usuario", usuarioCreado);
			req.setAttribute("flash", "ocurrio algun error - no se pudo agregar el usuario en la BD "+ usuarioService.erroresUsuario(usuarioCreado));

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/vistas/usuarios/crear.jsp");
			dispatcher.forward(req, resp);
		}
		
		
		

	}
	
}
