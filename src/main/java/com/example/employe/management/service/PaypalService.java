package com.example.employe.management.service;


import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class PaypalService {

    @Autowired
    private APIContext apiContext;

    public Payment createPayment(String total, String cancelUrl, String successUrl) throws PayPalRESTException {
        Amount amount = new Amount();
        amount.setCurrency("EUR");
        amount.setTotal(total); // Use the total as a string

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription("Payment for Haj");

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod("PAYPAL");

        Payment payment = new Payment();

        payment.setIntent("sale");
        payment.setPayer(payer);
        payment.setTransactions(transactions);

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("http://localhost:4200/home"); // Replace with your cancel URL
        redirectUrls.setReturnUrl("http://localhost:4200/home"); // Replace with your success URL
        payment.setRedirectUrls(redirectUrls);


        return payment.create(apiContext);
    }

    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);

        Payment payment = new Payment();
        payment.setId(paymentId);

        return payment.execute(apiContext, paymentExecution);
    }
}
