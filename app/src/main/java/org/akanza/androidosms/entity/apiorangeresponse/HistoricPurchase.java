package org.akanza.androidosms.entity.apiorangeresponse;

import org.akanza.androidosms.entity.PurchaseOrder;

/**
 * Created by Christian Amani on 15/12/2015.
 */
public class HistoricPurchase
{
    private PurchaseOrder[] purchaseOrders;

    public HistoricPurchase()
    {}

    public HistoricPurchase(PurchaseOrder[] purchaseOrders)
    {
        this.purchaseOrders = purchaseOrders;
    }

    public PurchaseOrder[] getPurchaseOrders()
    {
        return purchaseOrders;
    }

    public void setPurchaseOrders(PurchaseOrder[] purchaseOrders)
    {
        this.purchaseOrders = purchaseOrders;
    }
}
