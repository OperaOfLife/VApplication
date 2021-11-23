package sg.edu.iss.ebs.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Pet {
	
	
		@Id
		private String petId;
		private String OwnerId;
		private String petName;
		private String gender;
		private String mobile;
		private String email;
		private String password;
		private Role role;

}
