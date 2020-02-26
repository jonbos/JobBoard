import java.util.ArrayList;
import java.util.List;

import controller.EmployerDAO;
import model.Employer;
import model.Job;

/**
 * 
 */

/**
 * @author jonbos
 *
 */
public class EmployerTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Employer e = new Employer("Dahl's Foods", "Des Moines, IA");
		Job j1 = new Job("Checker", e);
		Job j2 = new Job("Customer Service", e);
		Job j3 = new Job("Produce", e);
		List<Job> listOfJobs = new ArrayList<>();
		listOfJobs.add(j1);
		listOfJobs.add(j2);
		listOfJobs.add(j3);
		e.setJobs(listOfJobs);
		EmployerDAO eDAO = new EmployerDAO();
		eDAO.insert(e);
		
		for (Job j : eDAO.searchForEmployerByName("Dahl's Foods").get(0).getJobs()) {
			System.out.println(j.toString());
		}
	}
}
