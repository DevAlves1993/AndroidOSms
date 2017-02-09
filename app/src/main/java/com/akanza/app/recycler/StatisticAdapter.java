package com.akanza.app.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akanza.app.R;

import org.akanza.androidosms.entity.ServiceStatistic;

import java.util.Collections;
import java.util.List;

/**
 * Created by user on 09/02/2017.
 */

public class StatisticAdapter extends RecyclerView.Adapter<StatisticAdapter.StatisticViewHolder>
{

    private List<ServiceStatistic> list;

    public StatisticAdapter(ServiceStatistic[] tab)
    {
        Collections.addAll(this.list,tab);
    }

    @Override
    public StatisticViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_statistic,parent,false);
        return new StatisticViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StatisticViewHolder holder, int position)
    {
        ServiceStatistic serviceStatistic = list.get(position);
        String country = serviceStatistic.getCountry();
        holder.txtCountry.setText(country);
        ServiceStatistic.CountyStatistic[] countryStatistics = serviceStatistic.getCountryStatistics();
        if(countryStatistics.length != 0)
        {
            String applicationId = countryStatistics[0].getApplicationId();
            holder.txtApplicationId.setText(applicationId);
            int usage = countryStatistics[0].getUsage();
            holder.txtUsage.setText(usage);
        }
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }

    public class StatisticViewHolder extends RecyclerView.ViewHolder
    {
        TextView txtService;
        TextView txtCountry;
        TextView txtApplicationId;
        TextView txtUsage;
        public StatisticViewHolder(View view)
        {
            super(view);
            txtService = (TextView) view.findViewById(R.id.txtService);
            txtCountry = (TextView) view.findViewById(R.id.txtCountry);
            txtApplicationId = (TextView) view.findViewById(R.id.txtApplicationId);
            txtUsage = (TextView) view.findViewById(R.id.txtUsage);
        }
    }
}
