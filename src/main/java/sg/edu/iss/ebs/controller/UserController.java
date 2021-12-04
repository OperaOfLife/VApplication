package sg.edu.iss.ebs.controller;



import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.ebs.domain.PatientReport;

import sg.edu.iss.ebs.domain.Role;
import sg.edu.iss.ebs.domain.User;
import sg.edu.iss.ebs.domain.UserSession;
import sg.edu.iss.ebs.service.UserService;


@Controller
@RequestMapping("/login")
public class UserController {
	
	@Autowired
	UserService uservice;
	
	String errmsg = "";
	String msg = "NO SUCH USER EXISTS.";
	String msg1 = "USER ALREADY EXISTS.";
	
	@Autowired
	public void setUserInterface(UserService us) {
		this.uservice = us;
	}

	@RequestMapping(path = "/home")
	public String login(Model model) {
		
		model.addAttribute("details",new  PatientReport());
		
		User u = new User();
			
		model.addAttribute("user", u);
		return "home";
	}
	
	@RequestMapping(path = "/homeupload")
	public String uploading(Model model) {
		
		model.addAttribute("details",new  PatientReport());
		
		User u = new User();
			
		model.addAttribute("user", u);
		return "upload-pdf-report";
	}
	
	
	
	@RequestMapping(path = "/authenticate") 
	  public String	  register(@ModelAttribute("user") User user, Model model,HttpSession session)
	  {
		UserSession usession = new UserSession(); 
	  
	  if(!uservice.authenticate(user))
	  {
		  model.addAttribute("errmsg",msg ); 
		  return "home";
	  }
	  else if(uservice.authenticate(user))
	  {
		  User u = uservice.findByName(user.getUserId());
		  
		  usession.setUser(user); 
		  session.setAttribute("usession", usession);
		  
		  String currentusername=usession.getUser().getUserId();  
	 
	  
	  
	   if(u.getRole().equals(Role.ADMIN))
		  return "forward:/login/homeupload";
	  
	  else 
	  { 
		  
		  model.addAttribute("errmsg",msg ); 
		  return "home";
		  }
	  
	  
	  } 
	  else return "home"; 
	  }
	  
	  
	@RequestMapping(path = "/exit")
	public String logout(@ModelAttribute("user") User user, Model model, HttpSession session) 
	{
				
		session.setAttribute("usession", null);
		return "home";
	}


}
