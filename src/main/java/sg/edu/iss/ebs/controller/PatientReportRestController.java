package sg.edu.iss.ebs.controller;

import java.io.ByteArrayInputStream;
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

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.iss.ebs.domain.Family;
import sg.edu.iss.ebs.domain.Item;
import sg.edu.iss.ebs.domain.PatientReport;
import sg.edu.iss.ebs.domain.PatientReportChinese;
import sg.edu.iss.ebs.domain.PatientReportDetails;
import sg.edu.iss.ebs.service.FamilyService;
import sg.edu.iss.ebs.service.ItemService;
import sg.edu.iss.ebs.service.PatientReportChineseService;
import sg.edu.iss.ebs.service.PatientReportService;
import sg.edu.iss.ebs.service.UploadReportService;


@RestController
@RequestMapping("/report")
public class PatientReportRestController 
{
	
	private static boolean ASC = true;
	  private static boolean DESC = false;

	
	@Autowired
	PatientReportService prservice;
	
	@Autowired
	PatientReportChineseService prcservice;
	
	
	
	@Autowired
	ItemService iservice;
	
	@Autowired
	FamilyService fservice;
	
	@Autowired
    private UploadReportService urservice;
	
	
	
	/*
	 * @GetMapping("/intensityList") public
	 * ResponseEntity<List<PatientReportDetails>> findReportDetails(@RequestParam
	 * String reportId) { List<PatientReportDetails> prdlist =
	 * prservice.findDetailsByReportId(reportId);
	 * 
	 * return new ResponseEntity<List<PatientReportDetails>>(prdlist,
	 * HttpStatus.OK); }
	 */
	
	
	@GetMapping("/allReportTypes")
	  public ResponseEntity<HashMap<String, String>> findAllReportTypes(@RequestParam String uname)
	  { 
		  List<PatientReport> prlist = prservice.findAllReports(uname); 
		  HashMap<String, String>  prhashmap = new HashMap<String, String>(); 
		  for (PatientReport pr :  prlist) 
		  { 
			  prhashmap.put(pr.getType().getId(), pr.getReportId());
		 } 
		  return  new ResponseEntity<HashMap<String, String>>(prhashmap, HttpStatus.OK); 
	}
	  
	
	
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
		  
			  for (Item i:categoryItem)
			  {
			   for (PatientReportDetails prd :  prdlist) 
			     { 
				  if(i.getItemName().equalsIgnoreCase(prd.getItemName()))
				  {
					  	prdhashmap.put(i.getItemName(), prd.getIntensity());
					  	break;
				  }
					/*
					 * else prdhashmap.put(i.getItemName(), "0");
					 */
			     }
			  } 
		  
		 // If(!prdhashmap.size()>0)
		  		
		  
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
	  
	  @GetMapping("/allReports")
	  public ResponseEntity<Map<String, ArrayList<String>>> findAllReports(@RequestParam String userId)
	  { 
		   
		 List<PatientReport> allrep=prservice.findAllReports(userId);
		  
		 Map<String, ArrayList<String>> reportMap = new HashMap<String, ArrayList<String>>();
		 
		 for (PatientReport pr :  allrep) 
		  { 
			 String reportId=pr.getReportId().toString();
			 
			  reportMap.put(reportId, new ArrayList<String>());
			  reportMap.get(reportId).add(pr.getFileName());
			  reportMap.get(reportId).add(pr.getType().getReportName());
			  //reportMap.get(reportId).add(pr.getReportPdf());
				 
		 } 

		
		  return  new ResponseEntity<Map<String, ArrayList<String>>>(reportMap, HttpStatus.OK); 
	}
	  
	  @GetMapping("/allReportsChinese")
	  public ResponseEntity<Map<String, ArrayList<String>>> findAllReportsChinese(@RequestParam String userId)
	  { 
		   
		 List<PatientReportChinese> allrep=prcservice.findAllReports(userId);
		  
		 Map<String, ArrayList<String>> reportMap = new HashMap<String, ArrayList<String>>();
		 
		 for (PatientReportChinese pr :  allrep) 
		  { 
			 String reportId=pr.getReportIdCH().toString();
			 
			  reportMap.put(reportId, new ArrayList<String>());
			  reportMap.get(reportId).add(pr.getFileNameCH());
			  reportMap.get(reportId).add(pr.getTypeCH().getReportName());
			  //reportMap.get(reportId).add(pr.getReportPdf());
				 
		 } 

		
		  return  new ResponseEntity<Map<String, ArrayList<String>>>(reportMap, HttpStatus.OK); 
	}
	  
