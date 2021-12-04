package sg.edu.iss.ebs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.ebs.domain.Category;

import sg.edu.iss.ebs.repo.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService 
{
	
	@Autowired
	CategoryRepository crepo;

	@Override
	public Optional<Category> findByCategoryId(String id) {
		// TODO Auto-generated method stub
		return crepo.findById(id);
	}
	
	

}
