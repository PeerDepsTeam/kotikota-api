package com.pjlegacy.base.endpoint.rest.controller;

import com.pjlegacy.base.endpoint.rest.mapper.UserMapper;
import com.pjlegacy.base.endpoint.rest.model.AuthenticationPayload;
import com.pjlegacy.base.endpoint.rest.model.SignUp;
import com.pjlegacy.base.endpoint.rest.model.User;
import com.pjlegacy.base.model.validator.UserValidator;
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
  private final UserValidator validator;

  @PostMapping(value = "/signin")
  public User signin(@RequestBody AuthenticationPayload payload) {
    var authUser = userService.findByFirebaseIdAndEmail(payload.getProviderId(), payload.getEmail());
    return userMapper.toRest(authUser);
  }

  @PostMapping(value = "/signup")
  public User signup(@RequestBody SignUp payload){
    var toSave = userMapper.toDomain(payload);
    validator.accept(toSave);
    return userMapper.toRest(userService.save(toSave));
  }


}
