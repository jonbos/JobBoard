package view;

import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.text.WordUtils;

import controller.EmployerDAO;
import controller.JobDAO;
import model.Employer;
import model.Job;

public class JobView implements EntityView {
	private static Scanner in;
	private static JobDAO jobHelper = new JobDAO();
	private static EmployerDAO empHelper = new EmployerDAO();
	private static EmployerView empView;

	public JobView(Scanner in) {
		JobView.empView = new EmployerView(in);
		JobView.in = in;
	}

	@Override
	public void add() {
		System.out.print("Enter job title: ");
		String jobTitle = in.nextLine();
		empView.view();
		System.out.print("Enter employer id: ");
		int empID = in.nextInt();
		Employer e = empHelper.searchForEmployerById(empID);
		Job toAdd = new Job(jobTitle, e);
		jobHelper.insert(toAdd);
	}

	@Override
	public void view() {
		List<Job> allJobs = jobHelper.getAll();
		printJobsList(allJobs);
	}

	@Override
	public void edit() {
		List<Job> foundItems;
		System.out.print("Enter job title: ");
		String jobTitle = in.nextLine();
		foundItems = jobHelper.searchForJobByTitle(jobTitle);
		if (!foundItems.isEmpty()) {
			printJobsList(foundItems);
			System.out.print("Which ID to edit: ");
			int idToEdit = in.nextInt();

			Job toEdit = jobHelper.get(idToEdit);
			System.out.println("Retrieved " + toEdit.getTitle());
			System.out.println("1 : Update Title");
			System.out.println("2 : Update Description");
			System.out.println("3 : Update Employer");
			System.out.print("Your selection: ");

			int update = in.nextInt();
			in.nextLine();

			if (update == 1) {
				System.out.print("New Title: ");
				String newTitle = in.nextLine();
				toEdit.setTitle(newTitle);
			} else if (update == 2) {
				System.out.print("New Description: ");
				String newDesc = in.nextLine();
				toEdit.setJobDescription(newDesc);
			} else if (update == 3) {
				empView.view();
				System.out.println("New Employer: ");
				int newEmpID = in.nextInt();
				Employer e = empHelper.searchForEmployerById(newEmpID);
				toEdit.setEmployer(e);
			}
			jobHelper.update(toEdit);

		} else {
			System.out.println("---- No results found");
		}
	}

	@Override
	public void delete() {
		view();
		System.out.print("Enter the job ID to delete: ");
		int id = in.nextInt();
		Job j = jobHelper.get(id);
		jobHelper.delete(j);
	}

	private static void printHeader() {
		System.out.println(
				"---------------------------------------------------------------------------------------------------------------------");
		String format = "%1$-4s %2$-40s %3$-40s %4$-10s";
		System.out.println(String.format(format, "ID", "Job Title", "Employer", "Date Posted"));
		System.out.println(
				"---------------------------------------------------------------------------------------------------------------------");
	}

	private static void printJobsList(List<Job> jobs) {
		printHeader();
		for (Job job : jobs) {
			String format = "%1$-4s %2$-40s %3$-40s %4$-10s";
			System.out.println(
					String.format(format, job.getId(), job.getTitle(), job.getEmployer().getName(), job.getCreated()));
		}
	}

	public void details() {
		view();
		System.out.print("Enter the job ID to show details: ");
		int id = in.nextInt();
		Job j = jobHelper.get(id);
		System.out.println(WordUtils.wrap("JOB TITLE: " + j.getTitle(), 100));
		System.out.println(WordUtils.wrap("EMPLOYER : " + j.getEmployer().getName(), 100));
		System.out.println(WordUtils.wrap("POST DATE: " + j.getCreated(), 100));
		System.out.println(WordUtils.wrap("EMPLOYER LOCATION : " + j.getEmployer().getLocation(), 100));

		System.out.print("DESCRIPTION: ");
		String desc = j.getJobDescription();
		String lines[] = desc.split("\\r?\\n");
		for (String line : lines) {
			System.out.println(WordUtils.wrap(line, 85));
		}
	}

	public void cleanUp() {
		jobHelper.cleanUp();
	}
}
