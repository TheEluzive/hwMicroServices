package com.example.users.controllers;

import com.example.users.dto.UsernameDto;
import com.example.users.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@AllArgsConstructor
public class UserControllers {
    private final UserService userService;

    @PostMapping("/getUsersByArrayId")
    public ArrayList<UsernameDto> getUser(@RequestBody HashMap<Long, Long> senderIdMap, HttpServletRequest request) {
        final var users = new ArrayList<UsernameDto>();
        for (Long id : senderIdMap.values()) {
            users.add(
                    new UsernameDto(
                            id,
                            userService.getUsernameById(id)
                    )
            );
        }

        return users;
    }
}
