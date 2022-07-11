package com.company.banksystem.app;

import com.company.banksystem.entity.Credit;
import com.company.banksystem.entity.CreditOffer;
import com.company.banksystem.entity.Payment;
import io.jmix.core.DataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class TaskService {
    @Autowired
    private DataManager dataManager;

    public ArrayList<Payment> calculateSchedule(CreditOffer currentCredit, BigDecimal currentAmount, BigDecimal percent, int monthQty){

        BigDecimal creditAmount = currentAmount; //выплата по кредиту
        BigDecimal percentAmount = currentAmount.multiply(percent.divide(BigDecimal.valueOf(100), 2, RoundingMode.CEILING)); //выплата по проценту
        BigDecimal totalAmount = percentAmount.add(creditAmount); //выплата всего

        ArrayList<Payment> listOfPayments = new ArrayList<Payment>();

        for(int i =0; i < monthQty; i++){
            Payment payment = dataManager.create(Payment.class);
            payment.setPaymentDate(LocalDate.now().plusMonths(i+1));
            payment.setPaymentAmount(totalAmount.divide(BigDecimal.valueOf(monthQty), 2, RoundingMode.CEILING));

            payment.setPaymentPercentAmount(percentAmount.divide(BigDecimal.valueOf(monthQty), 2, RoundingMode.CEILING).multiply(BigDecimal.valueOf(monthQty-i)));
            payment.setPaymentCreditAmount(creditAmount.divide(BigDecimal.valueOf(monthQty), 2, RoundingMode.CEILING).multiply(BigDecimal.valueOf(monthQty-i)));

            payment.setCreditOffer(currentCredit);
            listOfPayments.add(payment);
        }
        return listOfPayments;
    }

    public boolean isPassLimit(Credit currentCredit,BigDecimal currentAmount,BigDecimal percent){

        BigDecimal creditAmount = currentAmount; //выплата по кредиту
        BigDecimal percentAmount = currentAmount.multiply(percent.divide(BigDecimal.valueOf(100), 2, RoundingMode.CEILING)); //выплата по проценту
        BigDecimal totalAmount = percentAmount.add(creditAmount); //выплата всего

        BigDecimal limit = currentCredit.getLimit();

        int compResult = limit.compareTo(totalAmount);
        boolean result = true;

        if(compResult == -1)
            result = false;

        return result;
    }
}