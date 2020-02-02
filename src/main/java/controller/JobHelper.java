package controller;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Job;

public class JobHelper {
	
	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("JobBoard");
	
	public void insertJob(Job job) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(job);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<Job> showAllJobs(){
		EntityManager em = emf.createEntityManager();
		List<Job> allItems = em.createQuery("SELECT job FROM Job job ORDER BY job.created DESC").getResultList();
		return allItems;
	}

	public void deleteJob(Job toDelete) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Job> typedQuery = em.
				createQuery("SELECT job FROM Job job WHERE job.id = :id", Job.class);
		typedQuery.setParameter("id", toDelete.getId());
		typedQuery.setMaxResults(1);
		
		Job result = typedQuery.getSingleResult();
		
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}

	public Job searchForJobById(int id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Job found = em.find(Job.class, id);
		em.close();
		return found;
	}

	public void updateJob(Job toEdit) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}

	public List<Job> searchForJobByTitle(String jobTitle) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		//SEARCH
		TypedQuery<Job> typedQuery = em.createQuery("SELECT job FROM Job job WHERE (0 < LOCATE(:jobTitle, job.title))", Job.class);
		typedQuery.setParameter("jobTitle", jobTitle);
		List<Job> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}

	public List<Job> searchForJobByEmployerName(String empName) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Job> typedQuery = em.createQuery("SELECT job FROM Job job WHERE job.emp.name = :empName", Job.class);
		typedQuery.setParameter("empName", empName);
		typedQuery.setMaxResults(1);
		List<Job> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}
	
	public void cleanUp() {
		emf.close();
	}
}
