package com.pjlegacy.base.endpoint.rest.controller;

import com.pjlegacy.base.endpoint.rest.mapper.UserMapper;
import com.pjlegacy.base.endpoint.rest.model.User;
import com.pjlegacy.base.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {
  private final UserService service;
  private final UserMapper mapper;

  @GetMapping(value = "/users/{id}")
  public User getUserById(@PathVariable("id") String id){
    return mapper.toRest(service.getUserById(id));
  }

  @PutMapping("/users/{id}")
  public User crupdateUser(@RequestBody User toSave){
    var domain = mapper.toDomain(toSave);
    return mapper.toRest(service.save(domain));
  }

}
