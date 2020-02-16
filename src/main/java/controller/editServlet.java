package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Employer;
import model.Job;

/**
 * Servlet implementation class editServlet
 */
@WebServlet("/editServlet")
public class editServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public editServlet() {
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
		String id = request.getParameter("id");
		String type = request.getParameter("type");
		response.getWriter().append(type + " ").append(id);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type = request.getParameter("type");
		Integer id = Integer.parseInt(request.getParameter("id"));
		String path = "/index.jsp";

		if (type.equals("job")) {
			JobDAO jobDAO = new JobDAO();
			EmployerDAO empDAO = new EmployerDAO();

			String jobTitle = request.getParameter("jobTitle");
			String jobDescription = request.getParameter("jobDescription");
			Integer empID = Integer.parseInt(request.getParameter("jobEmployer"));
			Employer jobEmployer = empDAO.get(empID);

			Job job = jobDAO.get(id);

			job.setEmployer(jobEmployer);
			job.setJobDescription(jobDescription);
			job.setTitle(jobTitle);
			
			jobDAO.update(job);
			
			path = "/viewDetailsServlet?type=job&id=".concat(id.toString());


		} else if (type.equals("employer")) {
			EmployerDAO empDAO = new EmployerDAO();
			Employer emp = empDAO.get(id);
			String empName = request.getParameter("empName");
			String empLoc = request.getParameter("empLocation");
			String empDesc = request.getParameter("empDescription");
			emp.setName(empName);
			emp.setDescription(empDesc);
			emp.setLocation(empLoc);
			empDAO.update(emp);
			path = "/viewDetailsServlet?type=employer&id=".concat(id.toString());
		}
		request.getServletContext().getRequestDispatcher(path).forward(request, response);
	}

}
