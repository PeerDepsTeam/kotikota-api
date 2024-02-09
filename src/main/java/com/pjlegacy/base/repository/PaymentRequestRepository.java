package com.pjlegacy.base.repository;

import com.pjlegacy.base.model.PaymentRequest;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRequestRepository extends JpaRepository<PaymentRequest, String> {
  List<PaymentRequest> findByPostId(String postId);
}
