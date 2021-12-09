package sg.edu.iss.ebs.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.ebs.domain.Family;
import sg.edu.iss.ebs.domain.User;


public interface FamilyRepository extends JpaRepository<Family, Integer>{

	@Query("SELECT f FROM Family f WHERE f.user.userId LIKE :userId")
	Family findByUserId(@Param("userId")String userId);
	
	@Modifying
    @Query("UPDATE Family f set f.familyId = :key WHERE f.user.userId LIKE :userId") 
	void addFamilyId(@Param("key") String key , @Param("userId") String userId );
	
	@Modifying
    @Query("UPDATE Family f set f.familyId = :key WHERE f.user.userId LIKE :userId") 
	void deleteFamilyId(@Param("userId") String userId , @Param("key") String key);
	
	
	@Query("SELECT f FROM Family f WHERE f.familyId LIKE :familyId")
	List<Family> getFamily(@Param("familyId")String familyId);

	
	

	
	

}
