package br.com.futurodev.semana3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.util.TimeZone;

// @SpringBootApplication(exclude = {SecurityAutoConfiguration.class}) // remover a propriedade exclude para funcionar token
@SpringBootApplication
public class Semana3Application {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        SpringApplication.run(Semana3Application.class, args);
        System.out.println(new BCryptPasswordEncoder().encode("203040"));
    }

}
