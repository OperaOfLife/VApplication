package sg.edu.iss.ebs.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.iss.ebs.domain.Item;

public interface ItemRepository extends JpaRepository<Item ,String> 
{

	public Item findItemByItemName(String name);

}
