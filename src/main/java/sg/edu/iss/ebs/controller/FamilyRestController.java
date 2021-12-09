package sg.edu.iss.ebs.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.transaction.Transactional;

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

import sg.edu.iss.ebs.domain.Family;
import sg.edu.iss.ebs.domain.User;
import sg.edu.iss.ebs.service.EmailService;
import sg.edu.iss.ebs.service.FamilyService;
import sg.edu.iss.ebs.service.UserService;

@RestController
@RequestMapping("/family")
public class FamilyRestController
{
	
	@Autowired
	UserService uservice;
	
	@Autowired
	FamilyService fservice;
	
	@Autowired
	private EmailService eservice;
	
	
	@Transactional
	 @RequestMapping("/addMember") 
	 public ResponseEntity<Boolean> add(@RequestParam String uname,@RequestParam String familyUsername) 
	 { 
		 boolean check =true;
		  
		 
		  Family f=new Family();
		  
		  f=fservice.findByName(uname);
		  String s=f.getFamilyId().toString();
		  
		  if(f!=null)
		  {
			 fservice.addFamilyId(s,familyUsername);
			 		 
			 check = true; 
		  }
		   
		  if (f==null) 
		   check = false; 
		   
		  return new ResponseEntity<Boolean>(check, HttpStatus.OK); 
	 }
	 
	 @Transactional
	 @RequestMapping("/deleteMember") 
	 public ResponseEntity<Boolean> delete(@RequestParam String familyUsername) 
	 { 
		 boolean check =true;
		  
		
		  
		  Family f=new Family();
		  
		  f=fservice.findByName(familyUsername);
		  
		  if(f!=null)
		  {
			 fservice.deleteFamilyId(familyUsername);
			 		 
			 check = true; 
		  }
		   
		  if (f==null) 
		   check = false; 
		   
		  return new ResponseEntity<Boolean>(check, HttpStatus.OK); 
	 }
	
	
	 @RequestMapping("/manageMember") 
	 public ResponseEntity<String> manage(@RequestParam String uname) 
	 { 
	  	  
	  String key=UUID.randomUUID().toString();
	  
	  User u=new User();
	  
	  u=uservice.findByName(uname);
	  
	  if(u!=null)
	  	 send(u,key); 
	   
	  return new ResponseEntity<String>(key, HttpStatus.OK); 
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
