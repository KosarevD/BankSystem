package com.company.banksystem.screen.creditoffer;

import com.company.banksystem.app.TaskService;
import com.company.banksystem.entity.Credit;
import com.company.banksystem.entity.Payment;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.HasValue;
import io.jmix.ui.model.DataContext;
import io.jmix.ui.screen.*;
import com.company.banksystem.entity.CreditOffer;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;

@UiController("CreditOffer.edit")
@UiDescriptor("credit-offer-edit.xml")
@EditedEntityContainer("creditOfferDc")
public class CreditOfferEdit extends StandardEditor<CreditOffer> {
    @Autowired
    private TaskService taskService;
    @Autowired
    private DataContext dataContext;

    CreditOffer currentEntity = null;
    BigDecimal currentAmount = null;
    Credit currentCredit = null;
    BigDecimal percent = null;
    Integer monthQty = null;
    @Autowired
    private Button commitAndCloseBtn;

    @Subscribe
    public void onInitEntity(InitEntityEvent<CreditOffer> event) {
        currentEntity = event.getEntity();
    }

    @Subscribe("monthQuantityField")
    public void onMonthQuantityFieldValueChange(HasValue.ValueChangeEvent<Integer> event) {
        monthQty = event.getComponent().getValue();

        if(currentCredit != null && currentAmount != null && monthQty != null) {
            if (taskService.isPassLimit(currentCredit, currentAmount, percent)) {
                commitAndCloseBtn.setEnabled(true);
                ArrayList<Payment> payments = new ArrayList<>();
                payments = taskService.calculateSchedule(currentEntity, currentAmount, percent, monthQty);
                dataContext.merge(payments);
                currentEntity.setPayments(payments);
            }
            else{
                commitAndCloseBtn.setEnabled(false);
            }
        }
    }

    @Subscribe("amountField")
    public void onAmountFieldValueChange(HasValue.ValueChangeEvent<BigDecimal> event) {
        currentAmount = event.getComponent().getValue();

        if(currentCredit != null && currentAmount != null && monthQty != null){
            if (taskService.isPassLimit(currentCredit, currentAmount, percent)) {
                commitAndCloseBtn.setEnabled(true);
                ArrayList<Payment> payments = new ArrayList<>();
                payments = taskService.calculateSchedule(currentEntity, currentAmount, percent, monthQty);
                dataContext.merge(payments);
                currentEntity.setPayments(payments);
            }
            else{
                commitAndCloseBtn.setEnabled(false);
            }
        }
    }

    @Subscribe("creditField")
    public void onCreditFieldValueChange(HasValue.ValueChangeEvent<Credit> event) {
        currentCredit = event.getComponent().getValue();
        percent = currentCredit.getInterestRate();

        if(currentAmount != null && currentCredit != null && monthQty != null){
            if (taskService.isPassLimit(currentCredit, currentAmount, percent)) {
                commitAndCloseBtn.setEnabled(true);
                ArrayList<Payment> payments = new ArrayList<>();
                payments = taskService.calculateSchedule(currentEntity, currentAmount, percent, monthQty);
                dataContext.merge(payments);
                currentEntity.setPayments(payments);
            }
            else{
                commitAndCloseBtn.setEnabled(false);
            }
        }
    }
}