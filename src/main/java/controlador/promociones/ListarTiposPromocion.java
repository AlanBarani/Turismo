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
import servicios.PromocionesService;

@WebServlet("/tipos-promociones.do")
public class ListarTiposPromocion extends HttpServlet implements Servlet {

	private static final long serialVersionUID = 6013786756316964729L;
	private PromocionesService promocionesService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.promocionesService = new PromocionesService();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ArrayList<String> tiposPromo = promocionesService.listarTiposPromo();		
		req.setAttribute("tiposPromo", tiposPromo);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/vistas/promociones/crear.jsp");
		dispatcher.forward(req, resp);
	}

}
