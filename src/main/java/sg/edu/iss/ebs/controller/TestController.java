package sg.edu.iss.ebs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {

	
	
	
	    @GetMapping("/page")
	    public String index()
	    {
	        return "test";
	    }

}
