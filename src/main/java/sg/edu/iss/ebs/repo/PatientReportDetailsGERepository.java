package sg.edu.iss.ebs.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import sg.edu.iss.ebs.domain.PatientReportGEDetails;



public interface PatientReportDetailsGERepository extends JpaRepository<PatientReportGEDetails, Integer>{
	
	
	@Query("SELECT p FROM PatientReportGEDetails p WHERE p.reportGE.reportId LIKE :reportId")
	List<PatientReportGEDetails> findGEDetailedReportByReportId(@Param("reportId")String reportId);

}
