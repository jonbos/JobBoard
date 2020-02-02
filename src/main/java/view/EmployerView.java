package view;

import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.text.WordUtils;

import controller.EmployerHelper;
import model.Employer;

public class EmployerView implements EntityView {
	private static Scanner in;
	private static EmployerHelper empHelper = new EmployerHelper();

	public EmployerView(Scanner in) {
		EmployerView.in = in;
	}

	@Override
	public void add() {
		System.out.print("Enter employer name: ");
		String empName = in.nextLine();
		System.out.print("Enter employer location: ");
		String location = in.nextLine();
		Employer toAdd = new Employer(empName, location);
		empHelper.insertItem(toAdd);
	}

	@Override
	public void view() {
		List<Employer> allEmployers = empHelper.showAllEmployers();
		printEmployerList(allEmployers);
	}

	@Override
	public void edit() {
		List<Employer> foundItems;
		System.out.print("Enter employer name: ");
		String empName = in.nextLine();
		foundItems = empHelper.searchForEmployerByName(empName);
		if (!foundItems.isEmpty()) {
			printEmployerList(foundItems);
			System.out.print("Which ID to edit: ");
			int idToEdit = in.nextInt();

			Employer toEdit = empHelper.searchForEmployerById(idToEdit);
			System.out.println("Retrieved " + toEdit.getName());
			System.out.println("1 : Update Name");
			System.out.println("2 : Update Location");
			System.out.println("3 : Update Description");
			System.out.print("Your selection: ");

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

	@Override
	public void delete() {
		view();
		System.out.print("Enter the employer ID to delete: ");
		int empID = in.nextInt();
		Employer e = empHelper.searchForEmployerById(empID);
		empHelper.deleteEmployer(e);
	}

	private static void printEmployerHeader() {
		System.out
				.println(String.format("----------------------------------------------------------------------------"));
		String format = "%1$-4s %2$-40s %3$-40s";
		System.out.println(String.format(format, "ID", "Employer Name", "Employer Location"));
		System.out
				.println(String.format("----------------------------------------------------------------------------"));
	}

	private static void printEmployerList(List<Employer> allEmployers) {
		printEmployerHeader();
		for (Employer emp : allEmployers) {
			String format = "%1$-4s %2$-40s %3$-40s";
			System.out.println(String.format(format, emp.getId(), emp.getName(), emp.getLocation()));
		}
	}

	public void details() {
		view();
		System.out.print("Enter the employer ID to show details: ");
		int id = in.nextInt();
		Employer e = empHelper.searchForEmployerById(id);
		System.out.println(WordUtils.wrap("EMPLOYER NAME: " + e.getName(), 100));
		System.out.println(WordUtils.wrap("EMPLOYER LOCATION : " + e.getLocation(), 100));
		System.out.println("EMPLOYER DESCRIPTION:");
		String desc = e.getDescription();
		String lines[] = desc.split("\\r?\\n");
		for (String line : lines) {
			System.out.println(WordUtils.wrap(line, 100));
		}
	}

	public void cleanUp() {
		empHelper.cleanUp();
	}

}
