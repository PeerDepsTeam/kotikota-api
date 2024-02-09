package com.pjlegacy.base.repository;

import com.pjlegacy.base.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
  Optional<User> findByFirebaseIdAndEmail(String firebaseId, String email);
}
