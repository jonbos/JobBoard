package view;

import java.util.Scanner;

import controller.EmployerHelper;
import controller.JobHelper;

public class CLI {
	static Scanner in = new Scanner(System.in);
	
	static EmployerHelper empHelper = new EmployerHelper();
	static JobHelper jobHelper = new JobHelper();
	
	static JobView jobView = new JobView(in);
	static EmployerView empView = new EmployerView(in);

	
	final static String MENU_PROMPT = "Select an item:\n*  1 -- Add an item\n*  2 -- Edit an item\n*  3 -- Delete an item\n*  4 -- View the list\n*  5 -- View details\n*  6 -- Exit the awesome program\n*  Your selection: ";

	private static void add() {
		String empOrJob = promptEmpOrJob("ADD");

		switch (empOrJob) {
		case "EMPLOYER":
			empView.add();
			break;
		case "JOB":
			jobView.add();
			break;
		case "BACK":
			return;
		default:
			break;
		}
	}

	private static void delete() {
		String empOrJob = promptEmpOrJob("DELETE");

		switch (empOrJob) {
		case "EMPLOYER":
			empView.delete();
			break;
		case "JOB":
			jobView.delete();
			break;
		case "BACK":
			return;
		default:
			break;
		}
	}
	
	private static void edit() {
		String empOrJob = promptEmpOrJob("EDIT");

		switch (empOrJob) {
		case "EMPLOYER":
			empView.edit();
			break;
		case "JOB":
			jobView.edit();
			break;
		case "BACK":
			return;
		default:
			break;
		}
	}


	public static void main(String[] args) {
		runMenu();
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

	private static void view() {
		String empOrJob = promptEmpOrJob("VIEW");

		switch (empOrJob) {
		case "EMPLOYER":
			empView.view();
			break;
		case "JOB":
			jobView.view();
			break;
		case "BACK":
			return;
		default:
			break;
		}
	}

	private static void viewDetails() {
		String empOrJob = promptEmpOrJob("VIEW DETAILS OF");

		switch (empOrJob) {
		case "EMPLOYER":
			empView.details();
			break;
		case "JOB":
			jobView.details();
			break;
		case "BACK":
			return;
		default:
			break;
		}
	}
}
