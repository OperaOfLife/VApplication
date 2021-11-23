package sg.edu.iss.ebs.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sg.edu.iss.ebs.domain.PatientReport;

@Repository
public interface PatientReportRepository extends JpaRepository<PatientReport, String>{

	PatientReport findPatientReportByReportId(String id);

}
