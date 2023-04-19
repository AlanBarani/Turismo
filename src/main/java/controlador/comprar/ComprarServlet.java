package controlador.comprar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Producto;
import modelo.Usuario;
import servicios.ItinerarioService;
import servicios.ProductoService;
import servicios.UsuarioService;

@WebServlet("/comprar.do")
public class ComprarServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = -2327681578618570633L;
	private ProductoService productoService;
	private UsuarioService usuarioService;

	@Override
	public void init() throws ServletException {
		super.init();
		productoService =  new ProductoService();
		usuarioService = new UsuarioService();
	}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long id = Long.parseLong(req.getParameter("id"));
		Usuario usuario = usuarioService.buscarID(id);    	
		ArrayList<Producto> listaProductos =  productoService.listarOrdenados(usuario);
		
		req.removeAttribute("usuario");
    	req.setAttribute("usuario", usuario);
		req.removeAttribute("listaProductos");
    	req.setAttribute("listaProductos", listaProductos);
    	
    	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/vistas/comprar.jsp");
		dispatcher.forward(req, resp);

    }


}
