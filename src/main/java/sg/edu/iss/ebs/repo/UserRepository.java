package sg.edu.iss.ebs.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.ebs.domain.PatientReportDetails;
import sg.edu.iss.ebs.domain.User;



public interface UserRepository extends JpaRepository<User, String>
{
	public User findUserByUserIdAndPassword(String un, String pwd);
    public User findUserByUserId(String name);

   
    @Modifying
    @Query("UPDATE User u set u.password = :pwd WHERE u.userId LIKE :id") 
	 public void updatePwd(@Param("id") String id , @Param("pwd") String pwd);
    
    
	@Query("SELECT u FROM User u WHERE u.userId LIKE :userId")
	public User findByUserId(@Param("userId")String userId);
}

