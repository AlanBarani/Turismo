package controlador.promociones;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet("/attractions/buy54.do")
public class ComprarPromocionServlet extends HttpServlet {

//	private static final long serialVersionUID = 3455721046062278592L;
//	private BuyAttractionService buyAttractionService;
//
//	@Override
//	public void init() throws ServletException {
//		super.init();
//		this.buyAttractionService = new BuyAttractionService();
//	}
//
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//		Integer attractionId = Integer.parseInt(req.getParameter("id"));
//		User user = (User) req.getSession().getAttribute("user");
//		Map<String, String> errors = buyAttractionService.buy(user.getId(), attractionId);
//		
//		User user2 = DAOFactory.getUserDAO().find(user.getId());
//		req.getSession().setAttribute("user", user2);
//		
//		if (errors.isEmpty()) {
//			req.setAttribute("flash", "¡Gracias por comprar!");
//		} else {
//			req.setAttribute("errors", errors);
//			req.setAttribute("flash", "No ha podido realizarse la compra");
//		}
//
//		RequestDispatcher dispatcher = getServletContext()
//				.getRequestDispatcher("/attractions/index.do");
//		dispatcher.forward(req, resp);
//	}
}
