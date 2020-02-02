package view;

import java.util.Scanner;

public class ViewFactory {
	Scanner in;
	private JobView jobView;
	private EmployerView empView;
	
	
	public ViewFactory(Scanner in) {
		jobView = new JobView(in);
		empView = new EmployerView(in);
	}
	
	public EntityView getView(String viewType) {
		switch (viewType) {
		case "JOB":
			return jobView;
		case "EMPLOYER":
			return empView;
		default:
			return null;
		}
	}
}
