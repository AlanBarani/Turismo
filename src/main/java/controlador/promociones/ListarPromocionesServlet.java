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
import modelo.Producto;
import servicios.PromocionesService;

@WebServlet("/promociones.do")
public class ListarPromocionesServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = 4823226780206635727L;
	private PromocionesService promocionesService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.promocionesService = new PromocionesService();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ArrayList<Producto> promociones = promocionesService.listar();
		req.setAttribute("promociones", promociones);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/vistas/promociones/promociones.jsp");
		dispatcher.forward(req, resp);
	}
	
}
