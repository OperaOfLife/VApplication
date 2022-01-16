package sg.edu.iss.ebs.service;

import java.util.List;

import sg.edu.iss.ebs.domain.PatientReportDetails;
import sg.edu.iss.ebs.domain.PatientReportGEDetails;



public interface ExportExcelToDBService {

	
List<PatientReportDetails> getExcelDataAsList(String filePath);
	
	int saveExcelData(List<PatientReportDetails> prDetails);
	
List<PatientReportGEDetails> getExcelDataAsList224(String filePath);
	
	int saveExcelData224(List<PatientReportGEDetails> prDetails);
	
}
