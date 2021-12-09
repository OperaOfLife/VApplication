package sg.edu.iss.ebs.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.ebs.domain.Family;
import sg.edu.iss.ebs.domain.User;
import sg.edu.iss.ebs.repo.FamilyRepository;

@Service
public class FamilyServiceImpl implements FamilyService {
	
	
	@Autowired
	FamilyRepository frepo;

	@Override
	public void createFamily(Family family) {
		 frepo.save(family);
		
	}

	@Override
	public Family findByName(String uname) {
		// TODO Auto-generated method stub
		return frepo.findByUserId(uname);
	}

	@Override
	public void addFamilyId(String uname, String key) {
		// TODO Auto-generated method stub
		frepo.addFamilyId(uname,key);
		
	}
	
	
	
	@Override
	public void deleteFamilyId(String familyUsername) {
		// TODO Auto-generated method stub
		
		String key=UUID.randomUUID().toString();
		
		frepo.deleteFamilyId(familyUsername,key);
		
	}

	@Override
	public List<Family> getAllMembers(String familyId) {
		// TODO Auto-generated method stub
		return frepo.getFamily(familyId);
	}

}
