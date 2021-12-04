package sg.edu.iss.ebs.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.iss.ebs.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, String>
{
	

}
