package com.pjlegacy.base.endpoint.rest.mapper;

import com.pjlegacy.base.endpoint.rest.model.Post;
import java.time.Instant;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static java.time.Instant.now;

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

  public com.pjlegacy.base.model.Post toDomain(Post rest) {
    return com.pjlegacy.base.model.Post.builder()
        .id(rest.getId())
        .author(userMapper.toDomain(rest.getAuthor()))
        .content(rest.getContent())
        .categories(rest.getCategories().stream()
            .map(categoryMapper::toDomain).toList())
        .creationDatetime(rest.getCreationDatetime())
        .lastUpdateDatetime(now())
        .amountRequired(rest.getAmountRequired())
        .deadline(rest.getDeadline())
        .description(rest.getDescription())
        .title(rest.getTitle())
        .thumbnail(rest.getThumbnail())
        .build();
  }
}
