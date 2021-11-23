package sg.edu.iss.ebs.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileExporterService {
	
	
	public void uploadFile(MultipartFile file);

}
