package com.group.eventmanagement;

import com.group.eventmanagement.model.Person;
import com.group.eventmanagement.persistence.PersonRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@SpringBootApplication
//@ComponentScan(basePackages = {"com.group.eventmanagement"})
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class EventManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventManagementApplication.class, args);
	}
	
	@RequestMapping("/")
	public String greeting(){
		return "Hello!";
	}

}
