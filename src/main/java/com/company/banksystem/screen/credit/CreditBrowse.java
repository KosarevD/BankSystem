package com.company.banksystem.screen.credit;

import io.jmix.ui.screen.*;
import com.company.banksystem.entity.Credit;

@UiController("Credit.browse")
@UiDescriptor("credit-browse.xml")
@LookupComponent("creditsTable")
public class CreditBrowse extends StandardLookup<Credit> {
}