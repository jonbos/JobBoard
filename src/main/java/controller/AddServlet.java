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
 * Servlet implementation class AddServlet
 */
@WebServlet("/addServlet")
public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String type = request.getParameter("type");
		String path = "/index.jsp";

		if (type.equals("employer")) {
			EmployerDAO empDAO = new EmployerDAO();
			String empName = request.getParameter("empName");
			String empLocation = request.getParameter("empLocation");
			String empDescription = request.getParameter("empDescription");

			Employer emp = new Employer();
			emp.setName(empName);
			emp.setDescription(empDescription);
			emp.setLocation(empLocation);

			empDAO.insert(emp);

		} else if (type.equals("job")) {
			JobDAO jobDAO = new JobDAO();
			EmployerDAO empDAO = new EmployerDAO();

			String jobTitle = request.getParameter("jobTitle");
			String jobDescription = request.getParameter("jobDescription");
			Integer empID = Integer.parseInt(request.getParameter("jobEmployer"));
			Employer jobEmployer = empDAO.get(empID);

			Job job = new Job();
			job.setEmployer(jobEmployer);
			job.setJobDescription(jobDescription);
			job.setTitle(jobTitle);

			jobDAO.insert(job);

		}

		request.getServletContext().getRequestDispatcher(path).forward(request, response);

	}

}
