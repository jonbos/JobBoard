package view;

import org.apache.commons.lang3.text.WordUtils;
import java.util.List;
import java.util.Scanner;

import controller.EmployerHelper;
import controller.JobHelper;
import model.Employer;
import model.Job;

public class CLI {
	static Scanner in = new Scanner(System.in);
	
	static EmployerHelper empHelper = new EmployerHelper();
	static JobHelper jobHelper = new JobHelper();
	
	final static String MENU_PROMPT = "Select an item:\n*  1 -- Add an item\n*  2 -- Edit an item\n*  3 -- Delete an item\n*  4 -- View the list\n*  5 -- View details\n*  6 -- Exit the awesome program\n*  Your selection: ";

	public static void main(String[] args) {
		runMenu();
	}

	private static void runMenu() {
		boolean quit = false;
		System.out.println("--- Welcome to Job Search! ---");
		while (!quit) {
			System.out.print(MENU_PROMPT);
			int selection = in.nextInt();
			in.nextLine();

			if (selection == 1) {
				add();
			} else if (selection == 2) {
				edit();
			} else if (selection == 3) {
				delete();
			} else if (selection == 4) {
				view();
			} else if (selection == 5) {
				viewDetails();
			} else {
				empHelper.cleanUp();
				System.out.println("   Goodbye!   ");
				quit = true;
			}
		}
	}

	private static void viewDetails() {
		String empOrJob = promptEmpOrJob("VIEW DETAILS OF");

		switch (empOrJob) {
		case "EMPLOYER":
			viewEmployerDetails();
			break;
		case "JOB":
			viewJobDetails();
			break;
		case "BACK":
			return;
		default:
			break;
		}
	}

	private static void viewJobDetails() {
		viewJobList();
		System.out.print("Enter the job ID to show details: ");
		int id = in.nextInt();
		Job j = jobHelper.searchForJobById(id);
		System.out.println(WordUtils.wrap("JOB TITLE: "+j.getTitle(), 100));
		System.out.println(WordUtils.wrap("EMPLOYER : "+ j.getEmployer().getName(), 100));
		System.out.println(WordUtils.wrap("POST DATE: "+j.getCreated(), 100));
		System.out.println(WordUtils.wrap("EMPLOYER : "+ j.getEmployer().getName(), 100));
		System.out.println("DESCRIPTION:\n");
		String desc =  j.getJobDescription();
		String lines[] = desc.split("\\r?\\n");
		for (String line : lines) {
			System.out.println(WordUtils.wrap(line, 100));
		}
//		System.out.println(WordUtils.wrap("DESCRIPTION: "+ j.getJobDescription() , 100));
	}

	private static void viewEmployerDetails() {
		viewEmployerList();
		System.out.print("Enter the employer ID to show details: ");
		int id = in.nextInt();
		Employer e = empHelper.searchForEmployerById(id);
		System.out.println(WordUtils.wrap("EMPLOYER NAME: "+e.getName(), 100));
		System.out.println(WordUtils.wrap("EMPLOYER LOCATION : "+ e.getLocation(), 100));
		System.out.println("EMPLOYER DESCRIPTION:");
		String desc = e.getDescription();
		String lines[] = desc.split("\\r?\\n");
		for (String line : lines) {
			System.out.println(WordUtils.wrap(line, 100));
		}
	}

	public static String promptEmpOrJob(String action) {
		System.out.println(String.format("*  What would you like to %s?:", action));
		System.out.println(String.format("*  1 -- %s EMPLOYERS", action));
		System.out.println(String.format("*  2 -- %s JOBS", action));
		System.out.println("*  3 -- Return to main menu");
		System.out.print("*  Your selection: ");

		int choice = in.nextInt();
		in.nextLine();

		switch (choice) {
		case 1:
			return "EMPLOYER";
		case 2:
			return "JOB";
		case 3:
			return "BACK";
		default:
			return "";
		}
	}

	private static void add() {
		String empOrJob = promptEmpOrJob("ADD");

		switch (empOrJob) {
		case "EMPLOYER":
			addEmployer();
			break;
		case "JOB":
			addJob();
			break;
		case "BACK":
			return;
		default:
			break;
		}
	}

	private static void addJob() {
		System.out.print("Enter job title: ");
		String jobTitle = in.nextLine();
		viewEmployerList();
		System.out.print("Enter employer id: ");
		int empID = in.nextInt();
		Employer e = empHelper.searchForEmployerById(empID);
		Job toAdd = new Job(jobTitle, e);
		jobHelper.insertJob(toAdd);
	}

	private static void addEmployer() {
		// TODO Auto-generated method stub
		System.out.print("Enter employer name: ");
		String empName = in.nextLine();
		System.out.print("Enter employer location: ");
		String location = in.nextLine();
		Employer toAdd = new Employer(empName, location);
		empHelper.insertItem(toAdd);
	}

