package com.amanichristian.agl.osmsandroid;

/**
 * Created by AMANI CHRISTIAN CYRILLE on 23/11/2015.
 */
public class PurchaseOrders
{
    private String purchaseOrderId;
    private String mode;
    private String bundleId;
    private String bundleDescription;
    private String partnerId;
    private Inputs[] inputs;
    private OrderExecutionInformation orderExecutionInformation;

    public String getPurchaseOrderId()
    {
        return purchaseOrderId;
    }

    public String getMode()
    {
        return mode;
    }

    public String getBundleId()
    {
        return bundleId;
    }

    public String getBundleDescription()
    {
        return bundleDescription;
    }

    public String getPartnerId()
    {
        return partnerId;
    }

    public Inputs[] getInputs()
    {
        return inputs;
    }

    public OrderExecutionInformation getOrderExecutionInformation()
    {
        return orderExecutionInformation;
    }


    public class Inputs
    {
        private String type;
        private String value;

        public String getType()
        {
            return type;
        }

        public String getValue()
        {
            return value;
        }
    }

    public class OrderExecutionInformation
    {
        private String date;
        private String amount;
        private String currency;
        private String service;
        private String country;
        private String contractId;

        public String getDate()
        {
            return date;
        }

        public String getAmount()
        {
            return amount;
        }

        public String getCurrency()
        {
            return currency;
        }

        public String getService()
        {
            return service;
        }

        public String getCountry()
        {
            return country;
        }

        public String getContractId()
        {
            return contractId;
        }
    }
}
