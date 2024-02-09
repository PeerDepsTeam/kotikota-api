package com.pjlegacy.base.model.validator;

import com.pjlegacy.base.model.Post;
import com.pjlegacy.base.model.exception.BadRequestException;
import java.util.function.Consumer;
import org.springframework.stereotype.Component;

@Component
public class PostValidator implements Consumer<Post> {
  @Override
  public void accept(Post post) {
    var stringBuilder = new StringBuilder();
    if (post.getId() == null) {
      stringBuilder.append("Id is mandatory ");
    }
    if (post.getAuthor() == null) {
      stringBuilder.append("Author is mandatory ");
    }
    if (post.getCategories() == null) {
      stringBuilder.append("Categories are mandatory ");
    }
    if (post.getContent() == null) {
      stringBuilder.append("Content is mandatory ");
    }
    if (post.getTitle() == null) {
      stringBuilder.append("Title is mandatory.");
    }
    if (!stringBuilder.isEmpty()) {
      throw new BadRequestException(stringBuilder.toString());
    }
  }
}
