package sg.edu.iss.ebs.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sg.edu.iss.ebs.domain.Item;
import sg.edu.iss.ebs.domain.PatientReport;

@Repository
public interface PatientReportRepository extends JpaRepository<PatientReport, String>{

	PatientReport findPatientReportByReportId(String id);
	
	
	@Query("SELECT pr FROM PatientReport pr WHERE pr.user.userId LIKE :userId")
	List<PatientReport> findAllReportsById(@Param("userId")String userId);

	@Query("SELECT pr.reportPdf FROM PatientReport pr WHERE pr.reportId LIKE :reportId")
	byte[] findPdf(@Param("reportId")String reportId);
	

}
