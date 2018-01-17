package com.example.honor.orderfoods;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.honor.orderfoods.Interface.ItemClickListener;
import com.example.honor.orderfoods.Model.Item;
import com.example.honor.orderfoods.ViewHold.ItemViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class ItemList extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference itemList;
    TextView txtFullName;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    String categoryId = "";

    FirebaseRecyclerAdapter<Item, ItemViewHolder> adapterItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        firebaseDatabase = FirebaseDatabase.getInstance();
        itemList = firebaseDatabase.getReference("item");
        recyclerView = (RecyclerView)findViewById(R.id.recycler_item);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        if(getIntent() != null)
        {
            categoryId = getIntent().getStringExtra("categoryId");
        }
        if(!categoryId.isEmpty() && categoryId !=null){
            loadListItem(categoryId);
        }
    }

    private void loadListItem(String categoryId) {
        adapterItem = new FirebaseRecyclerAdapter<Item, ItemViewHolder>(
                Item.class,
                R.layout.item_item,
                ItemViewHolder.class,
                itemList.orderByChild("CategoryId").equalTo(categoryId)) {
            @Override
            protected void populateViewHolder(ItemViewHolder viewHolder, Item model, int position) {
                viewHolder.item_name.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage()).into(viewHolder.item_image);
                final Item local = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void OnClik(View view, int position, boolean isLongClick) {
                        Toast.makeText(ItemList.this, ""+local.getName(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

        };
        recyclerView.setAdapter(adapterItem);
    }
}
