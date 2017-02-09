package com.akanza.app.recycler;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akanza.app.R;

import org.akanza.androidosms.entity.PurchaseOrder;

import java.util.Collections;
import java.util.List;

/**
 * Created by user on 08/02/2017.
 */

public class PurchaseAdapter extends RecyclerView.Adapter<PurchaseAdapter.PurchaseViewHolder>
{

    List<PurchaseOrder> list;

    public PurchaseAdapter(PurchaseOrder[] tab)
    {
        Collections.addAll(this.list,tab);
    }

    @Override
    public PurchaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_purchase_order,parent,false);
        return new PurchaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PurchaseViewHolder holder, int position)
    {
        PurchaseOrder purchaseOrder = list.get(position);
        String purchaseOrderId = purchaseOrder.getPurchaseOrderId();
        holder.txtPurchaseOrderId.setText(purchaseOrderId);
        String mode = purchaseOrder.getMode();
        holder.txtMode.setText(mode);
        String bundleId = purchaseOrder.getBundleId();
        holder.txtBundleId.setText(bundleId);
        String bundleDescription = purchaseOrder.getBundleDescription();
        holder.txtBundleDescription.setText(bundleDescription);
        String partnerId = purchaseOrder.getPartnerId();
        holder.txtPartnerId.setText(partnerId);
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }

    public class PurchaseViewHolder extends RecyclerView.ViewHolder
    {
        TextView txtPurchaseOrderId;
        TextView txtMode;
        TextView txtBundleId;
        TextView txtBundleDescription;
        TextView txtPartnerId;

        public PurchaseViewHolder(View view)
        {
            super(view);
            txtPurchaseOrderId = (TextView) view.findViewById(R.id.txtPurchaseOrderId);
            txtMode = (TextView) view.findViewById(R.id.txtMode);
            txtBundleId = (TextView) view.findViewById(R.id.txtBundleId);
            txtBundleDescription = (TextView) view.findViewById(R.id.txtBundleDescription);
            txtPartnerId = (TextView) view.findViewById(R.id.txtPartnerId);
        }
    }
}
