package edu.ram.learning.spring.ipldashboard;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IplDashboardApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(IplDashboardApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Application started");
		
	}
}
