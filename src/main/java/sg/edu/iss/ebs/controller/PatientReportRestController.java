package sg.edu.iss.ebs.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.iss.ebs.domain.Item;
import sg.edu.iss.ebs.domain.PatientReportDetails;
import sg.edu.iss.ebs.service.ItemService;
import sg.edu.iss.ebs.service.PatientReportService;


@RestController
@RequestMapping("/report")
public class PatientReportRestController 
{
	
	private static boolean ASC = true;
	  private static boolean DESC = false;

	
	@Autowired
	PatientReportService prservice;
	
	@Autowired
	ItemService iservice;
	
	
	
	/*
	 * @GetMapping("/intensityList") public
	 * ResponseEntity<List<PatientReportDetails>> findReportDetails(@RequestParam
	 * String reportId) { List<PatientReportDetails> prdlist =
	 * prservice.findDetailsByReportId(reportId);
	 * 
	 * return new ResponseEntity<List<PatientReportDetails>>(prdlist,
	 * HttpStatus.OK); }
	 */
	
	  @GetMapping("/intensityList")
	  public ResponseEntity<HashMap<String, String>> findReportDetails(@RequestParam String reportId)
	  { 
		  List<PatientReportDetails> prdlist = prservice.findDetailsByReportId(reportId); 
		  HashMap<String, String>  prdhashmap = new HashMap<String, String>(); 
		  for (PatientReportDetails prd :  prdlist) 
		  { 
			  prdhashmap.put(prd.getItemName(), prd.getIntensity());
		 } 
		  return  new ResponseEntity<HashMap<String, String>>(prdhashmap, HttpStatus.OK); 
	}
	  
	  
	  @GetMapping("/categoryIntensityList")
	  public ResponseEntity<HashMap<String, String>> findCategoryItemDetails(@RequestParam String reportId,@RequestParam String categoryId)
	  { 
		  List<Item> categoryItem = iservice.findByCatId(categoryId);
		  
		  List<PatientReportDetails> prdlist = prservice.findDetailsByReportId(reportId); 
		  	  
		  HashMap<String, String>  prdhashmap = new HashMap<String, String>(); 
		  for (PatientReportDetails prd :  prdlist) 
		  { 
			  for (Item i:categoryItem)
			  {
				  if(prd.getItemName().equalsIgnoreCase(i.getItemName()))
					  	prdhashmap.put(prd.getItemName(), prd.getIntensity());
			  }
		 } 
		  
		  HashMap<String, String> sortedprdhashmap = sortbykey(prdhashmap);
		  
		  return  new ResponseEntity<HashMap<String, String>>(sortedprdhashmap, HttpStatus.OK); 
	}
	  
	  @GetMapping("/sortedIntensityList")
	  public ResponseEntity<Map<String, Integer>> findReportDetailsSorted(@RequestParam String reportId)
	  { 
		  
		  
		  
		  List<PatientReportDetails> prdlist = prservice.findDetailsByReportId(reportId); 
		  Map<String, Integer>  prdhashmap = new HashMap<String, Integer>(); 
		  for (PatientReportDetails prd :  prdlist) 
		  { 
			  prdhashmap.put(prd.getItemName(), Integer.parseInt( prd.getIntensity()));
		 } 
		  
		  Map<String, Integer> sortedprdhashmap = sortByValue(prdhashmap, DESC);
		  
		  	
		
		  return  new ResponseEntity<Map<String, Integer>>(sortedprdhashmap, HttpStatus.OK); 
	}
	  
	  
	  
	  private static Map<String, Integer> sortByValue(Map<String, Integer> unsortMap, final boolean order)
	    {
	        List<Entry<String, Integer>> list = new LinkedList<>(unsortMap.entrySet());

	        // Sorting the list based on values
	        list.sort((o1, o2) -> order ? o1.getValue().compareTo(o2.getValue()) == 0
	                ? o1.getKey().compareTo(o2.getKey())
	                : o1.getValue().compareTo(o2.getValue()) : o2.getValue().compareTo(o1.getValue()) == 0
	                ? o2.getKey().compareTo(o1.getKey())
	                : o2.getValue().compareTo(o1.getValue()));
	        return list.stream().collect(Collectors.toMap(Entry::getKey, Entry::getValue, (a, b) -> b, LinkedHashMap::new));

	    }
	  
	  public static HashMap<String, String> sortbykey(HashMap<String, String> map)
	    {
	        HashMap<String, String> temp
	            = map.entrySet()
	                  .stream()
	                  .sorted((i1, i2)
	                              -> i1.getKey().compareTo(
	                                  i2.getKey()))
	                  .collect(Collectors.toMap(
	                      Map.Entry::getKey,
	                      Map.Entry::getValue,
	                      (e1, e2) -> e1, LinkedHashMap::new));
	 
	        // Display the HashMap which is naturally sorted
	       return temp;
	        }
	   
	  
	 
	
	  @GetMapping("/test") 
	  public ResponseEntity<String> test() 
	  { 
		//String s="hahhahahhahah";		
		//return new ResponseEntity<String>(s, HttpStatus.OK); 	
		
		return ResponseEntity.ok("Successful Booking !! An email has been sent.");
	  } 
	
	
}
