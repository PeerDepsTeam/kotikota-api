package com.pjlegacy.base.endpoint.rest.controller;

import com.pjlegacy.base.endpoint.rest.mapper.PostMapper;
import com.pjlegacy.base.endpoint.rest.model.Post;
import com.pjlegacy.base.model.validator.PostValidator;
import com.pjlegacy.base.service.PostService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class PostController {
  private final PostService service;
  private final PostMapper mapper;
  private final PostValidator validator;

  @GetMapping(value = "/posts")
  public List<Post> getPosts(
      @RequestParam(name = "page", required = false) Integer page,
      @RequestParam(name = "page_size", required = false) Integer pageSize,
      @RequestParam(name = "categories", required = false) String categories) {
    return service.getPosts(page, pageSize, categories).stream()
        .map(mapper::toRest)
        .toList();
  }

  @PutMapping(value = "/posts/{pid}")
  public Post crupdatePost(@PathVariable(name = "pid") String pId,
                           @RequestBody Post toCrupdate) {
    var domain = mapper.toDomain(toCrupdate);
    validator.accept(domain);
    return mapper.toRest(service.crupdate(domain));
  }

  @GetMapping(value = "/posts/{pid}")
  public Post getPostById(@PathVariable(name = "pid") String pId) {
    return mapper.toRest(service.getPostById(pId));
  }

  @DeleteMapping(value = "/posts/{pid}")
  public Post getDeletePost(@PathVariable(name = "pid") String pId) {
    return mapper.toRest(service.getDeletePost(pId));
  }

  @GetMapping(value = "/users/{id}/posts")
  public List<Post> getPostsByUserId(@PathVariable("id") String userId,
                                     @RequestParam(value = "page", required = false) Integer page,
                                     @RequestParam(value = "page_size", required = false) Integer pageSize){
    return service.findPostsByUserId(userId, page, pageSize).stream()
        .map(mapper::toRest)
        .toList();
  }

}
