package com.pjlegacy.base.endpoint.rest.controller;

import com.pjlegacy.base.endpoint.rest.mapper.PaymentMapper;
import com.pjlegacy.base.endpoint.rest.model.FundsRaised;
import com.pjlegacy.base.endpoint.rest.model.PaymentRequest;
import com.pjlegacy.base.model.validator.PaymentValidator;
import com.pjlegacy.base.service.PaymentService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

  @GetMapping(value = "/posts/{pid}/fundsraised")
  public FundsRaised getFundsRaised(@PathVariable(name = "pid") String pId) {
    List<com.pjlegacy.base.model.PaymentRequest> payments = service.findByPostId(pId);
    return mapper.toRestFundsRaised(pId, payments);
  }
}
