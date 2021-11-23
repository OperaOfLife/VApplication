package sg.edu.iss.ebs.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.iss.ebs.domain.PatientReportDetails;
import sg.edu.iss.ebs.service.PatientReportService;


@RestController
@RequestMapping("/report")
public class PatientReportRestController 
{

	
	@Autowired
	PatientReportService prservice;
	
	
	
	  @GetMapping("/intensityList") 
	  public ResponseEntity<HashMap<String, String>> findReportDetails(@RequestParam String reportId) 
	  { 
		List<PatientReportDetails> prdlist = prservice.findDetailsByReportId(reportId); 
		HashMap<String, String> prdhashmap = new HashMap<String, String>();
		for (PatientReportDetails prd : prdlist) 
		 {
			prdhashmap.put(prd.getItemName(), prd.getIntensity());
		}
		return new ResponseEntity<HashMap<String, String>>(prdhashmap, HttpStatus.OK); 	    
	  } 
	
	  @PostMapping("/test") 
	  public ResponseEntity<String> test() 
	  { 
		//String s="hahhahahhahah";		
		//return new ResponseEntity<String>(s, HttpStatus.OK); 	
		
		return ResponseEntity.ok("Successful Booking !! An email has been sent.");
	  } 
	
	
}
