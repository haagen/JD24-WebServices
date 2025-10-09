package se.apiva.demospringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class DemoSpringBootApplication {

    public static void main(String[] args) {

        var passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("adminpassword"));
        System.out.println(passwordEncoder.encode("userpassword"));


        SpringApplication.run(DemoSpringBootApplication.class, args);
    }

}
