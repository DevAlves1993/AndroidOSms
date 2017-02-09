package com.akanza.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.akanza.app.recycler.PurchaseAdapter;

import org.akanza.androidosms.core.OSms;
import org.akanza.androidosms.core.exception.HttpApiOrangeException;
import org.akanza.androidosms.entity.PurchaseOrder;
import org.akanza.androidosms.entity.apiorangeresponse.HistoricPurchase;
import org.akanza.androidosms.entity.apiorangeresponse.ResponseError;

import java.io.IOException;

public class HistoricActivity extends AppCompatActivity
{

    private RecyclerView rcView;
    private OSms oSms;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historic);
        initRcView();
        initOsms();
        refresh();
    }

    private void refresh()
    {
        HistoricPurchase historicPurchase = getHistoricPurchase();
        setAdapterOfRcView(historicPurchase);
    }


    private void setAdapterOfRcView(HistoricPurchase historicPurchase)
    {
        PurchaseOrder[] purchaseOrders = historicPurchase.getPurchaseOrders();
        if(purchaseOrders != null)
        {
            PurchaseAdapter adapter = new PurchaseAdapter(purchaseOrders);
            rcView.setAdapter(adapter);
        }
    }

    private HistoricPurchase getHistoricPurchase()
    {
        HistoricPurchase historicPurchase = null;
        try
        {
            historicPurchase =  oSms.obtainHistoricSMS();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (HttpApiOrangeException e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
            ResponseError error = e.getError();
        }
        return historicPurchase;
    }

    private void initRcView()
    {
        rcView = (RecyclerView) findViewById(R.id.rcView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rcView.setLayoutManager(layoutManager);
        rcView.setItemAnimator(new DefaultItemAnimator());
    }

    private void initOsms()
    {
        try
        {
            oSms = new OSms.BuilderOSms()
                    .id(MainActivity.ID)
                    .secretCode(MainActivity.SECRET_CODE)
                    .build();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (HttpApiOrangeException e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
            ResponseError error = e.getError();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.reload_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.reload :
                refresh();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
