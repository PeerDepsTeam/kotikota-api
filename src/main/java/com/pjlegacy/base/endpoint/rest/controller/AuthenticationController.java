package com.pjlegacy.base.endpoint.rest.controller;

import com.pjlegacy.base.endpoint.rest.mapper.UserMapper;
import com.pjlegacy.base.endpoint.rest.model.AuthenticationPayload;
import com.pjlegacy.base.endpoint.rest.model.User;
import com.pjlegacy.base.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthenticationController {
  private final UserService userService;
  private final UserMapper userMapper;

  @PostMapping(value = "/signin")
  public User signin(@RequestBody AuthenticationPayload payload) {
    var authUser = userService.findByFirebaseIdAndEmail(payload.getProviderId(), payload.getEmail());
    return userMapper.toRest(authUser);
  }


}
