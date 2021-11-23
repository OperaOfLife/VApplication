package sg.edu.iss.ebs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sg.edu.iss.ebs.domain.PatientReportDetails;

import sg.edu.iss.ebs.repo.PatientReportDetailsRepository;
import sg.edu.iss.ebs.service.ExportExcelToDBService;
import sg.edu.iss.ebs.service.FileExporterService;

@Controller
@RequestMapping("/export")
public class ExportExcelToDBController {
	
private static String UPLOADED_FOLDER1 = "D://VIITAR//";

	
	@Autowired
	FileExporterService fileService;
	
	@Autowired
	ExportExcelToDBService excelservice;
	
	@Autowired
	PatientReportDetailsRepository prdrepo;
	
	
	
	@GetMapping("/page")
    public String index(Model model) {
    	
    	
    	model.addAttribute("export",new  PatientReportDetails());
        return "export-excel-data";
    }
	
	

	

    @PostMapping("/uploadFile")
    public String uploadFile( @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
    	
		/*
		 * Path path = Paths.get(UPLOADED_FOLDER +
		 * StringUtils.cleanPath(file.getOriginalFilename()));
		 * 
		 * pathxsl=path.toString();
		 */

        fileService.uploadFile(file);
        
        

        redirectAttributes.addFlashAttribute("message",
            "You have successfully uploaded '"+ file.getOriginalFilename()+"' !");
        try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "redirect:/page";
    }
    
    @RequestMapping("/saveData")
    public String saveExcelData(@ModelAttribute("invoice") PatientReportDetails reportDetails,@RequestParam("file") MultipartFile file,
    										Model model,BindingResult bindingresult) 
    {
    	String filePath=UPLOADED_FOLDER1 + file.getOriginalFilename();
    	//String details=reportDetails.getDescription();
    	
    	List<PatientReportDetails> excelDataAsList = excelservice.getExcelDataAsList(filePath);
    	int noOfRecords = excelservice.saveExcelData(excelDataAsList);
    	model.addAttribute("noOfRecords",noOfRecords);
    	
    	return "export-excel-data-confirmation";
    }


}
