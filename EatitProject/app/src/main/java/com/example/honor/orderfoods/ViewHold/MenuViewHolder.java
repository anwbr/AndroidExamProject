package com.example.honor.orderfoods.ViewHold;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.honor.orderfoods.Interface.ItemClickListener;
import com.example.honor.orderfoods.R;

/**
 * Created by honor on 12/26/2017.
 */

public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView textMenuName;
    public ImageView imageView;
    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public MenuViewHolder(View view){
        super(view);
        textMenuName = (TextView)itemView.findViewById(R.id.menu_name);
        imageView = (ImageView)itemView.findViewById(R.id.menu_image);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        itemClickListener.OnClik(view, getAdapterPosition(), false);
    }
}
