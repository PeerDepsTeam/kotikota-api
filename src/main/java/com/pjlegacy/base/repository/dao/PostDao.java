package com.pjlegacy.base.repository.dao;

import com.pjlegacy.base.model.Category;
import com.pjlegacy.base.model.Post;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.stereotype.Repository;

import static jakarta.persistence.criteria.JoinType.LEFT;

@Repository
@AllArgsConstructor
public class PostDao {
  private EntityManager entityManager;

  public List<Post> findByCriteria(String categories, Pageable pageable) {
    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Post> query = builder.createQuery(Post.class);
    Root<Post> root = query.from(Post.class);
    Join<Post, Category> categoryJoin = root.join("categories", LEFT);

    Predicate predicate = builder.conjunction();

    if (categories != null && !categories.isEmpty()) {
      List<String> categoreisList = Arrays.stream(categories.split(",")).toList();
      predicate =
          builder.equal(
              builder.lower(categoryJoin.get("label")), categoreisList.get(0).toLowerCase());
      for (String category : categoreisList) {
        predicate =
            builder.or(
                predicate,
                builder.or(
                    builder.equal(
                        builder.lower(categoryJoin.get("label")), category.toLowerCase())));
      }
    }

    query
        .distinct(true)
        .where(predicate)
        .orderBy(QueryUtils.toOrders(pageable.getSort(), root, builder))
        .orderBy(builder.desc(root.get("creationDatetime")));

    return entityManager
        .createQuery(query)
        .setFirstResult((pageable.getPageNumber()) * pageable.getPageSize())
        .setMaxResults(pageable.getPageSize())
        .getResultList();
  }
}
