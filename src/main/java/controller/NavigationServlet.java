package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NavigationServlet
 */
@WebServlet("/navigationServlet")
public class NavigationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NavigationServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = "/index.jsp";
		String type = request.getParameter("type");
		String action = request.getParameter("action");
		Integer id = Integer.parseInt(request.getParameter("id"));

		DAO dao = DAOFactory.getDAO(type);

		if (action.equals("delete")) {
			dao.delete(dao.get(id));
		} else if (action.equals("edit")) {
			Object toEdit = dao.get(id);
			request.setAttribute("toEdit", toEdit);
			path = "/edit.jsp?type=".concat(type);
		}

		request.getServletContext().getRequestDispatcher(path).forward(request, response);
	}

}
