package sg.edu.iss.ebs.controller;




import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.ebs.domain.Family;
import sg.edu.iss.ebs.domain.Role;
import sg.edu.iss.ebs.domain.User;
import sg.edu.iss.ebs.service.EmailService;
import sg.edu.iss.ebs.service.FamilyService;
import sg.edu.iss.ebs.service.UserService;



@Controller
@RequestMapping("/register")
public class RegisterController
{
	
	@Autowired
	UserService uservice;
	
	@Autowired
	FamilyService fservice;
	
	@Autowired
	private EmailService eservice;
	
	String msg="user already exists";
	
	@RequestMapping("/add") 
	 public String addNewPatient(Model model) 
	{ 
	  //model.addAttribute("patient",new Patient());
	  model.addAttribute("user",new User());
	  return "register"; 
	 }
	
	
	
	@RequestMapping("/save")
	  public String savePatient( @ModelAttribute("user") User user,BindingResult bindingResult,
			  						 	Model model)
	{
		
		
		if (bindingResult.hasErrors())
	    {
	      return "home";
	    }
		
		User u=uservice.findUserByUserId(user.getUserId());
		
		//String id=pat.get();
		
		if(u==null)
		{	
			//uservice.createUser(user);
			
			String password=UUID.randomUUID().toString();
			
			String username=user.getName().toString();
			user.setName(username);
			user.setPassword(password);
			user.setRole(Role.PATIENT);
			
			uservice.createUser(user);
			
			send(user,password);
			
			String familyId=UUID.randomUUID().toString();
			Family family=new Family();
		    
		    family.setFamilyId(familyId);
		    family.setUser(user);
		    
		    fservice.createFamily(family);
		    
		    
		    return "forward:/login/homeupload";
			
		
		}
		else
		{
			model.addAttribute("errmsg",msg);
			return "register";
		}
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
	
	
	
}
