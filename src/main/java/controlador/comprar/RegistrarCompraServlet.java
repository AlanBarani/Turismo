package controlador.comprar;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Producto;
import modelo.Usuario;
import servicios.ProductoService;
import servicios.UsuarioService;



@WebServlet("/registrarcompra.do")
public class RegistrarCompraServlet extends HttpServlet implements Servlet {


	private static final long serialVersionUID = -5561410142734112962L;
	private UsuarioService usuarioService;
	private ProductoService productoService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		this.usuarioService = new UsuarioService();
		this.productoService = new ProductoService();

	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long idProducto = Long.parseLong(req.getParameter("idproducto"));
		Long idUsuario = Long.parseLong(req.getParameter("idusuario"));
		Boolean esPromo = Boolean.parseBoolean(req.getParameter("esp"));

		Usuario usuario = usuarioService.buscarID(idUsuario);
		Producto producto = (Producto)productoService.buscarPorID(idProducto,esPromo);
		
		boolean todoBien = productoService.aceptarSugerencia(producto, usuario);
		if (todoBien) {
			req.setAttribute("flash", "Se realizo la compra correctamente");	
		} else {
			req.setAttribute("flash", "No se pudo realizar la compra");
		}
		resp.sendRedirect("comprar.do?id="+usuario.getIdUsuario());
	}

}
