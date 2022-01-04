package sg.edu.iss.ebs.service;

import org.springframework.web.multipart.MultipartFile;

import sg.edu.iss.ebs.domain.PatientReport;
import sg.edu.iss.ebs.domain.PatientReportChinese;

public interface UploadReportService 
{

	PatientReport storeFile(MultipartFile file, PatientReport details);
	
	

	PatientReport getFile(String fileName);

	//PatientReportChinese storeFileCH(MultipartFile file, PatientReportChinese details);



	PatientReportChinese storeFileCH(MultipartFile file, PatientReport details);

}
