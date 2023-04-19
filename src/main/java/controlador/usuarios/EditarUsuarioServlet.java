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

@WebServlet("/editarusuario.do")
public class EditarUsuarioServlet extends HttpServlet {

	private static final long serialVersionUID = -3257286338320037323L;
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
		Integer id = Integer.parseInt(req.getParameter("id"));

		Usuario usuario = usuarioService.buscarID((long) id);
		req.setAttribute("usuario", usuario);

		List<TipoAtraccion> tiposAtracciones = tipoAtraccionService.listar();
		// Antes de tener el service se usaba esto:
//		 TipoAtraccionDAO tipoAtraccionesDAO = DAOFactory.getTipoAtraccionDAO();
//		 ArrayList<TipoAtraccion> tiposAtracciones = tipoAtraccionesDAO.buscarTodos();
		req.setAttribute("tiposAtracciones", tiposAtracciones);
		// --------------------------------------------------------------------------------------

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/vistas/usuarios/editar.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Long id = (long) Integer.parseInt(req.getParameter("id"));
		String nombre = req.getParameter("nombre");
		String email = req.getParameter("email");
		String pass = req.getParameter("pass");
		Integer presupuesto = Integer.parseInt(req.getParameter("presupuesto"));
		double tiempo = Double.parseDouble(req.getParameter("tiempo"));
		TipoAtraccion preferencia = TipoAtraccion.valueOf(req.getParameter("preferencia"));
		Boolean estaActivo = Boolean.valueOf(req.getParameter("estaActivo"));
		Boolean esAdmin = Boolean.valueOf(req.getParameter("esAdmin"));
		Posicion pos = new Posicion(0, 0);

		Usuario usuarioEditado = usuarioService.actualizar(id, nombre, email, pass, presupuesto, tiempo, preferencia,
				estaActivo, esAdmin, pos);

		if (usuarioEditado.esValido()) {
			resp.sendRedirect("//localhost:8080/AppWebTierraMedia/listarusuarios.do");

		} else {
			req.setAttribute("usuarioEditado", usuarioEditado);

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/vistas/usuarios/editar.jsp");
			dispatcher.forward(req, resp);
		}
	}

}
