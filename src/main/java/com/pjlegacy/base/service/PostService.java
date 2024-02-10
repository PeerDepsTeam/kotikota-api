package com.pjlegacy.base.service;

import com.pjlegacy.base.model.Post;
import com.pjlegacy.base.model.exception.NotFoundException;
import com.pjlegacy.base.repository.PostRepository;
import com.pjlegacy.base.repository.dao.PostDao;
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
  private final PostDao dao;

  public List<Post> getPosts(Integer page, Integer pageSize, String categories){
    int pageValue = page == null ? 0 : page -1;
    int pageSizeValue = pageSize == null ? 10 : pageSize -1;
    Pageable pageable = PageRequest.of(pageValue, pageSizeValue,
        Sort.by(DESC, "creationDatetime"));
    return dao.findByCriteria(categories, pageable);
  }

  public Post crupdate(Post post){
    return repository.save(post);
  }

  public Post getPostById(String postId){
    return repository.findById(postId)
        .orElseThrow(()->new NotFoundException("Post."+postId+" is not found"));
  }

  public List<Post> findPostsByUserId(String userId, Integer page, Integer pageSize){
    int pageValue = page == null ? 0 : page -1;
    int pageSizeValue = pageSize == null ? 10 : pageSize;
    Pageable pageable = PageRequest.of(pageValue, pageSizeValue, Sort.by(DESC, "creationDatetime"));
    return repository.findByAuthorId(userId, pageable).toList();
  }

  public Post getDeletePost(String pId) {
    repository.deleteById(pId);
    return Post.builder()
        .id(pId)
        .build();
  }
}
