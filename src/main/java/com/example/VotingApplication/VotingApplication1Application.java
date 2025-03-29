package com.example.VotingApplication;

import java.awt.Desktop;
import java.net.URI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})

public class VotingApplication1Application {

	public static void main(String[] args) {
		SpringApplication.run(VotingApplication1Application.class, args);
		if(Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(new URI("http://localhost:8888"));
            } catch (Exception e) {
                System.err.println("Failed to open browser: " + e.getMessage());
            }
        } else {
            System.out.println("Desktop not supported. Please open http://localhost:8888 manually.");
        }
	}

}
