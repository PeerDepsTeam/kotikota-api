package com.pjlegacy.base.model.validator;

import com.pjlegacy.base.model.PaymentRequest;
import com.pjlegacy.base.model.exception.BadRequestException;
import java.util.function.Consumer;
import org.springframework.stereotype.Component;

@Component
public class PaymentValidator implements Consumer<PaymentRequest> {

  @Override
  public void accept(PaymentRequest payment) {
    var stringBuilder = new StringBuilder();
    if (payment.getId() == null) {
      stringBuilder.append("Id is mandatory ");
    }
    if (payment.getPost() == null) {
      stringBuilder.append("Post is mandatory ");
    }
    if (payment.getAmount() == null) {
      stringBuilder.append("Amount is mandatory ");
    }
    if (payment.getFrom() == null) {
      stringBuilder.append("From is mandatory ");
    }
    if (payment.getTo() == null) {
      stringBuilder.append("To is mandatory ");
    }
    if (payment.getPaymentMethod() == null) {
      stringBuilder.append("PaymentMethod is mandatory ");
    }
    if (payment.getPaymentType() == null) {
      stringBuilder.append("PaymentType is mandatory.");
    }
    if (!stringBuilder.isEmpty()) {
      throw new BadRequestException(stringBuilder.toString());
    }
  }
}
