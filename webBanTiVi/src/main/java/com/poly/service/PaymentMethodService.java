package com.poly.service;

import com.poly.entity.PaymentMethod;

import java.lang.management.OperatingSystemMXBean;
import java.util.List;
import java.util.Optional;

public interface PaymentMethodService {
    List<PaymentMethod> getAll();

    PaymentMethod add(PaymentMethod paymentMethod);

    PaymentMethod update(PaymentMethod paymentMethod, Integer id);

    Boolean delete(Integer id);

    Optional<PaymentMethod> getOne(Integer id);
}
