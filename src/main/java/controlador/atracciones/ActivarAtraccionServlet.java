package controlador.atracciones;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servicios.AtraccionService;

@WebServlet("/atracciones/activarAtraccion.do")
public class ActivarAtraccionServlet extends HttpServlet {
	private static final long serialVersionUID = 6854620691113208381L;
	private AtraccionService atraccionService;
	@Override
	public void init() throws ServletException {
		super.init();
		this.atraccionService = new AtraccionService();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long id = Long.parseLong(req.getParameter("id"));
		atraccionService.activar(id);
		resp.sendRedirect("/AppWebTierraMedia/atracciones/index.do");
	}
}
