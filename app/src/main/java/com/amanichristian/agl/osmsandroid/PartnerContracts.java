package com.amanichristian.agl.osmsandroid;


import java.util.ArrayList;

/**
 * Created by AMANI CHRISTIAN CYRILLE on 23/11/2015.
 */
public class PartnerContracts
{
    private String partnerId;
    private Contract[] contracts;

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