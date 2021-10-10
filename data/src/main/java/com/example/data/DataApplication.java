package com.example.data;

import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DataApplication {

  @Setter(onMethod_={@Value("${app.id}")})
  private String id;

  public static void main(String[] args) {
    SpringApplication.run(DataApplication.class, args);
  }
}
