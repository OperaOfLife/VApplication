package sg.edu.iss.ebs.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import sg.edu.iss.ebs.domain.PatientReportGEDetails;
import sg.edu.iss.ebs.repo.PatientReportDetailsGERepository;
import sg.edu.iss.ebs.repo.PatientReportDetailsRepository;
import sg.edu.iss.ebs.repo.PatientReportRepository;

@Service
public class ExportExcelToDBServiceImpl implements ExportExcelToDBService {
	
	
	@Value("${app.upload.file:${user.home}}")
	public String EXCEL_FILE_PATH;

	@Autowired
	PatientReportDetailsRepository prdrepo;
	
	@Autowired
	PatientReportDetailsGERepository prd224repo;
	
	
	@Autowired
	PatientReportService prservice;
	
	@Autowired
	ItemService iservice;

	Workbook workbook;
	
	Workbook workbook224;
	
	public List<String> reportname=new ArrayList<>();
	public List<PatientReportDetails> getExcelDataAsList(String filepath) {

		List<String> list = new ArrayList<String>();
		
		List<String> reportIdNames = new ArrayList<String>();

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
		Sheet sheet = workbook.getSheetAt(1);

		// Getting number of columns in the Sheet
		int noOfColumns = sheet.getRow(0).getLastCellNum();
		System.out.println("-------Sheet has '"+noOfColumns+"' columns------");
		
		
		int totalrows = 0;
		int columnNum = 1;
		for (Row row : sheet) {
		    if (row.getCell(columnNum) != null) {
		    	totalrows =totalrows+ 1;
		    	reportIdNames.add((row.getCell(columnNum)).toString());
		    }
		    if (row.getCell(columnNum) == null)
		    	break;
		}
		System.out.println("-------Sheet has '"+totalrows+"' rows------");
		

		// Using for-each loop to iterate over the rows and columns
		for (Row row : sheet) {
			for (Cell cell : row) {
				String cellValue = dataFormatter.formatCellValue(cell);
				list.add(cellValue);
			}
		}
		
		// filling excel data and creating list as List<Invoice>
		List<PatientReportDetails> reportList = createList(reportIdNames,list,totalrows, noOfColumns);

		// Closing the workbook
		try {
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return reportList;
	}

	private List<PatientReportDetails> createList(List<String> reportname,List<String> excelData,int noOfRows, int noOfColumns) {

		ArrayList<PatientReportDetails> detailsList = new ArrayList<PatientReportDetails>();
		
		int i = 10;
		
		int totalrows=noOfRows;
		int totalcols=noOfColumns;
		
		int counter=0;
		int x=0;
		int rowcounter=1;
		
		int totalsize=noOfRows*noOfColumns;
		
		
		int k=0;
		
		
		
		for(int q=0;q<totalrows-1;q++)
		{	
			
		
		for(int p=10;p<totalcols;p++)
		 {
		
			PatientReportDetails details = new PatientReportDetails(); 
		  String itemname1="";
		  String itemname=excelData.get(p).toString();
		  
		  StringBuilder sb=new StringBuilder(itemname);	
		  
		  if(!(Character.isLetter(sb.charAt(0))))
			  sb.deleteCharAt(0);
		  
		   itemname1=sb.toString();
		  				 
					
		details.setItemName(itemname1);
		
		x= p + ((noOfColumns)*rowcounter);
		
		details.setIntensity(excelData.get(x));
		
		String id=reportname.get(k +1);
		
		PatientReport prep = prservice.findPatientById(id);
		
		details.setReport(prep);
		details.getReport().setReportId(reportname.get(k +1));
		
		detailsList.add(details);
		
		 }
		rowcounter=rowcounter+1;
		k=k+1;
		}
		
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
	
	
	
	
	
	public List<String> reportname224=new ArrayList<>();
	
	public List<PatientReportGEDetails> getExcelDataAsList224(String filepath) {

		List<String> listE = new ArrayList<String>();
		List<String> listG = new ArrayList<String>();
		
		List<String> reportIdNamesG = new ArrayList<String>();
		List<String> reportIdNamesE = new ArrayList<String>();

		// Create a DataFormatter to format and get each cell's value as String
		DataFormatter dataFormatter = new DataFormatter();

		// Create the Workbook
		try {
			workbook224 = WorkbookFactory.create(new File(filepath));
		} catch (EncryptedDocumentException | IOException e) {
			e.printStackTrace();
		}

		// Retrieving the number of sheets in the Workbook
		System.out.println("-------Workbook has '" + workbook224.getNumberOfSheets() + "' Sheets-----");

		// Getting the Sheet at index zero
		Sheet sheetE = workbook224.getSheetAt(2);
		Sheet sheetG = workbook224.getSheetAt(1);
		
		
		// Getting number of columns in the Sheet
		int noOfColumnsG = sheetG.getRow(0).getLastCellNum();
		System.out.println("-------Sheet has '"+noOfColumnsG+"' columns------");
		
		
		int totalrowsG = 0;
		int columnNumG = 1;
		for (Row row : sheetG) {
		    if (row.getCell(columnNumG) != null) {
		    	totalrowsG =totalrowsG + 1;
		    	reportIdNamesG.add((row.getCell(columnNumG)).toString());
		    }
		    if (row.getCell(columnNumG) == null)
		    	break;
		}
		System.out.println("-------Sheet has '"+totalrowsG+"' rows------");
		

		// Using for-each loop to iterate over the rows and columns
		for (Row row : sheetG) {
			for (Cell cell : row) {
				String cellValue = dataFormatter.formatCellValue(cell);
				listG.add(cellValue);
			}
		}
		
		

		// Getting number of columns in the Sheet
		int noOfColumnsE = sheetE.getRow(0).getLastCellNum();
		System.out.println("-------Sheet has '"+noOfColumnsE+"' columns------");
		
		
		int totalrowsE = 0;
		int columnNumE = 1;
		for (Row row : sheetE) {
		    if (row.getCell(columnNumE) != null) {
		    	totalrowsE =totalrowsE+ 1;
		    	reportIdNamesE.add((row.getCell(columnNumE)).toString());
		    }
		    if (row.getCell(columnNumE) == null)
		    	break;
		}
		System.out.println("-------Sheet has '"+totalrowsE+"' rows------");
		

		// Using for-each loop to iterate over the rows and columns
		for (Row row : sheetE) {
			for (Cell cell : row) {
				String cellValue = dataFormatter.formatCellValue(cell);
				listE.add(cellValue);
			}
		}
		
		
		
		List<PatientReportGEDetails> reportList224 = createList224(reportIdNamesG,reportIdNamesE,listG,listE,totalrowsG, noOfColumnsG,totalrowsE, noOfColumnsE);

		// Closing the workbook
		try {
			workbook224.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return reportList224;
	}
	
	

	private List<PatientReportGEDetails> createList224(List<String> reportnameG,List<String> reportnameE,List<String> excelDataG,List<String> excelDataE,int noOfRowsG, int noOfColumnsG,int noOfRowsE, int noOfColumnsE)
	{

		List<PatientReportGEDetails> detailsList224 = new ArrayList<PatientReportGEDetails>();
		
		
		int i = 10;
		
		int totalrowsG=noOfRowsG;
		int totalcolsG=noOfColumnsG;
		
		int counterG=0;
		int xG=0;
		int rowcounterG=1;
		
		int totalsizeG=noOfRowsG*noOfColumnsG;
		
		
		
		int totalrowsE=noOfRowsE;
		int totalcolsE=noOfColumnsE;
		
		int counterE=0;
		int xE=0;
		int rowcounterE=1;
		
		int totalsizeE=noOfRowsE*noOfColumnsE;
		
		Map<String, Map<String,String>> detailsG = new HashMap<String, Map<String,String>>();
		Map<String, Map<String,String>> detailsE = new HashMap<String,Map<String,String>>();
		
		
		int kE=0;
		
		for(int q=0;q<totalrowsE-1;q++)
		{	
			
			String reportId=reportnameE.get(kE +1);
			detailsE.put(reportId, new HashMap<String,String>());
		
			for(int p=10;p<totalcolsE;p++)
		 {
		
			
		  String itemnameE="";
		  String itemname=excelDataE.get(p).toString();
		  
		  StringBuilder sb=new StringBuilder(itemname);	
		  
		  if(!(Character.isLetter(sb.charAt(0))))
			  sb.deleteCharAt(0);
		  
		   itemnameE=sb.toString();
		   
		   xE= p + ((noOfColumnsE)*rowcounterE);
		   
		   	 
			  String intensityE=excelDataE.get(xE);
			  detailsE.get(reportId).put(itemnameE,intensityE );
			  //detailsE.get(reportId).add(excelDataE.get(xE));
		  				 
		
		 }
		rowcounterE=rowcounterE+1;
		
		kE=kE+1;
		}
		
		int kG=0;
		
		for(int q=0;q<totalrowsG-1;q++)
		{	
			
			String reportId=reportnameG.get(kG +1);
			detailsG.put(reportId, new HashMap<String,String>());
			
		for(int p=10;p<totalcolsG;p++)
		 {
		
			
		  String itemnameG="";
		  String itemname=excelDataG.get(p).toString();
		  
		  StringBuilder sb=new StringBuilder(itemname);	
		  
		  if(!(Character.isLetter(sb.charAt(0))))
			  sb.deleteCharAt(0);
		  
		   itemnameG=sb.toString();
		   
		   xG= p + ((noOfColumnsG)*rowcounterG);
		   
		   	 
			  
		   String intensityG=excelDataG.get(xG);
			  detailsG.get(reportId).put(itemnameG,intensityG );
		  				 
		
		 }
		
		rowcounterG=rowcounterG+1;
		
		kG=kG+1;
		}
		
		
		
		
		

        
			  
		  for(Map.Entry<String, Map<String,String>> mapE :detailsE.entrySet() )
			{
			  
			  
				String keyE = mapE.getKey(); // contains the keys
				Map<String,String> valuesE = mapE.getValue(); // contains arraylists

				
				for(Map.Entry<String,String> mapIntoE :valuesE.entrySet() )
				{
					PatientReportGEDetails detailsGE = new PatientReportGEDetails(); 
					
					String currentItem=mapIntoE.getKey();
					
					PatientReport prep = prservice.findPatientById(keyE);
					
					detailsGE.setReportGE(prep); 
					
					detailsGE.getReportGE().setReportId(keyE);
	           
				detailsGE.setItemName(currentItem);
					  
				detailsGE.setIntensityE(mapIntoE.getValue());
					
				
				
				for(Map.Entry<String, Map<String,String>> mapG :detailsG.entrySet() )
					{
					
					String keyG = mapG.getKey(); // contains the keys
					
					if(keyG.equalsIgnoreCase(keyE))
						{
					
					Map<String,String> valuesG = mapG.getValue(); // contains arraylists
					
					String intensityG="null";
					
					for(Map.Entry<String,String> mapIntoG :valuesG.entrySet() )
							{
							String matchingItem=mapIntoG.getKey();
							if(currentItem.equalsIgnoreCase(matchingItem))
								{
								intensityG=mapIntoG.getValue();
								
								}
							
							}
					detailsGE.setIntensityG(intensityG);
					
					detailsList224.add(detailsGE);
					
						}
					}
				
				
				}
				
				 
			 }
		  
		  System.out.println("HELLOO");
		
		return detailsList224;
	}

	
	
	@Override
	public int saveExcelData224(List<PatientReportGEDetails> reportDetailsGE) {
		int count=0;
		for(PatientReportGEDetails p: reportDetailsGE)
		{
			    prd224repo.save(p);
			    count=count+1;
		}
		//reportDetails = prdrepo.saveAll(reportDetails);
		//return reportDetails.size();
		return count;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * public List<PatientReportDetails> getExcelDataAsList1(String filepath) {
	 * 
	 * List<String> list = new ArrayList<String>();
	 * 
	 * // Create a DataFormatter to format and get each cell's value as String
	 * DataFormatter dataFormatter = new DataFormatter();
	 * 
	 * // Create the Workbook try { workbook = WorkbookFactory.create(new
	 * File(filepath)); } catch (EncryptedDocumentException | IOException e) {
	 * e.printStackTrace(); }
	 * 
	 * // Retrieving the number of sheets in the Workbook
	 * System.out.println("-------Workbook has '" + workbook.getNumberOfSheets() +
	 * "' Sheets-----");
	 * 
	 * // Getting the Sheet at index zero Sheet sheet = workbook.getSheetAt(0);
	 * 
	 * // Getting number of columns in the Sheet int noOfColumns =
	 * sheet.getRow(0).getLastCellNum();
	 * System.out.println("-------Sheet has '"+noOfColumns+"' columns------");
	 * 
	 * // Using for-each loop to iterate over the rows and columns for (Row row :
	 * sheet) { for (Cell cell : row) { String cellValue =
	 * dataFormatter.formatCellValue(cell); list.add(cellValue); } }
	 * 
	 * // filling excel data and creating list as List<Invoice>
	 * List<PatientReportDetails> reportList = createList1(list, noOfColumns);
	 * 
	 * // Closing the workbook try { workbook.close(); } catch (IOException e) { //
	 * TODO Auto-generated catch block e.printStackTrace(); }
	 * 
	 * return reportList; }
	 * 
	 * private List<PatientReportDetails> createList1(List<String> excelData, int
	 * noOfColumns) {
	 * 
	 * ArrayList<PatientReportDetails> detailsList = new
	 * ArrayList<PatientReportDetails>();
	 * 
	 * int i = noOfColumns;
	 * 
	 * for(int j=0;j<noOfColumns;j++) { reportname.add(excelData.get(j)); }
	 * 
	 * 
	 * 
	 * int k=0;
	 * 
	 * do
	 * 
	 * {
	 * 
	 * do {
	 * 
	 * 
	 * PatientReportDetails details = new PatientReportDetails();
	 * 
	 * 
	 * details.setItemName(excelData.get(i)); details.setIntensity(excelData.get(i +
	 * k + 1));
	 * 
	 * //Item id=iservice.findByName(excelData.get(i));
	 * 
	 * 
	 * 
	 * 
	 * String id=reportname.get(k +1);
	 * 
	 * PatientReport prep = prservice.findPatientById(id);
	 * 
	 * details.setReport(prep); details.getReport().setReportId(reportname.get(k
	 * +1));
	 * 
	 * 
	 * //details.setReport(reportname.get(k +1));
	 * //details.getReport().setReportId(reportname.get(k +1));
	 * 
	 * //inv.setItem(excelData.get(i)); //inv.setIntensity(excelData.get(i + k +
	 * 1)); //inv.setReportId(reportname.get(k +1)); //inv.setDescription(desc);
	 * 
	 * 
	 * detailsList.add(details); i = i + (noOfColumns);
	 * 
	 * } while (i < excelData.size());
	 * 
	 * i=noOfColumns;
	 * 
	 * k=k+1; }
	 * 
	 * while (k< (noOfColumns -1) );
	 * 
	 * 
	 * return detailsList; }
	 * 
	 * 
	 */
	
	
	
	
	
	
	
	
	
	
}
