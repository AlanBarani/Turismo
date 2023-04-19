package controlador.sesion;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 2642253690523257140L;

	@Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getSession().removeAttribute("nombreUsuario");
		req.getSession().removeAttribute("idUsuario");
		req.getSession().removeAttribute("usr");
		
		req.setAttribute("flash", "Cerraste sesion correctamente " );

		RequestDispatcher dispatcher = getServletContext()
  		      .getRequestDispatcher("/index.jsp");
  		    dispatcher.forward(req, resp); 	
    }
}
