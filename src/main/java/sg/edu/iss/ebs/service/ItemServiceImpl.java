package sg.edu.iss.ebs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.ebs.domain.Item;

import sg.edu.iss.ebs.repo.ItemRepository;

@Service
public class ItemServiceImpl implements ItemService
{
	@Autowired
	ItemRepository irepo;

	
	public Item findByName(String name)
	{
		
		return irepo.findItemByItemName(name);
		
		
	}
	
}
