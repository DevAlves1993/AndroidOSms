package com.amanichristian.agl.osmsandroid;

import com.amanichristian.agl.osmsandroid.Others.PurchaseOrders;

/**
 * Created by AMANI CHRISTIAN CYRILLE on 25/11/2015.
 */
public class HistoricPurchase
{
    private PurchaseOrders[] purchaseOrders;

    public HistoricPurchase(PurchaseOrders[] purchaseOrders)
    {
        this.purchaseOrders = purchaseOrders;
    }

    public PurchaseOrders[] getPurchaseOrders()
    {
        return purchaseOrders;
    }
}
