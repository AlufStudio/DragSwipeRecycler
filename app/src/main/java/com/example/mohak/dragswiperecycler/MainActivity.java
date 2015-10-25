package com.example.mohak.dragswiperecycler;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mohak.dragswiperecycler.helper.ItemTouchHelperAdapter;
import com.example.mohak.dragswiperecycler.helper.ItemTouchHelperViewHolder;
import com.example.mohak.dragswiperecycler.helper.SimpleItemTouchHelperCallback;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ItemTouchHelper mItemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recylerView);
        Adapter adapter = new Adapter(this, getdata());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);
    }

    public ArrayList<single> getdata() {

        ArrayList<single> data = new ArrayList<>();
        String[] arr2 = {"item 1", "item 2", "item 3", "item 4", "item 5", "item 6", "item 7"};
        for (int i = 0; i < arr2.length; i++) {
            single c = new single(arr2[i]);
            data.add(c);
        }
        return data;
    }


    public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> implements ItemTouchHelperAdapter {
        Context context;
        ArrayList<single> Single;


        public Adapter(Context context, ArrayList<single> Single) {
            this.context = context;
            this.Single = Single;

        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            LayoutInflater inflater = LayoutInflater.from(context);
            View v = inflater.inflate(R.layout.single, parent, false);
            MyViewHolder viewHolder = new MyViewHolder(v);
            return viewHolder;

        }


        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            single s = Single.get(position);
            holder.textView.setText(s.id);


        }

        @Override
        public int getItemCount() {
            return Single.size();
        }

        @Override
        public boolean onItemMove(int fromPosition, int toPosition) {
            Collections.swap(Single, fromPosition, toPosition);
            notifyItemMoved(fromPosition, toPosition);
            return true;
        }

        @Override
        public void onItemDismiss(int position) {

            Single.remove(position);
            notifyItemRemoved(position);
            notifyDataSetChanged();
        }



        class MyViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {

            TextView textView;

            public MyViewHolder(View itemView) {
                super(itemView);
                textView = (TextView) itemView.findViewById(R.id.textView);
            }


            @Override
            public void onItemSelected() {
                itemView.setBackgroundColor(Color.LTGRAY);
            }

            @Override
            public void onItemClear() {
                itemView.setBackgroundColor(1);
            }
        }
    }


}
