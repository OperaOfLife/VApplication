package sg.edu.iss.ebs.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Family 
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id; 
    private String familyId;
    
    
    @OneToOne
    private User user;
   
	/*
	 * @OneToMany(targetEntity=User.class,
	 * mappedBy="family",cascade=CascadeType.ALL, fetch = FetchType.LAZY) private
	 * List<User> user;
	 */
   // private User user;//userId

}
