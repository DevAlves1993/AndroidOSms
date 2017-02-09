package com.akanza.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.akanza.app.recycler.StatisticAdapter;

import org.akanza.androidosms.core.OSms;
import org.akanza.androidosms.core.exception.HttpApiOrangeException;
import org.akanza.androidosms.entity.PartnerStatistics;
import org.akanza.androidosms.entity.ServiceStatistic;
import org.akanza.androidosms.entity.apiorangeresponse.ResponseError;
import org.akanza.androidosms.entity.apiorangeresponse.StatisticSMS;

import java.io.IOException;

public class StatisticActivity extends AppCompatActivity
{

    private RecyclerView rcView;
    private OSms oSms;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);
        initRcView();
        initOsms();
        refresh();
    }

    private void refresh()
    {
        StatisticSMS statisticSMS = getStatisticSMS();
        ServiceStatistic[] serviceStatistics = getServiceStatistics(statisticSMS);
        setAdapterOfRcView(serviceStatistics);
    }

    private void setAdapterOfRcView(ServiceStatistic[] serviceStatistics)
    {
        StatisticAdapter adapter = new StatisticAdapter(serviceStatistics);
        rcView.setAdapter(adapter);
    }

    private ServiceStatistic[] getServiceStatistics(StatisticSMS statisticSMS)
    {
        PartnerStatistics.Statistic[] statistics = statisticSMS.getPartnerStatistics()
                .getStatistics();
        if(statistics != null)
        {
            if(statistics.length != 0)
            {
                return statistics[0].getServiceStatistics();
            }
        }
        return null;
    }

    private StatisticSMS getStatisticSMS()
    {
        StatisticSMS statisticSMS = null;
        try
        {
            statisticSMS =  oSms.obtainStatisticSMS();
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
        return statisticSMS;
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
