package com.company.banksystem.screen.creditoffer;

import io.jmix.ui.screen.*;
import com.company.banksystem.entity.CreditOffer;

@UiController("CreditOffer.browse")
@UiDescriptor("credit-offer-browse.xml")
@LookupComponent("creditOffersTable")
public class CreditOfferBrowse extends StandardLookup<CreditOffer> {
}