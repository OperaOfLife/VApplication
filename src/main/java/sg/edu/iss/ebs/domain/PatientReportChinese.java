package sg.edu.iss.ebs.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class PatientReportChinese {


	@Id
    private String reportIdCH;

	private String reportDateCH;
	
	/*
	 * @Id private String reportId ; 
	 */
	
	@Lob
    private byte[] reportPdfCH;
	
	private String fileNameCH;

    private String fileTypeCH;
    
    
	
	
    @OneToOne
	private User userCH;
    
	@OneToOne
    private ReportType typeCH;
	  
	/*
	 * @OneToMany(targetEntity=PatientReportDetails.class,
	 * mappedBy="report",cascade=CascadeType.ALL, fetch = FetchType.LAZY) private
	 * List<PatientReportDetails> reportDetailsCH;
	 */
	
		/*
		 * @OneToMany(targetEntity=Item.class,
		 * mappedBy="patientReport",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
		 * private List<Item> item;
		 */
	 
	  
	   public PatientReportChinese()  {
			super();
		}
	  
	  


		public PatientReportChinese(String fileName,  String fileType ,byte[] reportPdf) {
			super();
			this.reportPdfCH = reportPdf;
			this.fileNameCH = fileName;
			this.fileTypeCH = fileType;
		}




		public PatientReportChinese(String reportId, String reportDate, byte[] reportPdf, String fileName, String fileType,
				User user, ReportType type) {
			super();
			this.reportIdCH = reportId;
			this.reportDateCH = reportDate;
			this.reportPdfCH = reportPdf;
			this.fileNameCH= fileName;
			this.fileTypeCH = fileType;
			this.userCH = user;
			this.typeCH = type;
			this.userCH.setUserId(user.getUserId());
			this.typeCH.setReportName(type.getId()); 
		}
		
		public PatientReportChinese(String reportId, String reportDate, byte[] reportPdf, String fileName, String fileType,
				String userId, String reportName) {
			super();
			this.reportIdCH = reportId;
			this.reportDateCH = reportDate;
			this.reportPdfCH = reportPdf;
			this.fileNameCH= fileName;
			this.fileTypeCH = fileType;
			this.userCH.setUserId(userId);
			this.typeCH.setReportName(reportName); 
		}



		/*
		 * public PatientReportChinese(String reportId, String reportDate, byte[]
		 * reportPdf, String fileName, String fileType, User user, ReportType type,
		 * List<PatientReportDetails> reportDetails) { super(); this.reportIdCH =
		 * reportId; this.reportDateCH = reportDate; this.reportPdfCH = reportPdf;
		 * this.fileNameCH = fileName; this.fileTypeCH = fileType; this.userCH = user;
		 * this.typeCH = type; this.reportDetailsCH = reportDetails; }
		 */



		public String getReportIdCH() {
			return reportIdCH;
		}




		public void setReportIdCH(String reportIdCH) {
			this.reportIdCH = reportIdCH;
		}




		public String getReportDateCH() {
			return reportDateCH;
		}




		public void setReportDateCH(String reportDateCH) {
			this.reportDateCH = reportDateCH;
		}




		public byte[] getReportPdfCH() {
			return reportPdfCH;
		}




		public void setReportPdfCH(byte[] reportPdfCH) {
			this.reportPdfCH = reportPdfCH;
		}




		public String getFileNameCH() {
			return fileNameCH;
		}




		public void setFileNameCH(String fileNameCH) {
			this.fileNameCH = fileNameCH;
		}




		public String getFileTypeCH() {
			return fileTypeCH;
		}




		public void setFileTypeCH(String fileTypeCH) {
			this.fileTypeCH = fileTypeCH;
		}




		public User getUserCH() {
			return userCH;
		}




		public void setUserCH(User userCH) {
			this.userCH = userCH;
		}




		public ReportType getTypeCH() {
			return typeCH;
		}




		public void setTypeCH(ReportType typeCH) {
			this.typeCH = typeCH;
		}




		/*
		 * public List<PatientReportDetails> getReportDetailsCH() { return
		 * reportDetailsCH; }
		 * 
		 * 
		 * 
		 * 
		 * public void setReportDetailsCH(List<PatientReportDetails> reportDetailsCH) {
		 * this.reportDetailsCH = reportDetailsCH; }
		 */
		


		


	
	      

}
