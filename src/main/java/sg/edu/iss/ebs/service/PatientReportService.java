package sg.edu.iss.ebs.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import sg.edu.iss.ebs.domain.PatientReport;
import sg.edu.iss.ebs.domain.PatientReportDetails;
import sg.edu.iss.ebs.domain.PatientReportGEDetails;

public interface PatientReportService 
{
	public PatientReport findPatientById(String name);

	public List<PatientReportDetails> findDetailsByReportId(String reportId);
	
	public List<PatientReportGEDetails> findGEDetailsByReportId(String reportId);

	public List<PatientReport> findAllReports(String userId);

	byte[] getPdf(String filename);
}
