package sg.edu.iss.ebs.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import sg.edu.iss.ebs.domain.PatientReportChinese;

@Repository
public interface PatientReportChineseRepository extends JpaRepository<PatientReportChinese, String>
{

	PatientReportChinese findPatientReportByReportIdCH(String id);
	
	
	@Query("SELECT prc FROM PatientReportChinese prc WHERE prc.userCH.userId LIKE :userId")
	List<PatientReportChinese> findAllReportsById(@Param("userId")String userId);

	@Query("SELECT prc.reportPdfCH FROM PatientReportChinese prc WHERE prc.reportIdCH LIKE :reportId")
	byte[] findPdf(@Param("reportId")String reportId);
	

}

