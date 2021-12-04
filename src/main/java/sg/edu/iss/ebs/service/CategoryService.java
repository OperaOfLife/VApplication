package sg.edu.iss.ebs.service;

import java.util.List;
import java.util.Optional;

import sg.edu.iss.ebs.domain.Category;
import sg.edu.iss.ebs.domain.Item;

public interface CategoryService {
	
	public Optional<Category> findByCategoryId(String id);

}
