package com.pjlegacy.base.endpoint.rest.mapper;

import com.pjlegacy.base.endpoint.rest.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

  public User toRest(com.pjlegacy.base.model.User domain) {
    return new User()
        .id(domain.getId())
        .bio(domain.getBio())
        .email(domain.getEmail())
        .about(domain.getAbout())
        .firstName(domain.getFirstname())
        .lastName(domain.getLastname())
        //.birthDate(from(domain.getBirthdate()))
        .photo(domain.getPhoto())
        .username(domain.getUsername())
        .sex(domain.getSex());
  }
}
