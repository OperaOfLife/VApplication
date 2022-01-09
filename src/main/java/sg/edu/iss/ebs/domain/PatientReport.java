package sg.edu.iss.ebs.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class PatientReport {
	
	
	@Id
    private String reportId;

	private String reportDate;
	
	/*
	 * @Id private String reportId ; 
	 */
	
	@Lob
    private byte[] reportPdf;
	
	private String fileName;

    private String fileType;
    
    
	
	
    @OneToOne
	private User user;
    
	@OneToOne
    private ReportType type;
	  
	@OneToMany(targetEntity=PatientReportDetails.class, mappedBy="report",cascade=CascadeType.ALL, fetch = FetchType.LAZY)    
	private List<PatientReportDetails> reportDetails;
	
	@OneToMany(targetEntity=PatientReportGEDetails.class, mappedBy="reportGE",cascade=CascadeType.ALL, fetch = FetchType.LAZY)    
	private List<PatientReportGEDetails> reportDetailsGE;

	
		/*
		 * @OneToMany(targetEntity=Item.class,
		 * mappedBy="patientReport",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
		 * private List<Item> item;
		 */
	 
	  
	  public PatientReport() {
			super();
		}
	  
	  


		public PatientReport(String fileName,  String fileType ,byte[] reportPdf) {
			super();
			this.reportPdf = reportPdf;
			this.fileName = fileName;
			this.fileType = fileType;
		}




		public PatientReport(String reportId, String reportDate, byte[] reportPdf, String fileName, String fileType,
				User user, ReportType type) {
			super();
			this.reportId = reportId;
			this.reportDate = reportDate;
			this.reportPdf = reportPdf;
			this.fileName = fileName;
			this.fileType = fileType;
			this.user = user;
			this.type = type;
			this.user.setUserId(user.getUserId());
			this.type.setReportName(type.getId()); 
		}
		
		public PatientReport(String reportId, String reportDate, byte[] reportPdf, String fileName, String fileType,
				String userId, String reportName) {
			super();
			this.reportId = reportId;
			this.reportDate = reportDate;
			this.reportPdf = reportPdf;
			this.fileName = fileName;
			this.fileType = fileType;
			this.user.setUserId(userId);
			this.type.setReportName(reportName); 
		}



		public PatientReport(String reportId, String reportDate, byte[] reportPdf, String fileName, String fileType,
				User user, ReportType type, List<PatientReportDetails> reportDetails) {
			super();
			this.reportId = reportId;
			this.reportDate = reportDate;
			this.reportPdf = reportPdf;
			this.fileName = fileName;
			this.fileType = fileType;
			this.user = user;
			this.type = type;
			this.reportDetails = reportDetails;
		}
		
		
		
		public PatientReport(String reportId, String reportDate, byte[] reportPdf, String fileName, String fileType,
				User user, ReportType type, List<PatientReportDetails> reportDetails,
				List<PatientReportGEDetails> reportDetailsGE) {
			super();
			this.reportId = reportId;
			this.reportDate = reportDate;
			this.reportPdf = reportPdf;
			this.fileName = fileName;
			this.fileType = fileType;
			this.user = user;
			this.type = type;
			this.reportDetails = reportDetails;
			this.reportDetailsGE = reportDetailsGE;
		}
		  
	  
	  
	 



		public String getReportId() {
			return reportId;
		}


		public void setReportId(String reportId) {
			this.reportId = reportId;
		}


		public String getReportDate() {
			return reportDate;
		}


		public void setReportDate(String reportDate) {
			this.reportDate = reportDate;
		}


		public byte[] getReportPdf() {
			return reportPdf;
		}


		public void setReportPdf(byte[] reportPdf) {
			this.reportPdf = reportPdf;
		}


		public String getFileName() {
			return fileName;
		}


		public void setFileName(String fileName) {
			this.fileName = fileName;
		}


		public String getFileType() {
			return fileType;
		}


		public void setFileType(String fileType) {
			this.fileType = fileType;
		}


		public User getUser() {
			return user;
		}


		public void setUser(User user) {
			this.user = user;
		}


		public ReportType getType() {
			return type;
		}


		public void setType(ReportType type) {
			this.type = type;
		}


		public List<PatientReportDetails> getReportDetails() {
			return reportDetails;
		}


		public void setReportDetails(List<PatientReportDetails> reportDetails) {
			this.reportDetails = reportDetails;
		}




		public List<PatientReportGEDetails> getReportDetailsGE() {
			return reportDetailsGE;
		}




		public void setReportDetailsGE(List<PatientReportGEDetails> reportDetailsGE) {
			this.reportDetailsGE = reportDetailsGE;
		}




		
	
	      

}
