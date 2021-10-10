package com.example.aggregator.client;

import com.example.aggregator.dto.UsernameDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.HashMap;

@FeignClient(value = "users")
public interface UsersClient {
    @PostMapping("/getUsersByArrayId")
    ArrayList<UsernameDto> getValue(HashMap<Long, Long> senderIdMap);
}
