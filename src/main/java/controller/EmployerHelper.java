package controller;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Employer;

public class EmployerHelper {
	
	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("JobBoard");
	
	public void insertItem(Employer emp) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(emp);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<Employer> showAllEmployers(){
		EntityManager em = emf.createEntityManager();
		List<Employer> allItems = em.createQuery("SELECT emp FROM Employer emp").getResultList();
		return allItems;
	}

	public void deleteItem(Employer toDelete) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Employer> typedQuery = em.
				createQuery("SELECT emp FROM Employer emp WHERE emp.id = :id", Employer.class);
		typedQuery.setParameter("id", toDelete.getId());
		typedQuery.setMaxResults(1);
		
		Employer result = typedQuery.getSingleResult();
		
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}

	public Employer searchForItemById(int id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Employer found = em.find(Employer.class, id);
		em.close();
		return found;
	}

	public void updateItem(Employer toEdit) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}

	public List<Employer> searchForEmployerByName(String empName) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Employer> typedQuery = em.createQuery("SELECT emp FROM Employer emp where emp.name = :empName", Employer.class);
		typedQuery.setParameter("empName", empName);
		typedQuery.setMaxResults(1);
		List<Employer> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}

	public List<Employer> searchForItemByLocation(String location) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Employer> typedQuery = em.createQuery("SELECT emp FROM Employer emp WHERE emp.location = :location", Employer.class);
		typedQuery.setParameter("selectedItem", location);
		typedQuery.setMaxResults(1);
		List<Employer> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}
	
	public void cleanUp() {
		emf.close();
	}
}
