package com.harkandavez.android.apepo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView nameTree;
    TextView descTree;
    ImageView imgResource;
    ItemClickListener itemClickListener;

    public MyHolder(View itemView) {
        super(itemView);

        nameTree = (TextView) itemView.findViewById(R.id.daftar_judul);
        descTree = (TextView) itemView.findViewById(R.id.daftar_deskripsi);
        imgResource = (ImageView) itemView.findViewById(R.id.daftar_icon);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClick();
    }

    public void setItemClickListener(ItemClickListener itemClickListener)
    {
        this.itemClickListener = itemClickListener;
    }
}
