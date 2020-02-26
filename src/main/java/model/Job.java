package model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "jobs")
public class Job {

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date", updatable = false)
	@CreationTimestamp
	private Date created;

	@ManyToOne
	@JoinColumn(name="employer_id")
	private Employer employer;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "job_description")
	private String jobDescription;

	@Column(name = "title")
	private String title;

	public Job() {

	}

	public Job(String title) {
		super();
		this.title = title;
	}

	public Job(String jobTitle, Employer e) {
		this.employer = e;
		this.title = jobTitle;
	}

	public Date getCreated() {
		return created;
	}

	public Employer getEmployer() {
		return employer;
	}

	public int getId() {
		return id;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public String getTitle() {
		return title;
	}

	@PrePersist
	protected void onCreate() {
		created = new Date();
	}

	public void setEmployer(Employer employer) {
		this.employer = employer;
	}

	public void setJobDescription(String job_description) {
		this.jobDescription = job_description;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Job [created=" + created + ", employer=" + employer + ", id=" + id + ", jobDescription="
				+ jobDescription + ", title=" + title + "]";
	}
}
