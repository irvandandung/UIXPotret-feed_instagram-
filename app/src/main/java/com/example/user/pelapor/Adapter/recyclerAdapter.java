package com.example.user.pelapor.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.pelapor.R;

/**
 * Created by User on 19/02/2018.
 */
public class recyclerAdapter extends RecyclerView.Adapter<recycleViewHolder>  {
    private final Context context;

    int[] fotone = {
            R.drawable.img_feed_center_1,
            R.drawable.img_feed_center_2
    };

    LayoutInflater inflater;
    public recyclerAdapter(Context context) {
        this.context=context;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public recycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=inflater.inflate(R.layout.item_feed, parent, false);

        recycleViewHolder viewHolder=new recycleViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(recycleViewHolder holder, int position) {
        holder.gambarfeed.setImageResource(fotone[position]);
    }

    @Override
    public int getItemCount() {
        return fotone.length ;
    }


}
