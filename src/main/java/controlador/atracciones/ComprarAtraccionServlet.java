package controlador.atracciones;

import java.io.IOException;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Usuario;
import persistencia.genericos.DAOFactory;
import servicios.ComprarAtraccionService;

@WebServlet("/atraccion/buy.do")
public class ComprarAtraccionServlet extends HttpServlet {

	

	private static final long serialVersionUID = 7262460473910033160L;
	private ComprarAtraccionService comprarAtraccionService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.comprarAtraccionService = new ComprarAtraccionService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Long atraccionId = Long.parseLong(req.getParameter("atraccionId"));
		Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");
		Map<String, String> errors = comprarAtraccionService.buy(usuario.getIdUsuario(), atraccionId);
		
		Usuario usuario2 = DAOFactory.getUsuarioDAO().buscarPorIdUsuario(usuario.getIdUsuario());
		req.getSession().setAttribute("usuario", usuario2);
		
		if (errors.isEmpty()) {
			req.setAttribute("flash", "¡Gracias por comprar!");
		} else {
			req.setAttribute("errors", errors);
			req.setAttribute("flash", "No ha podido realizarse la compra");
		}

		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/atracciones/index.do");
		dispatcher.forward(req, resp);
	}
}
