package sg.edu.iss.ebs.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import sg.edu.iss.ebs.domain.PatientReport;
import sg.edu.iss.ebs.domain.PatientReportChinese;
import sg.edu.iss.ebs.domain.PatientReportDetails;
import sg.edu.iss.ebs.domain.Response;

import sg.edu.iss.ebs.service.UploadReportService;


@Controller
@RequestMapping("/upload")
public class UploadReportController 
{
	
	@Autowired
    private UploadReportService urservice;
	
	//Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "D://VIITAR//";

    @GetMapping("/page")
    public String index(Model model) {
    	
    	
    	model.addAttribute("details",new  PatientReport());
        return "upload-pdf-report";
    }

    @PostMapping("/uploadFile1") // //new annotation since 4.3
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes,Model model,BindingResult bindingresult) {
    	
    	

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:upload-pdf-report-confirmation";
        }

        try {
        	
        	

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
           
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/upload/uploadConfirmation";
    }

    @GetMapping("/uploadConfirmation")
    public String uploadStatus() {
        return "upload-pdf-report-confirmation";
    }
    
    
    

    @PostMapping("/uploadFile")
    public String uploadFile(@ModelAttribute("details") PatientReport details,@RequestParam("file") MultipartFile file,
    		HttpServletRequest request,RedirectAttributes redirectAttributes,Model model,BindingResult bindingresult) 
    {
    	
    	String lang= request.getParameter("language");
    	String chinese="ch";
    	String fileDownloadUri ="";
    	
    	 if (file.isEmpty()) {
             redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
             return "redirect:upload-pdf-report-confirmation";
         }

         try {
        	 
        	// String intensity=details.getIntensity();
        	 
        	 if(lang.equalsIgnoreCase(chinese))
        	 {
        		 PatientReportChinese fileNameCH = urservice.storeFileCH(file,details);
        		 fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
        		            .path("/downloadFile/")
        		            .path(fileNameCH.getFileNameCH())
        		            .toUriString();
        	 }
        	 else
        	 {
        		 PatientReport fileName = urservice.storeFile(file,details);
        		 fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
        		            .path("/downloadFile/")
        		            .path(fileName.getFileName())
        		            .toUriString();
        		 
        	 }
        
        
        
        

        

       //return new Response(fileName.getFileName(),  fileDownloadUri,file.getContentType(), file.getSize());
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded '" + file.getOriginalFilename() + "'");

    } catch (Exception e) {
        e.printStackTrace();
    }

    return "redirect:/upload/uploadConfirmation";
}


/*
 * @PostMapping("/uploadMultipleFiles") 
 * public List < Response > 
 *  uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) { return
 * Arrays.asList(files) .stream() .map(file -> uploadFile(file))
 * .collect(Collectors.toList()); }
 */
    
    
    @GetMapping("/downloadFile/{fileName}")
    public ResponseEntity < Object > downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
    	PatientReport prFile = urservice.getFile(fileName);

		/*
		 * return ResponseEntity.ok()
		 * .contentType(MediaType.parseMediaType(prFile.getFileType()))
		 * .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +
		 * prFile.getFileName() + "\"") .body(new
		 * ByteArrayResource(prFile.getReportPdf()));
		 */
    	
    	 return ResponseEntity.ok()
    	            .contentType(MediaType.parseMediaType(prFile.getFileType()))
    	            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + prFile.getFileName() + "\"")
    	            .body(new ByteArrayResource(prFile.getReportPdf()));
    }

}