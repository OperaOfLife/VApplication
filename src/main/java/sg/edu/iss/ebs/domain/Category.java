package sg.edu.iss.ebs.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Category {

	@Id
	private String categoryId;
	private String name;
	private String description;
	private String name_chinese;
	private String description_chinese;
	
	
	@OneToMany(targetEntity=Item.class, mappedBy="category",cascade=CascadeType.ALL, fetch = FetchType.LAZY)    
    private List<Item> item;
	
	

	
}
