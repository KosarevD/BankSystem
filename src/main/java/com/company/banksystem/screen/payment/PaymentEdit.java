package com.company.banksystem.screen.payment;

import io.jmix.ui.screen.*;
import com.company.banksystem.entity.Payment;

@UiController("Payment.edit")
@UiDescriptor("payment-edit.xml")
@EditedEntityContainer("paymentDc")
public class PaymentEdit extends StandardEditor<Payment> {
}