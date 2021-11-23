package sg.edu.iss.ebs.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class PatientReportDetails 
{
	@Id
	@GeneratedValue
	private int id ;
	
	private String intensity;
	
	
	private String itemName;
	
	

	@ManyToOne()
	@JoinColumn(name="reportId", referencedColumnName = "reportId")    
	private PatientReport report;
	
	/*
	 * @OneToOne private Item item;
	 */
	 
		/*
		 * @OneToOne private Category category;
		 */
	
	 public PatientReportDetails(int id, String intensity, PatientReport report) {
			super();
			this.id = id;
			this.intensity = intensity;
			this.report = report;
			/*
			 * this.item.setItemId(item.getItemId()); this.itemName= item.getItemName();
			 */
			//this.itemName=itemName;
			
		}


	public PatientReportDetails() {
		super();
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getIntensity() {
		return intensity;
	}


	public void setIntensity(String intensity) {
		this.intensity = intensity;
	}


	public PatientReport getReport() {
		return report;
	}


	public void setReport(PatientReport report) {
		this.report = report;
	}


	/*
	 * public Item getItem() { return item; }
	 * 
	 * 
	 * public void setItem(Item item) { this.item = item; }
	 */

	public String getItemName() {
		return itemName;
	}


	public void setItemName(String itemName) {
		this.itemName = itemName;
	}


	
	 
	 
	
	
}
