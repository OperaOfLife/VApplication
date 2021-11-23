package sg.edu.iss.ebs.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import sg.edu.iss.ebs.domain.PatientReport;
import sg.edu.iss.ebs.domain.ReportType;
import sg.edu.iss.ebs.domain.User;
import sg.edu.iss.ebs.exceptions.FileNotFoundException;
import sg.edu.iss.ebs.exceptions.FileStorageException;
import sg.edu.iss.ebs.repo.PatientReportRepository;


@Service
public class UploadReportServiceImpl implements UploadReportService
{
	
@Autowired
PatientReportRepository preportrepo;

public PatientReport storeFile(MultipartFile file,PatientReport details)
{
    // Normalize file name
    String fileName = StringUtils.cleanPath(file.getOriginalFilename());

    try 
    {
        // Check if the file's name contains invalid characters
        if (fileName.contains(".."))
        {
            throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
        }
        
        String reportId=details.getReportId();
        String dt=details.getReportDate();
		/*
		 * String reportType=details.getType().getReportName(); String
		 * userId=details.getUser().getUserId();
		 */
        
        
        User u=details.getUser();
        ReportType type=details.getType();
        
        
        PatientReport preport = new PatientReport(reportId,dt,file.getBytes(),fileName,
        														file.getContentType(),u,type);
        		//PatientReport preport = new PatientReport(fileName, file.getContentType(), file.getBytes());
        return preportrepo.save(preport);
    } 
    catch (IOException ex) 
    {
        throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
    }
}

public PatientReport getFile(String fileId)
{
    return preportrepo.findById(fileId)
        .orElseThrow(() -> new FileNotFoundException("File not found with id " + fileId));
}
}
