package sg.edu.iss.ebs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import sg.edu.iss.ebs.domain.User;


@Service
public class EmailServiceImpl implements EmailService
{

	private JavaMailSender javaMailSender;


	@Autowired
	UserService uservice;
	
	
	
	@Autowired
	public EmailServiceImpl(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	@Override
	public void sendEmail(User u,String newpwd) throws MailException
	{

		User user=uservice.findByName(u.getUserId());
		String email=u.getEmail();
		

		SimpleMailMessage mail = new SimpleMailMessage();
		
		mail.setTo(email);
		mail.setSubject("Password Recovery");
		mail.setText("Your new password is "+ newpwd);

		
		javaMailSender.send(mail);
	}

	
}
