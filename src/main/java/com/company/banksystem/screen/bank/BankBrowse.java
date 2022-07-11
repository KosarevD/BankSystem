package com.company.banksystem.screen.bank;

import io.jmix.ui.screen.*;
import com.company.banksystem.entity.Bank;

@UiController("Bank.browse")
@UiDescriptor("bank-browse.xml")
@LookupComponent("banksTable")
public class BankBrowse extends StandardLookup<Bank> {
}