package com.example.user.pelapor.Adapter;

import com.example.user.pelapor.R;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by User on 19/02/2018.
 */

public class recycleViewHolder extends RecyclerView.ViewHolder{

    TextView Judulgede,Judulcilik, deskripsijudul;
    ImageView iconcilik, gambarfeed;


    public recycleViewHolder(View v) {
        super(v);

        iconcilik = (ImageView) itemView.findViewById(R.id.ivUserProfile);
        gambarfeed = (ImageView) itemView.findViewById(R.id.ivFeedCenter);
        Judulgede =(TextView) itemView.findViewById(R.id.profilGede);
        Judulcilik = (TextView) itemView.findViewById(R.id.deskripsi);
        deskripsijudul = (TextView) itemView.findViewById(R.id.komen);

    }
}
