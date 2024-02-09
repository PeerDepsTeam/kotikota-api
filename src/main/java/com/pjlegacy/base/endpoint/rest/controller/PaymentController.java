package com.pjlegacy.base.endpoint.rest.controller;

import com.pjlegacy.base.endpoint.rest.mapper.PaymentMapper;
import com.pjlegacy.base.endpoint.rest.model.PaymentRequest;
import com.pjlegacy.base.model.validator.PaymentValidator;
import com.pjlegacy.base.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class PaymentController {
  private final PaymentService service;
  private final PaymentMapper mapper;
  private final PaymentValidator validator;

  @PutMapping(value = "/transactions")
  public PaymentRequest initiatePayment(@RequestBody PaymentRequest toCreate){
    var toSave = mapper.toDomain(toCreate);
    validator.accept(toSave);
    return mapper.toRest(service.save(toSave));
  }
}
