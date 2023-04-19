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
import servicios.AtraccionesService;
import servicios.PromocionesService;
import servicios.TipoAtraccionService;

@WebServlet("/promociones/crear.do")
public class CrearPromocionServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = 5329733344268364173L;
	private AtraccionesService atraccionesService;
	private PromocionesService promocionesService;
	private TipoAtraccionService tipoAtraccionService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.atraccionesService = new AtraccionesService();
		this.promocionesService = new PromocionesService();
		this.tipoAtraccionService = new TipoAtraccionService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//List<TipoAtraccion> listaTiposAtraccion = tipoAtraccionService.listar();
		ArrayList<String> listaTiposAtraccion = atraccionesService.listarTiposAtraccion();
		req.setAttribute("listaTiposAtraccion", listaTiposAtraccion);

		ArrayList<String> tiposPromo = promocionesService.listarTiposPromo();
		req.setAttribute("tiposPromo", tiposPromo);

		ArrayList<Atraccion> listaAtracciones = atraccionesService.listarAtracciones();
		req.setAttribute("listaAtracciones", listaAtracciones);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/vistas/promociones/crear.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer tipoPromocion = Integer.parseInt(req.getParameter("tipoPromo"));

		String tipoAtraccion = req.getParameter("tiposAtraccion");

		Long atr1 = Long.parseLong(req.getParameter("atraccion1"));
		Long atr2 = Long.parseLong(req.getParameter("atraccion2"));
//		Long atr3 = Long.parseLong(req.getParameter("atraccion3"));
//		if (req.getParameter("atraccion3") == null && Integer.parseInt(req.getParameter("atraccion3")) < 1) {
//			atr3 = Long.parseLong(req.getParameter("atraccion3"));
//		} 
		
		Long atr3 ;
		if (req.getParameter("atraccion3") == null) {
			atr3 = (long)1;
		} else {
			atr3 = Long.parseLong(req.getParameter("atraccion3"));
		}
		
		
		
		
		

		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		Atraccion a1 = atraccionDAO.buscarPorIdAtraccion(atr1);
		Atraccion a2 = atraccionDAO.buscarPorIdAtraccion(atr2);
		Atraccion a3 = atraccionDAO.buscarPorIdAtraccion(atr3);

		String nombre = req.getParameter("nombre");
		Integer costo = Integer.parseInt(req.getParameter("costo"));
		
		Integer descuento = Integer.parseInt(req.getParameter("descuento"));
		
		System.out.println("A VER EL TIPO");
		System.out.println(tipoPromocion);

				
		Producto promocion = promocionesService.crear(tipoPromocion, nombre, tipoAtraccion, a1, a2, a3, costo,
				descuento);

		System.out.println("VOLVIENDO DEL CREAR");
		System.out.println(promocion.toString());
						
		System.out.println(promocion.esValido());
		
		if (promocion.esValido()) {
			resp.sendRedirect("../promociones.do");
		} else {
			req.setAttribute("attraction", promocion);

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/vistas/promociones/promociones.jsp");
			dispatcher.forward(req, resp);
		}

	}

}
