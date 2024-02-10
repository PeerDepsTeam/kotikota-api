package com.pjlegacy.base.repository;

import com.pjlegacy.base.model.Post;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, String> {
  Page<Post> findByAuthorId(String authId, Pageable pageable);
}
