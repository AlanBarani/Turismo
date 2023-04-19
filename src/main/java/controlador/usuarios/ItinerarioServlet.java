package controlador.usuarios;

import java.io.IOException;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/itinerario.do")
public class ItinerarioServlet extends HttpServlet implements Servlet {

 	private static final long serialVersionUID = 3713847803267341308L;

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	req.getServletContext().getRequestDispatcher("/vistas/itinerario.jsp").forward(req, resp);
    }

	
}
