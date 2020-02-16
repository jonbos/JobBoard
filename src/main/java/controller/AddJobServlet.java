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
 * Servlet implementation class AddJobServlet
 */
@WebServlet("/addJobServlet")
public class AddJobServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddJobServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getServletContext().getRequestDispatcher("/addJob.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JobDAO jobDao = new JobDAO();
		EmployerDAO empDao = new EmployerDAO();
		Job job = new Job();
		Integer empId = Integer.parseInt(request.getParameter("empId"));
		String jobTitle = request.getParameter("jobTitle");
		String jobDesc = request.getParameter("jobDesc");
		Employer e = empDao.searchForEmployerById(empId);
		job.setEmployer(e);
		job.setJobDescription(jobDesc);
		job.setTitle(jobTitle);
		jobDao.insertJob(job);
		request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	}

}
