package com.pjlegacy.base.endpoint.rest.mapper;

import com.pjlegacy.base.endpoint.rest.model.SignUp;
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

  public com.pjlegacy.base.model.User toDomain(SignUp domain) {
    var sex = domain.getSex();
    return com.pjlegacy.base.model.User.builder()
        .id(domain.getId())
        .bio(domain.getBio())
        .email(domain.getEmail())
        .about(domain.getAbout())
        .firstname(domain.getFirstName())
        .lastname(domain.getLastName())
        //.birthDate(from(domain.getBirthdate()))
        .photo(domain.getPhoto())
        .username(domain.getUsername())
        .sex(sex !=null ? sexMapper(sex) : null)
        .build();
  }

  private static User.SexEnum sexMapper(SignUp.SexEnum sexEnum){
    return switch (sexEnum){
      case M -> User.SexEnum.M;
      case F -> User.SexEnum.F;
      case OTHER -> User.SexEnum.OTHER;
    };
  }
}
