package com.pjlegacy.base.endpoint.rest.controller;

import com.pjlegacy.base.endpoint.rest.mapper.PostMapper;
import com.pjlegacy.base.endpoint.rest.model.Post;
import com.pjlegacy.base.service.PostService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class PostController {
  private final PostService service;
  private final PostMapper mapper;

  @GetMapping(value = "/posts")
  public List<Post> getPosts(
      @RequestParam(name = "page", required = false) Integer page,
      @RequestParam(name = "page_size", required = false) Integer pageSize){
    return service.getPosts(page, pageSize).stream()
        .map(mapper::toRest)
        .toList();
  }

}