	private static void delete() {
		String empOrJob = promptEmpOrJob("DELETE");

		switch (empOrJob) {
		case "EMPLOYER":
			deleteEmployer();
			break;
		case "JOB":
			deleteJob();
			break;
		case "BACK":
			return;
		default:
			break;
		}
	}

	private static void deleteJob() {
		viewJobList();
		System.out.println("Enter the job ID to delete: ");
		int id = in.nextInt();
		Job j = jobHelper.searchForJobById(id);
		jobHelper.deleteJob(j);
	}

	private static void deleteEmployer() {
		viewEmployerList();
		System.out.print("Enter the employer ID to delete: ");
		int empID = in.nextInt();
		Employer e = empHelper.searchForEmployerById(empID);
		empHelper.deleteEmployer(e);
	}

	private static void edit() {
		String empOrJob = promptEmpOrJob("EDIT");

		switch (empOrJob) {
		case "EMPLOYER":
			editEmployer();
			break;
		case "JOB":
			editJob();
			break;
		case "BACK":
			return;
		default:
			break;
		}
	}

	private static void editJob() {
		List<Job> foundItems;
		System.out.println("Enter job title: ");
		String jobTitle = in.nextLine();
		foundItems = jobHelper.searchForJobByTitle(jobTitle);
		if (!foundItems.isEmpty()) {
			System.out.println("Results");
			for (Job j : foundItems) {
				System.out.println(j.getId() + ": " + j.getTitle());
			}
			System.out.println("Which ID to edit: ");
			int idToEdit = in.nextInt();

			Job toEdit = jobHelper.searchForJobById(idToEdit);
			System.out.println("Retrieved " + toEdit.getTitle());
			System.out.println("1 : Update Title");
			System.out.println("2 : Update Description");
			System.out.println("3 : Update Employer");

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
				viewEmployerList();
				System.out.println("New Employer: ");
				int newEmpID = in.nextInt();
				Employer e = empHelper.searchForEmployerById(newEmpID);
				toEdit.setEmployer(e);
			}
			jobHelper.updateJob(toEdit);

		} else {
			System.out.println("---- No results found");
		}
	}

	private static void editEmployer() {
		List<Employer> foundItems;
		System.out.print("Enter employer name: ");
		String empName = in.nextLine();
		foundItems = empHelper.searchForEmployerByName(empName);
		if (!foundItems.isEmpty()) {
			System.out.println("Results");
			for (Employer emp : foundItems) {
				System.out.println("ID: "+emp.getId() + " " + emp.toString());
			}
			System.out.print("Which ID to edit: ");
			int idToEdit = in.nextInt();

			Employer toEdit = empHelper.searchForEmployerById(idToEdit);
			System.out.println("Retrieved " + toEdit.getName());
			System.out.println("1 : Update Name");
			System.out.println("2 : Update Location");
			System.out.println("3 : Update Description");

			int update = in.nextInt();
			in.nextLine();

			if (update == 1) {
				System.out.print("New Store: ");
				String newName = in.nextLine();
				toEdit.setName(newName);
			} else if (update == 2) {
				System.out.print("New Location: ");
				String newLoc = in.nextLine();
				toEdit.setLocation(newLoc);
			} else if (update == 3) {
				System.out.print("New Description: ");
				String newDesc = in.nextLine();
				toEdit.setDescription(newDesc);
			}
			empHelper.updateEmployer(toEdit);

		} else {
			System.out.println("---- No results found");
		}
	}

	private static void printEmployerHeader() {
		String format = "%1$-4s %2$-40s %3$-40s";
		System.out.println(String.format(format, "ID", "Employer Name", "Employer Location"));
		System.out
				.println(String.format("----------------------------------------------------------------------------"));
	}

	private static void printJobHeader() {
		String format = "%1$-4s %2$-40s %3$-40s %4$-10s";
		System.out.println(String.format(format, "ID", "Job Title", "Employer", "Date Posted"));
		System.out
				.println(String.format("---------------------------------------------------------------------------------------------------------------------"));
	}

	private static void view() {
		String empOrJob = promptEmpOrJob("VIEW");

		switch (empOrJob) {
		case "EMPLOYER":
			viewEmployerList();
			break;
		case "JOB":
			viewJobList();
			break;
		case "BACK":
			return;
		default:
			break;
		}
	}

	private static void viewEmployerList() {
		List<Employer> allEmployers = empHelper.showAllEmployers();
		printEmployerHeader();
		for (Employer emp : allEmployers) {
			String format = "%1$-4s %2$-40s %3$-40s";
			System.out.println(String.format(format, emp.getId(), emp.getName(), emp.getLocation()));
		}
	}

	private static void viewJobList() {
		List<Job> allJobs = jobHelper.showAllJobs();
		printJobHeader();
		for (Job job : allJobs) {
			String format = "%1$-4s %2$-40s %3$-40s %4$-10s";
			System.out.println(String.format(format, job.getId(), job.getTitle(), job.getEmployer().getName(), job.getCreated()));
		}
	}
}
