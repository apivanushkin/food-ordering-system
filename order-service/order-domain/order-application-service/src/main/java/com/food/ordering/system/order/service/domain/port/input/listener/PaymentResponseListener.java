package com.food.ordering.system.order.service.domain.port.input.listener;

import com.food.ordering.system.order.service.domain.dto.PaymentResponse;
import jakarta.validation.Valid;

public interface PaymentResponseListener {

    void paymentCompleted(@Valid PaymentResponse paymentResponse);

    void paymentCanceled(@Valid PaymentResponse paymentResponse);
}
