package sg.edu.iss.ebs.service;

import sg.edu.iss.ebs.domain.User;

public interface EmailService 
{
	public void sendEmail(User user,String Newpwd);
}
