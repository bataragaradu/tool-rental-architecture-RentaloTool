package com.rbinnovative.rentalotool.ui.recicleview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.rbinnovative.rentalotool.R;
import com.rbinnovative.rentalotool.model.Order;

public class OrdersRecyclerAdapter  extends RecyclerView.Adapter<OrdersRecyclerAdapter.ViewHolder>{

    public static final String TAG = OrdersRecyclerAdapter.class.getSimpleName();

    private final Context context;
    private Order[] orders;

    public OrdersRecyclerAdapter(Order[] orders, Context context) {
        this.orders = orders;
        this.context = context;
    }

    @Override
    public OrdersRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.orders_list_item, parent, false);
        return new OrdersRecyclerAdapter.ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(OrdersRecyclerAdapter.ViewHolder holder, int positionSelected) {
        final Order order = orders[positionSelected];
        holder.orderIdtextView.setText(order.getId().toString());
        holder.statusTextView.setText(order.getStatus());
        holder.orderStartDate.setText(order.getStartDate().toString());
    }


    @Override
    public int getItemCount() {
        return orders.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView orderIdtextView;
        public TextView statusTextView;
        public TextView orderStartDate;

        public ViewHolder(View itemView) {
            super(itemView);
            this.orderIdtextView = itemView.findViewById(R.id.orderIdTextView);
            this.orderStartDate = itemView.findViewById(R.id.order_start_date);
            this.statusTextView = itemView.findViewById(R.id.statusItem);
        }
    }
}
