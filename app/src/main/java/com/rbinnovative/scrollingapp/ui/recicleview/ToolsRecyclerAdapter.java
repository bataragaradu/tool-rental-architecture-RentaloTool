package com.rbinnovative.scrollingapp.ui.recicleview;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.rbinnovative.scrollingapp.R;
import com.rbinnovative.scrollingapp.model.Tool;

public class ToolsRecyclerAdapter extends RecyclerView.Adapter<ToolsRecyclerAdapter.ViewHolder>{

    private Tool[] listdata;

    public ToolsRecyclerAdapter(Tool[] listdata) {
        this.listdata = listdata;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int postiionSelected) {
        final Tool tool = listdata[postiionSelected];

        holder.textView.setText(listdata[postiionSelected].getId());
        //TODO: add image
//        holder.imageView.setImageResource(listdata[postiionSelected].getName());
        holder.relativeLayout.setOnClickListener(view -> Toast.makeText(view.getContext(),"click on item: "+ tool.getId(),Toast.LENGTH_LONG).show());
    }

    @Override
    public int getItemCount() {
        return listdata.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;
        public RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.imageView);
            this.textView = itemView.findViewById(R.id.textView);
            relativeLayout = itemView.findViewById(R.id.relativeLayout);
        }
    }
}