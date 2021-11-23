package sg.edu.iss.ebs.service;

import org.springframework.web.multipart.MultipartFile;

import sg.edu.iss.ebs.domain.PatientReport;

public interface UploadReportService 
{

	PatientReport storeFile(MultipartFile file, PatientReport details);

	PatientReport getFile(String fileName);

}
