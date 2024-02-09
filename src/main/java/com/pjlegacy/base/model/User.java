package com.pjlegacy.base.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.Instant;
import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "user_entity")
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@ToString
public class User implements Serializable {
  @Id
  private String id;
  private String firstname;
  private String lastname;

  @Column(unique = true)
  private String email;
  @Timestamp
  private Instant birthdate;
  @Column(unique = true)
  private String firebaseId;

  @Column(columnDefinition = "TEXT")
  private String photo;
  private String bio;
  @Column(unique = true)
  private String username;
  private String about;
}
