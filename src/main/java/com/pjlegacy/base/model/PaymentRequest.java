package com.pjlegacy.base.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.JdbcTypeCode;

import static jakarta.persistence.EnumType.STRING;
import static org.hibernate.type.SqlTypes.NAMED_ENUM;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class PaymentRequest {
  @Id
  private String id;
  private Integer amount;
  @ManyToOne
  private Post post;
  @ManyToOne
  @JoinColumn(name = "sender_id")
  private User from;
  @ManyToOne
  @JoinColumn(name = "receiver_id")
  private User to;
  private String label;
  private String reference;
  @Enumerated(STRING)
  @JdbcTypeCode(NAMED_ENUM)
  private com.pjlegacy.base.endpoint.rest.model.PaymentRequest.PaymentTypeEnum paymentType;
  @Enumerated(STRING)
  @JdbcTypeCode(NAMED_ENUM)
  private com.pjlegacy.base.endpoint.rest.model.PaymentRequest.PaymentMethodEnum paymentMethod;
}
