package controlador.comprar;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Producto;
import modelo.Usuario;
import servicios.ProductoService;
import servicios.UsuarioService;

@WebServlet("/confirmarcompra.do")
public class ConfirmarCompraServlet extends HttpServlet {

	private static final long serialVersionUID = 2935401036925183164L;
	private ProductoService productoService;
	private UsuarioService usuarioService;

	
	@Override
	public void init() throws ServletException {
		super.init();
		this.usuarioService = new UsuarioService();
		this.productoService = new ProductoService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long idProducto = (long) Integer.parseInt(req.getParameter("idp"));
		Long idUsuario = (long) Integer.parseInt(req.getParameter("idu"));
		boolean esPromo = Boolean.parseBoolean(req.getParameter("esp"));

		Usuario usuario = usuarioService.buscarID(idUsuario);
		Producto producto = productoService.buscarPorID(idProducto,esPromo);
		
		req.setAttribute("usuario", usuario);
		req.setAttribute("producto", producto);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/vistas/confirmarcompra.jsp");
		dispatcher.forward(req, resp);
	}


}
