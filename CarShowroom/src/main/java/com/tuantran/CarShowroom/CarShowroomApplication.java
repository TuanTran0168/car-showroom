package com.tuantran.CarShowroom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // Required for @CreatedDate and @LastModifiedDate to work
public class CarShowroomApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarShowroomApplication.class, args);
	}

}
