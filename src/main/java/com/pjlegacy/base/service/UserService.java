package com.pjlegacy.base.service;

import com.pjlegacy.base.model.User;
import com.pjlegacy.base.model.exception.NotFoundException;
import com.pjlegacy.base.repository.UserRepository;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
  private final UserRepository repository;

  public User findByFirebaseIdAndEmail(String firebaseId, String email){
    return repository.findByFirebaseIdAndEmail(firebaseId, email)
        .orElseThrow(()-> new NotFoundException("User."+email+" is not found"));
  }

  public User getUserById(String userId){
    return repository.findById(userId)
        .orElseThrow(()-> new NotFoundException("User."+userId+" is not found"));
  }

  public User save(User toSave){
    return repository.save(toSave);
  }

}
