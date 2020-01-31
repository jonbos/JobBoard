package view;

import java.util.List;
import java.util.Scanner;

import controller.EmployerHelper;
import model.Employer;

public class CLI {
	static Scanner in = new Scanner(System.in);
	static EmployerHelper empHelper = new EmployerHelper();

	public static void main(String[] args) {
		runMenu();
	}

	private static void runMenu() {
		boolean quit = false;
		System.out.println("--- Welcome to Job Search! ---");
		while (!quit) {
			System.out.println("*  Select an item:");
			System.out.println("*  1 -- Add an item");
			System.out.println("*  2 -- Edit an item");
			System.out.println("*  3 -- Delete an item");
			System.out.println("*  4 -- View the list");
			System.out.println("*  5 -- Exit the awesome program");
			System.out.print("*  Your selection: ");
			int selection = in.nextInt();
			in.nextLine();

			if (selection == 1) {
				addAnEmployer();
			} else if (selection == 2) {
				editAnEmployer();
			} else if (selection == 3) {
				deleteAnEmployer();
			} else if (selection == 4) {
				viewEmployerList();
			} else {
				empHelper.cleanUp();
				System.out.println("   Goodbye!   ");
				quit = true;
			}
		}
	}

	private static void viewEmployerList() {
		List<Employer> allEmployers = empHelper.showAllEmployers();
		for (Employer emp : allEmployers) {
			System.out.println(emp.toString());
		}
	}

	private static void deleteAnEmployer() {
		System.out.print("Enter the employer to delete: ");
		String empName = in.nextLine();
		System.out.print("Enter the employer location: ");
		String location = in.nextLine();
		Employer toDelete = new Employer(empName, location);
		empHelper.deleteItem(toDelete);
	}

	private static void editAnEmployer() {
		List<Employer> foundItems;
		System.out.println("Enter employer name: ");
		String empName = in.nextLine();
		foundItems = empHelper.searchForEmployerByName(empName);
		if (!foundItems.isEmpty()) {
			System.out.println("Results");
			for (Employer emp : foundItems) {
				System.out.println(emp.getId() + ": " + emp.toString());
			}
			System.out.println("Which ID to edit: ");
			int idToEdit = in.nextInt();
			
			Employer toEdit = empHelper.searchForItemById(idToEdit);
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
			empHelper.updateItem(toEdit);

		} else {
			System.out.println("---- No results found");
		}
	}

	private static void addAnEmployer() {
		// TODO Auto-generated method stub
		System.out.print("Enter employer name: ");
		String empName = in.nextLine();
		System.out.print("Enter employer location: ");
		String location = in.nextLine();
		Employer toAdd = new Employer(empName, location);
		empHelper.insertItem(toAdd);
	}

}
