package com.pjlegacy.base.service;

import com.pjlegacy.base.model.Category;
import com.pjlegacy.base.model.exception.NotFoundException;
import com.pjlegacy.base.repository.CategoryRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryService {
  private final CategoryRepository repository;

  public List<Category> getCategories() {
    return repository.findAll();
  }

  public Category getCategoryById(String id) {
    return repository.findById(id)
        .orElseThrow(() -> new NotFoundException("Category." + id + " is not found"));
  }
}
