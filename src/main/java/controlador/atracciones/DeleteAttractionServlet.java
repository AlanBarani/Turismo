package controlador.atracciones;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Atraccion;
import servicios.AtraccionService;

@WebServlet("/atracciones/delete.do")
public class DeleteAttractionServlet extends HttpServlet {


	private static final long serialVersionUID = 967060433398681480L;
	private AtraccionService atraccionService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.atraccionService = new AtraccionService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Long idAtraccion = Long.parseLong(req.getParameter("id"));
		
		atraccionService.borrar(idAtraccion);
		
		resp.sendRedirect("/AppWebTierraMedia/atracciones/index.do");
	}
}
