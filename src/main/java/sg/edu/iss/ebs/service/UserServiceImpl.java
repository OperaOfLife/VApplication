package sg.edu.iss.ebs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import sg.edu.iss.ebs.domain.User;
import sg.edu.iss.ebs.repo.UserRepository;


@Service
public class UserServiceImpl implements UserService
{

	@Autowired
	UserRepository urepo;
	
	
	

	@Override
	public void createUser(User user) 
	{
		urepo.save(user);
	}

	
	
	
	  @Override public boolean authenticate(User user)
	  { 
		  User fromDB =  urepo.findUserByUserIdAndPassword(user.getUserId(), user.getPassword());
	       if (fromDB != null)
	    	   return true;
	       else
	    	   return false;
	       }
	 
	
	@Override
	public boolean authenticateRegister(User user)
	{
		User fromDB = urepo.findUserByUserId(user.getUserId());
		if (fromDB != null)
			return true;
		else
			return false;
	}

	@Override
	public User findByName(String nric) 
	{
		return urepo.findUserByUserId(nric);
	}
	
	
	@Override
	public void updatePassword(String id,String pwd) 
	{
		urepo.updatePwd(id, pwd);
	}
	
	public ResponseEntity<User> loginuser(String uname,String pwd) 
	{
		
		User userdetail = urepo.findUserByUserIdAndPassword(uname,pwd);
		
		return new ResponseEntity<User>(userdetail, null, HttpStatus.OK);
		
	}



	@Override 
	   public boolean authenticate(String uname, String pwd) { 
	    boolean check = false; 
	    User fromDB =  urepo.findUserByUserIdAndPassword(uname, pwd); 
	        if (fromDB != null) 
	         check = true; 
	        return check; 
	   }
	
	

	
}
