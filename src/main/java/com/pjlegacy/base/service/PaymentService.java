package com.pjlegacy.base.service;

import com.pjlegacy.base.model.PaymentRequest;
import com.pjlegacy.base.repository.PaymentRequestRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentService {
  private final PaymentRequestRepository repository;
  public PaymentRequest save(PaymentRequest toSave){
    return repository.save(toSave);
  }

  public List<PaymentRequest> findByPostId(String postId){
    return repository.findByPostId(postId);
  }
}
