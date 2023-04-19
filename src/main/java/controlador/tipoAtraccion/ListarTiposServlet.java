package controlador.tipoAtraccion;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.TipoAtraccion;
import servicios.TipoAtraccionService;

@WebServlet("/listarTipos.do")
public class ListarTiposServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = -6168865569485334512L;
	private TipoAtraccionService tipoAtraccionService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.tipoAtraccionService = new TipoAtraccionService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<TipoAtraccion> tiposAtraccion = tipoAtraccionService.listar();
		req.setAttribute("tiposAtraccion", tiposAtraccion);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/vistas/tiposAtracciones.jsp");
		dispatcher.forward(req, resp);
	}
}
