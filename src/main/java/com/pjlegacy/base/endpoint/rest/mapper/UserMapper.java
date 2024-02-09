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
        .birthDate(domain.getBirthdate())
        .photo(domain.getPhoto())
        .firebaseId(domain.getFirebaseId())
        .username(domain.getUsername())
        .sex(domain.getSex());
  }

  public com.pjlegacy.base.model.User toDomain(SignUp rest) {
    var sex = rest.getSex();
    return com.pjlegacy.base.model.User.builder()
        .id(rest.getId())
        .bio(rest.getBio())
        .email(rest.getEmail())
        .about(rest.getAbout())
        .firstname(rest.getFirstName())
        .lastname(rest.getLastName())
        .birthdate(rest.getBirthDate())
        .firebaseId(rest.getProviderId())
        .photo(rest.getPhoto())
        .username(rest.getUsername())
        .sex(sex !=null ? sexMapper(sex) : null)
        .build();
  }

  public com.pjlegacy.base.model.User toDomain(User rest) {
    var sex = rest.getSex();
    return com.pjlegacy.base.model.User.builder()
        .id(rest.getId())
        .bio(rest.getBio())
        .email(rest.getEmail())
        .about(rest.getAbout())
        .firstname(rest.getFirstName())
        .lastname(rest.getLastName())
        .birthdate(rest.getBirthDate())
        .firebaseId(rest.getFirebaseId())
        .photo(rest.getPhoto())
        .username(rest.getUsername())
        .sex(sex)
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
