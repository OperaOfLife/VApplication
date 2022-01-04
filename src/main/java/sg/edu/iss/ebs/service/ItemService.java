package sg.edu.iss.ebs.service;

import java.util.HashMap;
import java.util.List;

import sg.edu.iss.ebs.domain.Item;


public interface ItemService {
	
	public Item findByName(String name);
	public List<Item> findByCatId(String id);
	public List<Item> findAllItems();
	
	
	public String findCategoryById(String itemId);
	public String findByNameChinese(String name);

}
