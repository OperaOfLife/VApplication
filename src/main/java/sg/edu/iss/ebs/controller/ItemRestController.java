package sg.edu.iss.ebs.controller;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.iss.ebs.domain.Item;
import sg.edu.iss.ebs.service.ItemService;


@RestController
@RequestMapping("/item")
public class ItemRestController 
{
	
	@Autowired
	ItemService iservice;
	
	 @GetMapping("/details")
	  public ResponseEntity<Item> findItemDetails(@RequestParam String itemName)
	  { 
		  Item item = iservice.findByName(itemName); 
		  
		  
		  return  new ResponseEntity<Item>(item, HttpStatus.OK); 
	}
	 
	 
	 @GetMapping("/allItems")
	  public ResponseEntity<List<Item>> findAllItems()
	  { 
		   
		 List<Item> allCat=iservice.findAllItems();
		  
		  
		  return  new ResponseEntity<List<Item>>(allCat, HttpStatus.OK); 
	}
	 
	 @GetMapping("/getCat")
	  public ResponseEntity<String> findAllItems(@RequestParam String itemId)
	  { 
		   
		 String cat=iservice.findCategoryById(itemId);
		  
		  
		  return  new ResponseEntity<String>(cat, HttpStatus.OK); 
	}
	 
	 
	 

}
