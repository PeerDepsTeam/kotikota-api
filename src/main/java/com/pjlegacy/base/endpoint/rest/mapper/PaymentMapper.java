package com.pjlegacy.base.endpoint.rest.mapper;

import com.pjlegacy.base.endpoint.rest.model.PaymentRequest;
import com.pjlegacy.base.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PaymentMapper {
  private final UserMapper userMapper;
  private final CategoryMapper categoryMapper;
  private final PostService postService;

  public PaymentRequest toRest(com.pjlegacy.base.model.PaymentRequest domain){
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

  public com.pjlegacy.base.model.PaymentRequest toDomain(PaymentRequest rest){
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
}
