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
import servicios.AtraccionesService;
import servicios.PromocionesService;

@WebServlet("/promociones/editarAxB.do")
public class EditarPromocionAxBServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = -1911854169055374360L;
	private AtraccionesService atraccionesService;
	private PromocionesService promocionesService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.atraccionesService = new AtraccionesService();
		this.promocionesService = new PromocionesService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ArrayList<Atraccion> listaAtracciones = atraccionesService.listarAtracciones();
		req.setAttribute("listaAtracciones", listaAtracciones);
		
		Long id = Long.parseLong(req.getParameter("id"));

		Producto promocion = promocionesService.buscarPorId(id);
		req.setAttribute("promocion", promocion);

		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/vistas/promociones/editarAxB.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Long idProducto = Long.parseLong(req.getParameter("id"));
		
		Long atr1 = Long.parseLong(req.getParameter("atraccion1"));
		Long atr2 = Long.parseLong(req.getParameter("atraccion2"));
		Long atr3 = Long.parseLong(req.getParameter("atraccion3"));
		

		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		Atraccion a1 = atraccionDAO.buscarPorIdAtraccion(atr1);
		Atraccion a2 = atraccionDAO.buscarPorIdAtraccion(atr2);
		Atraccion a3 = atraccionDAO.buscarPorIdAtraccion(atr3);

		String nombre = req.getParameter("nombre");
		
		Producto promocion = promocionesService.actualizarAxB(idProducto, nombre, a1, a2, a3);
		
		
		if (promocion.esValido()) {
			System.out.println(promocion.toString());
			resp.sendRedirect("../promociones.do");
		} else {
			req.setAttribute("attraction", promocion);

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/vistas/promociones/promociones.jsp");
			dispatcher.forward(req, resp);
		}

	}

}
