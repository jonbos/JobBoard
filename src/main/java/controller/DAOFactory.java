package controller;

public class DAOFactory {
	public static DAO getDAO(String type) {
		switch (type) {
		case "job":
			return new JobDAO();
		case "employer":
			return new EmployerDAO();
		default:
			return null;
		}
	}
}
