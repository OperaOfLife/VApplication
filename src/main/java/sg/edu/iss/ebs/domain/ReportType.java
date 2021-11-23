package sg.edu.iss.ebs.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class ReportType {

	
	@Id
	private String id;
	private String reportName;
	private String description;
	
	
	public ReportType(String id, String reportName, String description) {
		super();
		this.id = id;
		this.reportName = reportName;
		this.description = description;
	}


	public ReportType() {
		super();
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getReportName() {
		return reportName;
	}


	public void setReportName(String reportName) {
		this.reportName = reportName;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	
	

	
}
