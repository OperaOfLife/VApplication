package sg.edu.iss.ebs.service;

import java.util.List;

import sg.edu.iss.ebs.domain.PatientReportDetails;



public interface ExportExcelToDBService {

	
List<PatientReportDetails> getExcelDataAsList(String filePath);
	
	int saveExcelData(List<PatientReportDetails> prDetails);
	
}