	  @GetMapping("/allMemberReports")
	  public ResponseEntity<Map<String, Object>> findAllMemberReports(@RequestParam String userId)
	  { 
		   
		  Family f=new Family();
 		  
		  f=fservice.findByName(userId);
		  
		  String s=f.getFamilyId().toString();
		  
		  List<Family> membersIds=fservice.getAllMembers(s);
		   
		  Map<String, Object> memberReportMap = new HashMap<String, Object>(); 
		  	
		  for(Family fam : membersIds)
		  {
			  
			  Map<String, ArrayList<String>> reportMapExtended = new HashMap<String, ArrayList<String>>();
				
		  
			  String memberUserId=fam.getUser().getUserId();
			  					  
			  List<PatientReport> allrep=prservice.findAllReports(memberUserId);
		  	 
			  for (PatientReport pr :  allrep) 
			  	{ 
			
				  String reportId=pr.getReportId().toString();
				 
			 
				  reportMapExtended.put(reportId, new ArrayList<String>());
				  reportMapExtended.get(reportId).add(pr.getFileName());
				  reportMapExtended.get(reportId).add(pr.getType().getReportName());
				  reportMapExtended.get(reportId).add(pr.getUser().getName());
				  	 
			  	} 
			  memberReportMap.put(memberUserId, reportMapExtended);
		  }

		
		  return  new ResponseEntity<Map<String, Object>>(memberReportMap, HttpStatus.OK); 
	}
	  
	  @GetMapping("/allMemberReportsChinese")
	  public ResponseEntity<Map<String, Object>> findAllMemberReportsChinese(@RequestParam String userId)
	  { 
		   
		  Family f=new Family();
 		  
		  f=fservice.findByName(userId);
		  
		  String s=f.getFamilyId().toString();
		  
		  List<Family> membersIds=fservice.getAllMembers(s);
		   
		  Map<String, Object> memberReportMap = new HashMap<String, Object>(); 
		  	
		  for(Family fam : membersIds)
		  {
			  
			  Map<String, ArrayList<String>> reportMapExtended = new HashMap<String, ArrayList<String>>();
				
		  
			  String memberUserId=fam.getUser().getUserId();
			  					  
			  List<PatientReportChinese> allrep=prcservice.findAllReports(memberUserId);
		  	 
			  for (PatientReportChinese pr :  allrep) 
			  	{ 
			
				  String reportId=pr.getReportIdCH().toString();
				 
			 
				  reportMapExtended.put(reportId, new ArrayList<String>());
				  reportMapExtended.get(reportId).add(pr.getFileNameCH());
				  reportMapExtended.get(reportId).add(pr.getTypeCH().getReportName());
				  reportMapExtended.get(reportId).add(pr.getUserCH().getName());
				  	 
			  	} 
			  memberReportMap.put(memberUserId, reportMapExtended);
		  }

		
		  return  new ResponseEntity<Map<String, Object>>(memberReportMap, HttpStatus.OK); 
	}
	   
	  @GetMapping("/reportPdf")
	  public ResponseEntity<String> findReportPdf(@RequestParam String fileName)
	  { 
		   
		 byte[] repPdf=prservice.getPdf(fileName);	
		 
		 //byte[] bytes = getByteArr();
		 String base64String = Base64.encodeBase64String(repPdf);
		 byte[] backToBytes = Base64.decodeBase64(base64String);
		 
		  return  new ResponseEntity<String>(base64String, HttpStatus.OK); 
	} 
	  
	  @GetMapping("/reportPdf1")
	  public ResponseEntity<byte[]> findReportPdf1(@RequestParam String fileName)
	  { 
		   
		 byte[] repPdf=prservice.getPdf(fileName);	
		 
		 //byte[] bytes = getByteArr();
		 String base64String = Base64.encodeBase64String(repPdf);
		 byte[] backToBytes = Base64.decodeBase64(base64String);
		 
		  return  new ResponseEntity<byte[]>(backToBytes, HttpStatus.OK); 
	} 
	  
