package controlador.promociones;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Atraccion;
import modelo.Producto;
import persistencia.genericos.AtraccionDAO;
import persistencia.genericos.DAOFactory;
import servicios.AtraccionService;
import servicios.PromocionesService;

@WebServlet("/promociones/editar.do")
public class EditarPromocionServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = -1911854169055374360L;
	private AtraccionService atraccionesService;
	private PromocionesService promocionesService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.atraccionesService = new AtraccionService();
		this.promocionesService = new PromocionesService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ArrayList<Atraccion> listaAtracciones = atraccionesService.listarAtracciones();
		req.setAttribute("listaAtracciones", listaAtracciones);
		
		Long id = Long.parseLong(req.getParameter("id"));

		Producto promocion = promocionesService.buscarPorId(id);
		req.setAttribute("promocion", promocion);

		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/vistas/promociones/editar.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Long idProducto = Long.parseLong(req.getParameter("idProducto"));
		
		Long atr1 = Long.parseLong(req.getParameter("atraccion1"));
		Long atr2 = Long.parseLong(req.getParameter("atraccion2"));
				

		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		Atraccion a1 = atraccionDAO.buscarPorIdAtraccion(atr1);
		Atraccion a2 = atraccionDAO.buscarPorIdAtraccion(atr2);
		
		String nombre = req.getParameter("nombre");
		Integer costo = Integer.parseInt(req.getParameter("costo"));
				
		Producto promocion = promocionesService.actualizarAbsol(idProducto, nombre,  a1, a2, costo);

		if (promocion.esValido()) {
			resp.sendRedirect("/promociones/promociones.jsp");
		} else {
			req.setAttribute("attraction", promocion);

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/vistas/promociones/promociones.jsp");
			dispatcher.forward(req, resp);
		}

	}

}
