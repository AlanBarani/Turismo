package controlador.atracciones;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Atraccion;
import modelo.TipoAtraccion;
import servicios.AtraccionService;
import servicios.TipoAtraccionService;

@WebServlet("/atracciones/create.do")
public class CreateAttractionServlet extends HttpServlet {

	private static final long serialVersionUID = 3801325811223907827L;
	private AtraccionService atraccionService;
	private TipoAtraccionService tipoAtraccionService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.atraccionService = new AtraccionService();
		this.tipoAtraccionService = new TipoAtraccionService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<TipoAtraccion> tiposAtracciones = tipoAtraccionService.listar();
		req.setAttribute("tiposAtracciones", tiposAtracciones);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/vistas/atracciones/create.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nombre = req.getParameter("nombre");
		TipoAtraccion tipo = TipoAtraccion.valueOf(req.getParameter("tipo"));
		Integer costo = Integer.parseInt(req.getParameter("costo"));
		Double duracion = Double.parseDouble(req.getParameter("duracion"));
		Integer cupo = Integer.parseInt(req.getParameter("cupo"));
		String img = req.getParameter("img");
		String descripcion = req.getParameter("descripcion");

		Atraccion atraccion = atraccionService.crear(nombre, tipo, costo, duracion, cupo, img, descripcion);
		if (atraccion.isValid()) {
			resp.sendRedirect("/AppWebTierraMedia/atracciones/index.do");
		} else {
			req.setAttribute("atraccion", atraccion);

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/vista/atraccion/create.jsp");
			dispatcher.forward(req, resp);
		}

	}

}
