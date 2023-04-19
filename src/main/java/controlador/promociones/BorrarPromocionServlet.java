package controlador.promociones;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servicios.AtraccionService;
import servicios.PromocionesService;

@WebServlet("/promociones/borrar.do")
public class BorrarPromocionServlet extends HttpServlet {


	private static final long serialVersionUID = 967060433398681480L;
	private PromocionesService promocionesService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.promocionesService = new PromocionesService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Long idPromocion = Long.parseLong(req.getParameter("id"));
		
		promocionesService.borrar(idPromocion);
		
		resp.sendRedirect("/AppWebTierraMedia/promociones.do");
	}
}