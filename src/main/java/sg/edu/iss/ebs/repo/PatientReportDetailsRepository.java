package sg.edu.iss.ebs.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.ebs.domain.PatientReportDetails;

public interface PatientReportDetailsRepository extends JpaRepository<PatientReportDetails, Integer> 
{
		
	@Query("SELECT p FROM PatientReportDetails p WHERE p.report.reportId LIKE :reportId")
	List<PatientReportDetails> findDetailedReportByReportId(@Param("reportId")String reportId);

}
