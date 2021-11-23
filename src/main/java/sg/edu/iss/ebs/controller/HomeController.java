package sg.edu.iss.ebs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.ebs.domain.User;



@Controller
@RequestMapping
public class HomeController 
{
	
	@GetMapping("/")
	public String showHome(Model model)
	{
		User u = new User();
		
		model.addAttribute("user", u);
       
		return "home";
	}
	

}
