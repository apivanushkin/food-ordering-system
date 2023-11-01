package com.food.ordering.system.order.service.domain.service;

import com.food.ordering.system.order.service.domain.dto.PaymentResponse;
import com.food.ordering.system.order.service.domain.port.input.listener.PaymentResponseListener;
import org.springframework.stereotype.Service;

@Service
public class PaymentResponseListenerImpl implements PaymentResponseListener {

    @Override
    public void paymentCompleted(PaymentResponse paymentResponse) {

    }

    @Override
    public void paymentCanceled(PaymentResponse paymentResponse) {

    }
}
