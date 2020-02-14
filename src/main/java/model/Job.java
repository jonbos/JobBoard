package model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "jobs")
public class Job {

	@Column(name = "title")
	private String title;

	@Column(name = "job_description")
	private String jobDescription;

	@ManyToOne
	private Employer employer;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date", updatable = false)
	@CreationTimestamp
	private Date created;

	public Date getCreated() {
		return created;
	}

	@PrePersist
	protected void onCreate() {
		created = new Date();
	}

	public Job() {

	}

	public Job(String jobTitle, Employer e) {
		this.employer = e;
		this.title = jobTitle;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String job_description) {
		this.jobDescription = job_description;
	}

	public Employer getEmployer() {
		return employer;
	}

	public void setEmployer(Employer employer) {
		this.employer = employer;
	}

	public int getId() {
		return id;
	}
}
