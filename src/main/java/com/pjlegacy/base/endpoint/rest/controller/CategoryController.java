package com.pjlegacy.base.endpoint.rest.controller;

import com.pjlegacy.base.endpoint.rest.mapper.CategoryMapper;
import com.pjlegacy.base.endpoint.rest.model.Category;
import com.pjlegacy.base.service.CategoryService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CategoryController {
  private final CategoryService service;
  private final CategoryMapper mapper;

  @GetMapping(value = "/categories")
  public List<Category> getCategories(){
    return service.getCategories().stream()
        .map(mapper::toRest)
        .collect(Collectors.toList());
  }

}
