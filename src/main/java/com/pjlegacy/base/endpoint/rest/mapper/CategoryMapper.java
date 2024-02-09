package com.pjlegacy.base.endpoint.rest.mapper;

import com.pjlegacy.base.endpoint.rest.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
  public Category toRest(com.pjlegacy.base.model.Category domain){
    return new Category()
        .id(domain.getId())
        .label(domain.getLabel());
  }

  public com.pjlegacy.base.model.Category toDomain(Category rest){
    return com.pjlegacy.base.model.Category.builder()
        .id(rest.getId())
        .label(rest.getLabel())
        .build();
  }
}
