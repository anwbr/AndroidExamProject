package com.example.honor.orderfoods.ViewHold;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.honor.orderfoods.Interface.ItemClickListener;
import com.example.honor.orderfoods.R;

/**
 * Created by honor on 1/17/2018.
 */

public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView item_name;
    public ImageView item_image;
    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public ItemViewHolder(View itemView) {
        super(itemView);
        item_name = (TextView)itemView.findViewById(R.id.item_name);
        item_image = (ImageView)itemView.findViewById(R.id.item_image);
    }

    @Override
    public void onClick(View view) {
        itemClickListener.OnClik(view, getAdapterPosition(), false);
    }
}
