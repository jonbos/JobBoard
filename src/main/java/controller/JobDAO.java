package controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Job;

public class JobDAO implements DAO<Job> {

	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("JobBoard");

	public void cleanUp() {
		emf.close();
	}

	@Override
	public void delete(Job toDelete) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Job> typedQuery = em.createQuery("SELECT job FROM Job job WHERE job.id = :id", Job.class);
		typedQuery.setParameter("id", toDelete.getId());
		typedQuery.setMaxResults(1);

		Job result = typedQuery.getSingleResult();

		em.remove(result);
		em.getTransaction().commit();
		em.close();

	}

	@Override
	public Job get(int id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Job found = em.find(Job.class, id);
		em.close();
		return found;
	}

	@Override
	public List<Job> getAll() {
		EntityManager em = emf.createEntityManager();
		List<Job> allItems = em.createQuery("SELECT job FROM Job job ORDER BY job.created DESC").getResultList();
		em.close();
		return allItems;
	}

	@Override
	public void insert(Job job) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(job);
		em.getTransaction().commit();
		em.close();
	}

	public List<Job> searchForJobByEmployerId(int empId) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Job> typedQuery = em.createQuery("SELECT job FROM Job job WHERE job.employer.id = :empId",
				Job.class);
		typedQuery.setParameter("empId", empId);
		List<Job> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}

	public List<Job> searchForJobByEmployerName(String empName) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Job> typedQuery = em.createQuery("SELECT job FROM Job job WHERE job.employer.name = :empName",
				Job.class);
		typedQuery.setParameter("empName", empName);
		List<Job> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}

	public List<Job> searchForJobByTitle(String jobTitle) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		// SEARCH
		TypedQuery<Job> typedQuery = em.createQuery("SELECT job FROM Job job WHERE (0 < LOCATE(:jobTitle, job.title))",
				Job.class);
		typedQuery.setParameter("jobTitle", jobTitle);
		List<Job> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}

	@Override
	public void update(Job toEdit) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}
}
