package com.akanza.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.akanza.app.recycler.ContractAdapter;

import org.akanza.androidosms.core.OSms;
import org.akanza.androidosms.core.exception.HttpApiOrangeException;
import org.akanza.androidosms.entity.PartnerContracts;
import org.akanza.androidosms.entity.ServiceContracts;
import org.akanza.androidosms.entity.apiorangeresponse.ContractsSMS;
import org.akanza.androidosms.entity.apiorangeresponse.ResponseError;

import java.io.IOException;

public class ContractActivity extends AppCompatActivity
{

    private RecyclerView rcView;
    private OSms oSms;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contract);
        initRcView();
        initOsms();
        refresh();
    }

    private void refresh()
    {
        ContractsSMS contractsSMS = getContractsSMS();
        setAdapterOfRcView(contractsSMS);
    }

    private void setAdapterOfRcView(ContractsSMS contractsSMS)
    {
        ServiceContracts[] serviceContracts = getServiceContracts(contractsSMS);
        if(serviceContracts != null)
        {
            ContractAdapter adapter = new ContractAdapter(serviceContracts);
            rcView.setAdapter(adapter);
        }
    }

    private void initRcView()
    {
        rcView = (RecyclerView) findViewById(R.id.rcView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rcView.setLayoutManager(layoutManager);
        rcView.setItemAnimator(new DefaultItemAnimator());
    }

    private ServiceContracts[] getServiceContracts(ContractsSMS contractsSMS)
    {
        PartnerContracts.Contract[] contracts = contractsSMS.getPartnerContracts()
                .getContracts();
        if(contracts.length != 0)
        {
            return contracts[0].getServiceContracts();
        }
        return null;
    }

    private ContractsSMS getContractsSMS()
    {
        ContractsSMS contractsSMS = null;
        try
        {
            contractsSMS =  oSms.obtainsContractsSMS();
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
        return contractsSMS;
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
