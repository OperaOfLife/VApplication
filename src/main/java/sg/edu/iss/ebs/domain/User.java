package sg.edu.iss.ebs.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class User implements Serializable {

	@Id
	private String userId;
	private String name;
	private String gender;
	private String mobile;
	private String email;
	private String password;
	private Role role;
	
	/*
	 * @ManyToOne()
	 * 
	 * @JoinColumn(name="familyId", referencedColumnName = "familyId", insertable =
	 * false, updatable = false) private Family family;
	 */
	
	
	

	public User() {
		super();
	}
	
	




	public User(String userId, String name, String gender, String mobile, String email, String password, Role role
			) {
		super();
		this.userId = userId;
		this.name = name;
		this.gender = gender;
		this.mobile = mobile;
		this.email = email;
		this.password = password;
		this.role = role;
		
	}






	public String getUserId() {
		return userId;
	}






	public void setUserId(String userId) {
		this.userId = userId;
	}






	public String getName() {
		return name;
	}






	public void setName(String name) {
		this.name = name;
	}






	public String getGender() {
		return gender;
	}






	public void setGender(String gender) {
		this.gender = gender;
	}






	public String getMobile() {
		return mobile;
	}






	public void setMobile(String mobile) {
		this.mobile = mobile;
	}






	public String getEmail() {
		return email;
	}






	public void setEmail(String email) {
		this.email = email;
	}






	public String getPassword() {
		return password;
	}






	public void setPassword(String password) {
		this.password = password;
	}






	public Role getRole() {
		return role;
	}






	public void setRole(Role role) {
		this.role = role;
	}






	
	
	
	

}
