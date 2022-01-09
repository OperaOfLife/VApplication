package sg.edu.iss.ebs.service;

import java.util.List;

import sg.edu.iss.ebs.domain.PatientReport;
import sg.edu.iss.ebs.domain.PatientReportChinese;

public interface PatientReportChineseService {

	List<PatientReportChinese> findAllReports(String userId);

}
