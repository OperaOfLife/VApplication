package sg.edu.iss.ebs.service;

import java.util.List;

import sg.edu.iss.ebs.domain.Family;
import sg.edu.iss.ebs.domain.User;

public interface FamilyService {

	void createFamily(Family family);

	Family findByName(String uname);

	void addFamilyId(String uname, String key);

	

	void deleteFamilyId(String familyUsername);

	List<Family> getAllMembers(String s);

}
