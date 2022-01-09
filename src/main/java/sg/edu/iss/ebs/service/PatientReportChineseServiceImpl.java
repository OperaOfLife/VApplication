package sg.edu.iss.ebs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.ebs.domain.PatientReport;
import sg.edu.iss.ebs.domain.PatientReportChinese;
import sg.edu.iss.ebs.repo.PatientReportChineseRepository;
import sg.edu.iss.ebs.repo.PatientReportRepository;

@Service
public class PatientReportChineseServiceImpl implements PatientReportChineseService{
	
	@Autowired
	PatientReportChineseRepository prcrepo;

	@Override
	public List<PatientReportChinese> findAllReports(String userId) {
		// TODO Auto-generated method stub
		return prcrepo.findAllReportsById(userId);
	}

}
