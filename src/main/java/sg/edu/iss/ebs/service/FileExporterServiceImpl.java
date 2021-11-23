package sg.edu.iss.ebs.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import sg.edu.iss.ebs.domain.PatientReportDetails;

@Service
public class FileExporterServiceImpl implements FileExporterService{

	
	
private static String UPLOADED_FOLDER = "D://VIITAR//";
	
	public List<PatientReportDetails> invoiceExcelReaderService() {
		return null;
	}
	
	/*
	 * @Value("${app.upload.dir:${user.home}}") public String uploadDir;
	 */

    public void uploadFile(MultipartFile file) {

        try {
            Path copyLocation = Paths
                .get(UPLOADED_FOLDER +  StringUtils.cleanPath(file.getOriginalFilename()));
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Could not store file " + file.getOriginalFilename()
                + ". Please try again!");
        }
    }
	
	
	

}
