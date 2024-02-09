package com.pjlegacy.base.endpoint.rest.mapper;

import com.pjlegacy.base.endpoint.rest.model.FundsRaised;
import com.pjlegacy.base.endpoint.rest.model.PaymentRequest;
import com.pjlegacy.base.service.PostService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static java.math.BigDecimal.valueOf;
import static java.util.UUID.randomUUID;

@Component
@AllArgsConstructor
public class PaymentMapper {
  private final UserMapper userMapper;
  private final PostService postService;

  public PaymentRequest toRest(com.pjlegacy.base.model.PaymentRequest domain) {
    return new PaymentRequest()
        .id(domain.getId())
        .postId(domain.getPost().getId())
        .amount(domain.getAmount())
        .from(userMapper.toRest(domain.getFrom()))
        .to(userMapper.toRest(domain.getTo()))
        .paymentMethod(domain.getPaymentMethod())
        .paymentType(domain.getPaymentType())
        .label(domain.getLabel())
        .reference(domain.getReference());
  }

  public com.pjlegacy.base.model.PaymentRequest toDomain(PaymentRequest rest) {
    var post = postService.getPostById(rest.getPostId());
    return com.pjlegacy.base.model.PaymentRequest.builder()
        .id(rest.getId())
        .post(post)
        .amount(rest.getAmount())
        .from(userMapper.toDomain(rest.getFrom()))
        .to(userMapper.toDomain(rest.getTo()))
        .paymentMethod(rest.getPaymentMethod())
        .paymentType(rest.getPaymentType())
        .label(rest.getLabel())
        .reference(rest.getReference())
        .build();
  }

  public FundsRaised toRestFundsRaised(String postId, List<com.pjlegacy.base.model.PaymentRequest> payments) {
    Integer amount = payments.stream().map(com.pjlegacy.base.model.PaymentRequest::getAmount)
        .reduce(Integer::sum).orElse(0);
    return new FundsRaised()
        .id(randomUUID().toString())
        .amount(valueOf(amount))
        .postId(postId)
        .transactions(payments.stream()
            .map(this::toRest).toList());
  }
}
