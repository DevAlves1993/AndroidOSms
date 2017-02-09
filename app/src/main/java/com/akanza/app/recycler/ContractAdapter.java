package com.akanza.app.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akanza.app.R;

import org.akanza.androidosms.entity.ServiceContracts;

import java.util.Collections;
import java.util.List;

/**
 * Created by user on 08/02/2017.
 */

public class ContractAdapter extends RecyclerView.Adapter<ContractAdapter.ContractViewHolder>
{

    List<ServiceContracts> list;

    public ContractAdapter(ServiceContracts[] tab)
    {
        Collections.addAll(this.list, tab);
    }

    @Override
    public ContractViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_contract,parent,false);
        return new ContractViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContractViewHolder holder, int position)
    {
        ServiceContracts contracts = list.get(position);
        String country = contracts.getCountry();
        holder.txtCountry.setText(country);
        String service = contracts.getService();
        holder.txtService.setText(service);
        String description = contracts.getScDescription();
        holder.txtDescription.setText(description);
        String contractId = contracts.getContractId();
        holder.txtContractId.setText(contractId);
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }

    public class ContractViewHolder extends RecyclerView.ViewHolder
    {
        TextView txtCountry;
        TextView txtService;
        TextView txtDescription;
        TextView txtContractId;

        public ContractViewHolder(View view)
        {
            super(view);
            txtCountry = (TextView) view.findViewById(R.id.txtCountry);
            txtService = (TextView) view.findViewById(R.id.txtService);
            txtDescription = (TextView) view.findViewById(R.id.txtDescription);
            txtContractId = (TextView) view.findViewById(R.id.txtContractId);
        }
    }
}
