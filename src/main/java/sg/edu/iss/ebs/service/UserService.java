package sg.edu.iss.ebs.service;

import org.springframework.http.ResponseEntity;

import sg.edu.iss.ebs.domain.User;



public interface UserService 
{
	public User findByName(String name);
	public void createUser(User user);
	public boolean authenticate(User user);
	public boolean authenticate(String uname,String pwd);
	public ResponseEntity<User> loginuser(String uname,String pwd);	
	boolean authenticateRegister(User user);
	public void updatePassword(String id, String pwd);
}
