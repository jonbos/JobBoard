package controller;

public class DAOFactory {

	public static JobHelper getDAO(String type) {
		switch (type) {
		case "job":
			return new JobHelper();
		case "employer":
			return new EmployerHelper();
		default:
			break;
		}
	}
}
