package com.pjlegacy.base.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Post implements Serializable {
  @Id
  private String id;
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User author;
  private String title;
  @Column(columnDefinition = "TEXT")
  private String content;
  @Column(columnDefinition = "TEXT")
  private String thumbnail;
  private String description;
  private BigDecimal amountRequired;
  @CreationTimestamp
  private Instant creationDatetime;
  @UpdateTimestamp
  private Instant lastUpdateDatetime;
  @Timestamp
  private Instant deadline;

  @OneToMany(cascade = ALL, fetch = LAZY)
  @JoinColumn(name = "post_id", unique = true)
  private List<Category> categories;

}