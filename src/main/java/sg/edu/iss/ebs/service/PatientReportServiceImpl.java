package sg.edu.iss.ebs.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.ebs.domain.PatientReport;
import sg.edu.iss.ebs.domain.PatientReportDetails;
import sg.edu.iss.ebs.repo.PatientReportDetailsRepository;
import sg.edu.iss.ebs.repo.PatientReportRepository;

@Service
public class PatientReportServiceImpl implements PatientReportService
{
	
	@Autowired
	PatientReportRepository prrepo;
	
	@Autowired
	PatientReportDetailsRepository prdrepo;

	@Override
	public PatientReport findPatientById(String id) {
		return prrepo.findPatientReportByReportId(id);
	}

	@Override
	public List<PatientReportDetails> findDetailsByReportId(String reportId) {
		
		return prdrepo.findDetailedReportByReportId(reportId);
	}

	@Override
	public List<PatientReport> findAllReports(String id) {
		// TODO Auto-generated method stub
		return prrepo.findAllReportsById(id);
	}
	
	@Override
	public byte[] getPdf(String filename) {
		// TODO Auto-generated method stub
		return prrepo.findPdf(filename);
	}

	
	
	

}
