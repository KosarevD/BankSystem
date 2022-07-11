package com.company.banksystem.screen.credit;

import io.jmix.ui.screen.*;
import com.company.banksystem.entity.Credit;

@UiController("Credit.edit")
@UiDescriptor("credit-edit.xml")
@EditedEntityContainer("creditDc")
public class CreditEdit extends StandardEditor<Credit> {
}