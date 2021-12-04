package sg.edu.iss.ebs.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.ebs.domain.Item;

import sg.edu.iss.ebs.repo.ItemRepository;

@Service
public class ItemServiceImpl implements ItemService
{
	@Autowired
	ItemRepository irepo;


	@Override
	public List<Item> findByCatId(String id) {
		
		return irepo.findItemByCategoryId(id);
	}


	@Override
	public Item findByName(String name) {
		
		return irepo.findItemByItemName(name);
	}


	@Override
	public List<Item> findAllItems() {

		return irepo.findAll();
	}


	@Override
	public String findCategoryById(String itemId) {
		
		return irepo.findCategoryByItemId(itemId);
	}


	
	

	
	
}
