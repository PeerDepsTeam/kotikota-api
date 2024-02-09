package com.pjlegacy.base.model.validator;

import com.pjlegacy.base.model.User;
import com.pjlegacy.base.model.exception.BadRequestException;
import java.util.function.Consumer;
import org.springframework.stereotype.Component;

@Component
public class UserValidator implements Consumer<User> {

  @Override
  public void accept(User user) {
    var stringBuilder = new StringBuilder();
    if (user.getId() == null){
      stringBuilder.append("Id is mandatory ");
    }
    if (user.getFirstname() == null){
      stringBuilder.append("FistName is mandatory ");
    }
    if (user.getFirstname() == null){
      stringBuilder.append("LastName is mandatory ");
    }
    if (user.getEmail() == null){
      stringBuilder.append("Email is mandatory ");
    }
    if (user.getFirebaseId() == null){
      stringBuilder.append("FirebaseId is mandatory.");
    }
    if (!stringBuilder.isEmpty()){
      throw new BadRequestException(stringBuilder.toString());
    }
  }
}
