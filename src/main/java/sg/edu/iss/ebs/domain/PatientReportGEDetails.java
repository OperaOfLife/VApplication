package sg.edu.iss.ebs.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class PatientReportGEDetails 
{
	@Id
	@GeneratedValue
	private int id ;
	
	private String intensityG;
	
	private String intensityE;
	
	
	private String itemName;
	
	

	@ManyToOne()
	@JoinColumn(name="reportId", referencedColumnName = "reportId")    
	private PatientReport reportGE;



	public PatientReportGEDetails(int id, String intensityG, String intensityE,PatientReport reportGE) 
	{
		super();
		this.id = id;
		this.intensityG = intensityG;
		this.intensityE = intensityE;
		this.reportGE = reportGE;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getIntensityG() {
		return intensityG;
	}



	public void setIntensityG(String intensityG) {
		this.intensityG = intensityG;
	}



	public String getIntensityE() {
		return intensityE;
	}



	public void setIntensityE(String intensityE) {
		this.intensityE = intensityE;
	}



	public String getItemName() {
		return itemName;
	}



	public void setItemName(String itemName) {
		this.itemName = itemName;
	}



	public PatientReport getReportGE() {
		return reportGE;
	}



	public void setReportGE(PatientReport reportGE) {
		this.reportGE = reportGE;
	}
	
	
	

}
