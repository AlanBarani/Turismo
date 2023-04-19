package controlador.usuarios;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servicios.UsuarioService;

@WebServlet("/desactivarusuario.do")
public class DesactivarUsuarioServlet extends HttpServlet {

	private static final long serialVersionUID = -2302816370243783003L;
	private UsuarioService usuarioService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.usuarioService = new UsuarioService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));

		int result = usuarioService.desactivar((long)id);
		if (result == 1) {
			
			req.setAttribute("flash", "El usuario id: "+ id+ "fue desactivado exitosamente ");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/listarusuarios.do");
			dispatcher.forward(req, resp);
			
		} else
		{
			req.setAttribute("flash", "El usuario id: "+ id+ "no pudo ser desactivado ");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/listarusuarios.do");
			dispatcher.forward(req, resp);
		}
	}
	

	
}
