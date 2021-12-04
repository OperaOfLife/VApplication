package sg.edu.iss.ebs;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableAutoConfiguration
public class EbsApplication
{
	

	public static void main(String[] args) 
	{
		SpringApplication.run(EbsApplication.class, args);
	}
	
	

}
