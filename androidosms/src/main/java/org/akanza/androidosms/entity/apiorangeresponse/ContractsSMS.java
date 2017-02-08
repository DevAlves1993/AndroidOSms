package org.akanza.androidosms.entity.apiorangeresponse;

import org.akanza.androidosms.entity.PartnerContracts;

/**
 * Created by Christian Amani on 15/12/2015.
 */
public class ContractsSMS
{
    private PartnerContracts partnerContracts;

    public ContractsSMS()
    {}

    public ContractsSMS(PartnerContracts partnerContracts)
    {
        this.partnerContracts = partnerContracts;
    }


    public PartnerContracts getPartnerContracts()
    {
        return partnerContracts;
    }

    public void setPartnerContracts(PartnerContracts partnerContracts)
    {
        this.partnerContracts = partnerContracts;
    }
}

