package com.courseapp.courseservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("userservice")
public interface UserFeignClient {

    @PostMapping("/users/names")
    List<String> getUserNames(@RequestBody List<Long> userIdList);
}
