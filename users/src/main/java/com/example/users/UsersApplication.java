package com.example.users;


import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UsersApplication {

    @Setter(onMethod_ = {@Value("${app.id}")})
    private String id;

    public static void main(String[] args) {
        SpringApplication.run(UsersApplication.class, args);
    }

}
