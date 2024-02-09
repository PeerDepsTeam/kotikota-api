package com.pjlegacy.base.endpoint.rest.mapper;

import com.pjlegacy.base.endpoint.rest.model.Post;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PostMapper {
  private final UserMapper userMapper;
  private final CategoryMapper categoryMapper;

  public Post toRest(com.pjlegacy.base.model.Post domain) {
    return new Post()
        .id(domain.getId())
        .author(userMapper.toRest(domain.getAuthor()))
        .content(domain.getContent())
        .categories(domain.getCategories().stream()
            .map(categoryMapper::toRest).toList())
        .creationDatetime(domain.getCreationDatetime())
        .updatedAt(domain.getLastUpdateDatetime())
        .amountRequired(domain.getAmountRequired())
        .deadline(domain.getDeadline())
        .description(domain.getDescription())
        .title(domain.getTitle())
        .thumbnail(domain.getThumbnail());
  }

  public com.pjlegacy.base.model.Post toDomain(Post domain) {
    return com.pjlegacy.base.model.Post.builder()
        .id(domain.getId())
        .author(userMapper.toDomain(domain.getAuthor()))
        .content(domain.getContent())
        .categories(domain.getCategories().stream()
            .map(categoryMapper::toDomain).toList())
        .creationDatetime(domain.getCreationDatetime())
        .lastUpdateDatetime(domain.getUpdatedAt())
        .amountRequired(domain.getAmountRequired())
        .deadline(domain.getDeadline())
        .description(domain.getDescription())
        .title(domain.getTitle())
        .thumbnail(domain.getThumbnail())
        .build();
  }
}
