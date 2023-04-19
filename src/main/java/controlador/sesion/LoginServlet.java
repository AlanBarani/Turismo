package controlador.sesion;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Usuario;
import servicios.UsuarioService;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 7869969339411899703L;
	private UsuarioService usuarioService;

	@Override
	public void init() throws ServletException {
		super.init();
		usuarioService = new UsuarioService();
	}
	
	

	@Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String email = req.getParameter("email");
    	String password = req.getParameter("password");
    	Usuario usr = usuarioService.login(email, password);
    	
    	
     	
    	if ( usr != null) {
    		req.getSession().setAttribute("nombreUsuario", usr.getNombre());
    		req.getSession().setAttribute("idUsuario", usr.getIdUsuario());
    		req.getSession().setAttribute("usr", usr);
    		req.setAttribute("id", usr.getIdUsuario());
    		
    		if (usr.getEsAdmin()) {
    			resp.sendRedirect("vistas/administrar.jsp");	
    		} else {
    			resp.sendRedirect("vistas/clientes.jsp");
    		}
    		
		} else {
			req.setAttribute("flash", "email o password incorrectos ");
			RequestDispatcher dispatcher = getServletContext()
	  		      .getRequestDispatcher("/vistas/login.jsp");
	  		    dispatcher.forward(req, resp);
		}
    }
}