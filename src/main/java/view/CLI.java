package view;

import java.util.Scanner;

public class CLI {
	static Scanner in = new Scanner(System.in);
	
	static ViewFactory viewFactory = new ViewFactory(in);
	
	final static String MENU_PROMPT = 
			"Select an item:\n*  "
			+ "1 -- Add an item\n*  "
			+ "2 -- Edit an item\n*  "
			+ "3 -- Delete an item\n*  "
			+ "4 -- View the list\n*  "
			+ "5 -- View details\n*  "
			+ "6 -- Exit the awesome program\n*  "
			+ "Your selection: ";

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
				viewFactory.getView(promptEmpOrJob("ADD")).add();
			} else if (selection == 2) {
				viewFactory.getView(promptEmpOrJob("EDIT")).edit();
			} else if (selection == 3) {
				viewFactory.getView(promptEmpOrJob("DELETE")).delete();
			} else if (selection == 4) {
				viewFactory.getView(promptEmpOrJob("VIEW")).view();
			} else if (selection == 5) {
				viewFactory.getView(promptEmpOrJob("VIEW DETAILS OF")).details();
			} else {
				cleanUp();
				System.out.println("   Goodbye!   ");
				quit = true;
			}
		}
	}
	
	private static String promptEmpOrJob(String action) {
		System.out.println(String.format("*  What would you like to %s?:", action));
		System.out.println(String.format("*  1 -- %s EMPLOYERS", action));
		System.out.println(String.format("*  2 -- %s JOBS", action));
		System.out.print("*  Your selection: ");

		int choice = in.nextInt();
		in.nextLine();

		switch (choice) {
		case 1:
			return "EMPLOYER";
		case 2:
			return "JOB";
		default:
			return null;
		}
	}

	private static void cleanUp() {
		viewFactory.getView("JOB").cleanUp();
		viewFactory.getView("EMPLOYER").cleanUp();
	}
}
