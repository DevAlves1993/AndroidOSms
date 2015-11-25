package com.amanichristian.agl.osmsandroid;


import java.util.ArrayList;

/**
 * Created by AMANI CHRISTIAN CYRILLE on 23/11/2015.
 */
public class PartnerContracts
{
    private String partnerId;
    private Contract[] contracts;

    public PartnerContracts(String partnerId, Contract[] contracts)
    {
        this.partnerId = partnerId;
        this.contracts = contracts;
    }

    public Contract[] getContracts()
    {
        return contracts;
    }

    public String getPartnerId()
    {
        return partnerId;
    }

    public class Contract
    {
        private String service;
        private String contractDescription;
        private ServiceContracts[] serviceContracts;

        public Contract(String service, String contractDescription, ServiceContracts[] serviceContracts)
        {
            this.service = service;
            this.contractDescription = contractDescription;
            this.serviceContracts = serviceContracts;
        }

        public ServiceContracts[] getServiceContracts()
        {
            return serviceContracts;
        }

        public String getContractDescription()
        {
            return contractDescription;
        }

        public String getService()
        {
            return service;
        }

        public class ServiceContracts
        {
            private String country;
            private String service;
            private String contractId;
            private String availableUnits;
            private String expires;
            private String scDescription;

            public ServiceContracts(String country,
                                    String service,
                                    String contractId,
                                    String availableUnits,
                                    String expires, String scDescription)
            {
                this.country = country;
                this.service = service;
                this.contractId = contractId;
                this.availableUnits = availableUnits;
                this.expires = expires;
                this.scDescription = scDescription;
            }

            public String getCountry()
            {
                return country;
            }

            public String getService()
            {
                return service;
            }

            public String getContractId()
            {
                return contractId;
            }

            public String getAvailableUnits()
            {
                return availableUnits;
            }

            public String getExpires()
            {
                return expires;
            }

            public String getScDescription()
            {
                return scDescription;
            }
        }
    }
}
