package sg.edu.iss.ebs.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import sg.edu.iss.ebs.domain.Item;
import sg.edu.iss.ebs.domain.PatientReport;
import sg.edu.iss.ebs.domain.PatientReportDetails;
import sg.edu.iss.ebs.repo.PatientReportDetailsRepository;
import sg.edu.iss.ebs.repo.PatientReportRepository;

@Service
public class ExportExcelToDBServiceImpl implements ExportExcelToDBService {
	
	
	@Value("${app.upload.file:${user.home}}")
	public String EXCEL_FILE_PATH;

	@Autowired
	PatientReportDetailsRepository prdrepo;
	
	
	@Autowired
	PatientReportService prservice;
	
	@Autowired
	ItemService iservice;

	Workbook workbook;
	
	public List<String> reportname=new ArrayList<>();
	public List<PatientReportDetails> getExcelDataAsList(String filepath) {

		List<String> list = new ArrayList<String>();

		// Create a DataFormatter to format and get each cell's value as String
		DataFormatter dataFormatter = new DataFormatter();

		// Create the Workbook
		try {
			workbook = WorkbookFactory.create(new File(filepath));
		} catch (EncryptedDocumentException | IOException e) {
			e.printStackTrace();
		}

		// Retrieving the number of sheets in the Workbook
		System.out.println("-------Workbook has '" + workbook.getNumberOfSheets() + "' Sheets-----");

		// Getting the Sheet at index zero
		Sheet sheet = workbook.getSheetAt(0);

		// Getting number of columns in the Sheet
		int noOfColumns = sheet.getRow(0).getLastCellNum();
		System.out.println("-------Sheet has '"+noOfColumns+"' columns------");

		// Using for-each loop to iterate over the rows and columns
		for (Row row : sheet) {
			for (Cell cell : row) {
				String cellValue = dataFormatter.formatCellValue(cell);
				list.add(cellValue);
			}
		}

		// filling excel data and creating list as List<Invoice>
		List<PatientReportDetails> reportList = createList(list, noOfColumns);

		// Closing the workbook
		try {
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return reportList;
	}

	private List<PatientReportDetails> createList(List<String> excelData, int noOfColumns) {

		ArrayList<PatientReportDetails> detailsList = new ArrayList<PatientReportDetails>();

		int i = noOfColumns;
		
		for(int j=0;j<noOfColumns;j++)
		{
			reportname.add(excelData.get(j));
		}
		
		
			
		int k=0;
		
		do
			
		{
			
		do {
			
			
			PatientReportDetails details = new PatientReportDetails();
			
			
			details.setItemName(excelData.get(i));
			details.setIntensity(excelData.get(i + k + 1));
			
			//Item id=iservice.findByName(excelData.get(i));
			
			
			
			
			String id=reportname.get(k +1);

			PatientReport prep = prservice.findPatientById(id);
			
			details.setReport(prep);
			details.getReport().setReportId(reportname.get(k +1));
			
			
			//details.setReport(reportname.get(k +1));
			//details.getReport().setReportId(reportname.get(k +1));
			
			//inv.setItem(excelData.get(i));
			//inv.setIntensity(excelData.get(i + k + 1));
			//inv.setReportId(reportname.get(k +1));
			//inv.setDescription(desc);
			

			detailsList.add(details);
			i = i + (noOfColumns);

		} while (i < excelData.size());
		
		i=noOfColumns;
		
		k=k+1;
		}
		
		while (k< (noOfColumns -1) );
		
		
		return detailsList;
	}

	@Override
	public int saveExcelData(List<PatientReportDetails> reportDetails) {
		int count=0;
		for(PatientReportDetails p: reportDetails)
		{
			    prdrepo.save(p);
			    count=count+1;
		}
		//reportDetails = prdrepo.saveAll(reportDetails);
		//return reportDetails.size();
		return count;
	}
}
