package sg.edu.iss.ebs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.ebs.domain.Family;
import sg.edu.iss.ebs.repo.FamilyRepository;

@Service
public class FamilyServiceImpl implements FamilyService {
	
	
	@Autowired
	FamilyRepository frepo;

	@Override
	public void createFamily(Family family) {
		 frepo.save(family);
		
	}

}
