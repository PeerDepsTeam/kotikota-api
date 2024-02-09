package com.pjlegacy.base.service;

import com.pjlegacy.base.model.Post;
import com.pjlegacy.base.repository.PostRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import static org.springframework.data.domain.Sort.Direction.DESC;

@Service
@AllArgsConstructor
public class PostService {
  private final PostRepository repository;

  public List<Post> getPosts(Integer page, Integer pageSize){
    int pageValue = page == null ? 0 : page -1;
    int pageSizeValue = pageSize == null ? 0 : pageSize -1;
    Pageable pageable = PageRequest.of(pageValue, pageSizeValue,
        Sort.by(DESC, "creationDatetime"));
    return repository.findAll(pageable).toList();
  }

  public Post crupdate(Post post){
    return repository.save(post);
  }
}
