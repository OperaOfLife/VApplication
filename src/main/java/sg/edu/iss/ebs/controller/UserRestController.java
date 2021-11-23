package sg.edu.iss.ebs.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.transaction.Transactional;

import org.hibernate.mapping.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.iss.ebs.domain.User;
import sg.edu.iss.ebs.service.EmailService;
import sg.edu.iss.ebs.service.EmailServiceImpl;
import sg.edu.iss.ebs.service.UserService;




@RestController
@RequestMapping("/userRest")
public class UserRestController 
{

	
	@Autowired
	UserService uservice;
	
	@Autowired
	private EmailService eservice;
	
	
	@Autowired
	public void setUserInterface(UserService us) {
		this.uservice = us;
	}
	
	 @RequestMapping("/authenticate") 
	 public ResponseEntity<Boolean> login(@RequestParam String uname,@RequestParam String pwd) 
	 { 
	  boolean check =true; 
	   
	  if (uservice.authenticate(uname, pwd) == true) 
	   check = true; 
	  if (uservice.authenticate(uname, pwd) == false) 
	   check = false; 
	   
	  return new ResponseEntity<Boolean>(check, HttpStatus.OK); 
	 }
	
	
	 @Transactional
	 @RequestMapping("/forgotPwd") 
	 public ResponseEntity<Boolean> recover(@RequestParam String uname) 
	 { 
	  boolean check =true;
	  
	  String newPwd=UUID.randomUUID().toString();
	  
	  //String newPwd="aallll";
	  
	  User u=new User();
	  
	  u=uservice.findByName(uname);
	  
	  if(u!=null)
	  {
		 uservice.updatePassword(uname, newPwd);
		 send(u,newPwd);		 
		 check = true; 
	  }
	   
	  if (u==null) 
	   check = false; 
	   
	  return new ResponseEntity<Boolean>(check, HttpStatus.OK); 
	 }
	
	
	 
	 public String send(User user,String newPwd)
		{
		
		try 
		{
			eservice.sendEmail(user,newPwd);
		}
		catch (MailException mailException) 
		{
			System.out.println(mailException);
		}
		return "Successful !!";
	}
		
		
		@ResponseStatus(HttpStatus.BAD_REQUEST)
		@ExceptionHandler(MethodArgumentNotValidException.class)
		public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
			Map<String, String> errors = new HashMap<>();
			ex.getBindingResult().getAllErrors().forEach((error) -> {
				String fieldName = ((FieldError) error).getField();
				String errorMessage = error.getDefaultMessage();
				errors.put(fieldName, errorMessage);
			});
			return errors;
		}
	
}
