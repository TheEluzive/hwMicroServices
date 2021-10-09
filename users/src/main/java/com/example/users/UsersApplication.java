package com.example.users;


import com.example.users.dto.UsernameDto;
import com.example.users.services.UserService;
import lombok.Setter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SpringBootApplication
@RestController

public class UsersApplication {
  private final Log logger = LogFactory.getLog(this.getClass());
  private UserService userService;

  @Bean
  public UserService startService(JdbcTemplate jdbcTemplate){
    this.userService = new UserService(jdbcTemplate);
    return this.userService;
  };

  @PostMapping("/getUsersByArrayId")


  public ArrayList<UsernameDto> getUser(@RequestBody HashMap<Long, Long> senderIdMap, HttpServletRequest request){
    final var users = new ArrayList<UsernameDto>();
    for (Long id: senderIdMap.values()) {
      users.add(
              new UsernameDto(
                      id,
                      userService.getUsernameById(id)
              )
      );
    }

    return users;
  }

  @Setter(onMethod_={@Value("${app.id}")})
  private String id;



  public static void main(String[] args) {
    SpringApplication.run(UsersApplication.class, args);
  }


}