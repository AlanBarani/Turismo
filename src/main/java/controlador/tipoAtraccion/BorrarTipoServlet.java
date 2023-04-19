package controlador.tipoAtraccion;

import java.io.IOException;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.TipoAtraccion;
import servicios.TipoAtraccionService;

@WebServlet("/borrarTipo.do")
public class BorrarTipoServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = -8038518217261869889L;
	private TipoAtraccionService tipoAtraccionService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.tipoAtraccionService = new TipoAtraccionService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		TipoAtraccion tipo = TipoAtraccion.valueOf(req.getParameter("tipo"));
		tipoAtraccionService.darDeBaja(tipo);

		resp.sendRedirect("listarTipos.do");
	}

}
