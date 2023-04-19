package controlador.atracciones;



import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Atraccion;
import modelo.Posicion;
import modelo.TipoAtraccion;
import servicios.AtraccionService;


@WebServlet("/atracciones/edit.do")
public class EditAttractionServlet extends HttpServlet {


	private static final long serialVersionUID = -7609872200602800107L;
	private AtraccionService atraccionService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.atraccionService = new AtraccionService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long idAtraccion = Long.parseLong(req.getParameter("id"));

		Atraccion atraccion = atraccionService.buscarId(idAtraccion);
		req.setAttribute("atraccion", atraccion);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/vistas/atracciones/edit.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long idAtraccion = Long.parseLong(req.getParameter("idAtraccion"));
		String nombre = req.getParameter("nombre");
		Integer costo = Integer.parseInt(req.getParameter("costo"));
		Double duracion = Double.parseDouble(req.getParameter("duracion"));
		Integer cupo = Integer.parseInt(req.getParameter("cupo"));
		String descripcion = req.getParameter("descripcion");
		

		Atraccion atraccion = atraccionService.actualizar( idAtraccion, nombre, costo,  duracion,  cupo, descripcion);

		if (atraccion.isValid()) {
			resp.sendRedirect("/AppWebTierraMedia/atracciones/index.do");
		} else {
			req.setAttribute("atraccion", atraccion);

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/vistas/atracciones/edit.jsp");
			dispatcher.forward(req, resp);
		}
	}
}
