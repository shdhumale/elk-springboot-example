package com.siddhu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@RestController
public class ElkSpringbootExampleApplication {
	
	Logger logger=LoggerFactory.getLogger(ElkSpringbootExampleApplication.class);

    @GetMapping("/getUser/{id}")
    public User getUserById(@PathVariable int id) {
		List<User> users=getUsers();
		User user=users.stream().
				filter(u->u.getId()==id).findAny().orElse(null);
		if(user!=null){
			logger.info("user found : {}",user);
			return user;
		}else{
			try {
				throw new Exception();
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("Error User Not Found with this ID : {}",id);
			}
			return new User();
		}
    }


    private List<User> getUsers() {
        return Stream.of(new User(1, "user1"),
				new User(2, "user2"),
				new User(3, "user3"),
				new User(4, "user4"))
				.collect(Collectors.toList());
    }

	public static void main(String[] args) {
		SpringApplication.run(ElkSpringbootExampleApplication.class, args);
	}

}