	  @GetMapping("/downloadFile")
	    public ResponseEntity < Object > downloadFilePdf(@RequestParam String fileName, HttpServletRequest request) {
	        // Load file as Resource
	    	PatientReport prFile = urservice.getFile(fileName);

			/*
			 * return ResponseEntity.ok()
			 * .contentType(MediaType.parseMediaType(prFile.getFileType()))
			 * .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +
			 * prFile.getFileName() + "\"") .body(new
			 * ByteArrayResource(prFile.getReportPdf()));
			 */
	    	
	    	 return ResponseEntity.ok()
	    	            .contentType(MediaType.parseMediaType(prFile.getFileType()))
	    	            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + prFile.getFileName() + "\"")
	    	            .body(new ByteArrayInputStream(prFile.getReportPdf()));
	    }

	 
	
	  @GetMapping("/test") 
	  public ResponseEntity<String> test() 
	  { 
		//String s="hahhahahhahah";		
		//return new ResponseEntity<String>(s, HttpStatus.OK); 	
		
		return ResponseEntity.ok("Successful Booking !! An email has been sent.");
	  } 
	  
	  
	  
	  //Chinese .....................................
	
	  @GetMapping("/sortedIntensityListChinese")
	  public ResponseEntity<Map<String, Integer>> findReportDetailsSortedChinese(@RequestParam String reportId)
	  { 
		  
		  
		  
		  List<PatientReportDetails> prdlist = prservice.findDetailsByReportId(reportId); 
		  Map<String, Integer>  prdhashmap = new HashMap<String, Integer>(); 
		  for (PatientReportDetails prd :  prdlist) 
		  { 
			  String itemInChinese=iservice.findByNameChinese(prd.getItemName());
			  if(itemInChinese!=null)
				  prdhashmap.put(itemInChinese, Integer.parseInt( prd.getIntensity()));
			  else
				  prdhashmap.put(prd.getItemName(), Integer.parseInt( prd.getIntensity()));
		 } 
		  
		  Map<String, Integer> sortedprdhashmap = sortByValue(prdhashmap, DESC);
		  
		  	
		
		  return  new ResponseEntity<Map<String, Integer>>(sortedprdhashmap, HttpStatus.OK); 
	}
	  
	  @GetMapping("/intensityListChinese")
	  public ResponseEntity<HashMap<String, String>> findReportDetailsChinese(@RequestParam String reportId)
	  { 
		  List<PatientReportDetails> prdlist = prservice.findDetailsByReportId(reportId); 
		  HashMap<String, String>  prdhashmap = new HashMap<String, String>(); 
		  for (PatientReportDetails prd :  prdlist) 
		  { 
			  String itemInChinese=iservice.findByNameChinese(prd.getItemName());
			  if(itemInChinese!=null)
				  prdhashmap.put(itemInChinese, prd.getIntensity());
			  else
				  prdhashmap.put(prd.getItemName(), prd.getIntensity());
		 } 
		  return  new ResponseEntity<HashMap<String, String>>(prdhashmap, HttpStatus.OK); 
	}
	  
	  
	  @GetMapping("/categoryIntensityListChinese")
	  public ResponseEntity<HashMap<String, String>> findCategoryItemDetailsChinese(@RequestParam String reportId,@RequestParam String categoryId)
	  { 
		  List<Item> categoryItem = iservice.findByCatId(categoryId);
		  
		  List<PatientReportDetails> prdlist = prservice.findDetailsByReportId(reportId); 
		  	  
		  HashMap<String, String>  prdhashmap = new HashMap<String, String>(); 
		  
			  for (Item i:categoryItem)
			  {
			   for (PatientReportDetails prd :  prdlist) 
			     { 
				  if(i.getItemName().equalsIgnoreCase(prd.getItemName()))
				  {
					  
					  String itemInChinese=iservice.findByNameChinese(prd.getItemName());
					  if(itemInChinese!=null)
						  prdhashmap.put(itemInChinese, prd.getIntensity());
					  else
					  	prdhashmap.put(i.getItemName(), prd.getIntensity());
					  	break;
				  }
					/*
					 * else prdhashmap.put(i.getItemName(), "0");
					 */
			     }
			  } 
		  
		 // If(!prdhashmap.size()>0)
		  		
		  
		  //HashMap<String, String> sortedprdhashmap = sortbykey(prdhashmap);
		  
		  return  new ResponseEntity<HashMap<String, String>>(prdhashmap, HttpStatus.OK); 
	}
}
