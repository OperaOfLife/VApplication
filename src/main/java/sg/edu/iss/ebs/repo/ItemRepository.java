package sg.edu.iss.ebs.repo;

import java.util.HashMap;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.ebs.domain.Item;
import sg.edu.iss.ebs.domain.PatientReportDetails;

public interface ItemRepository extends JpaRepository<Item ,String> 
{

	public Item findItemByItemName(String name);
	
	
	@Query("SELECT i FROM Item i WHERE i.category.categoryId LIKE :categoryId")
	List<Item> findItemByCategoryId(@Param("categoryId")String categoryId);
	
	@Query("SELECT i.category.categoryId FROM Item i WHERE i.itemId LIKE :itemId")
	String findCategoryByItemId(@Param("itemId")String itemId);

	@Query("SELECT i.itemNameChinese FROM Item i WHERE i.itemName LIKE :itemName")
	public String findItemByItemNameChinese(@Param("itemName")String itemName);

	@Query("SELECT i FROM Item i WHERE i.itemNameChinese LIKE :itemNameChinese")
	public Item findItemByChineseItemName(@Param("itemNameChinese")String itemNameChinese);
	
	
	

	
}
