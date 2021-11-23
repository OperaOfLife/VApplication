package sg.edu.iss.ebs.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Item {

	@Id
	private String itemId;
	private String itemName;
	
	@Lob
	private String description;
	private String imageSource;
	
	
	
	@ManyToOne()
	@JoinColumn(name="categoryId", referencedColumnName = "categoryId", insertable = false, updatable = false)    
	private Category category;



	public Item(String itemId, String itemName, String description, String imageSource, Category category) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.description = description;
		this.imageSource = imageSource;
		this.category = category;
	}



	public Item() {
		super();
	}



	public String getItemId() {
		return itemId;
	}



	public void setItemId(String itemId) {
		this.itemId = itemId;
	}



	public String getItemName() {
		return itemName;
	}



	public void setItemName(String itemName) {
		this.itemName = itemName;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getImageSource() {
		return imageSource;
	}



	public void setImageSource(String imageSource) {
		this.imageSource = imageSource;
	}



	public Category getCategory() {
		return category;
	}



	public void setCategory(Category category) {
		this.category = category;
	}
	
	
	
	
	/*
	 * @ManyToOne()
	 * 
	 * @JoinColumn(name="Patient_Report_Id", referencedColumnName = "id", insertable
	 * = false, updatable = false) private PatientReport patientReport;
	 */
	
}
