package com.harkandavez.android.apepo;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;

/**
 * Created by Handhika on 7/11/2017.
 */
public class AdapterList extends RecyclerView.Adapter<MyHolder> implements Filterable {
    private  Context context;
    private  ArrayList<Tree> trees;
    private  ArrayList<Tree> filterTrees;

    public AdapterList(ArrayList<Tree> arrayList){
        trees = arrayList;
        filterTrees = arrayList;
    }

    public AdapterList(Context context, ArrayList<Tree> trees) {
        this.context = context;
        this.trees = trees;
        filterTrees = trees;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        final Tree t =filterTrees.get(position);
        GlideClient.thumbnail(context, t.getImageURL(), holder.imgResource);
        holder.nameTree.setText(t.getTreeName());
        holder.descTree.setText(t.getTreeDesc());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick() {
                openDetailActivity(t.getTreeName(),t.getTreeDesc(),t.getImageURL(),t.getQRUrl(), t.getImage1(), t.getImage2(), t.getImage3());
            }
        });
    }

    @Override
    public int getItemCount() {
        //return filterTrees.size();
        return (filterTrees == null) ? 0 : filterTrees.size();
    }

    private void openDetailActivity(String name,String desc,String imageUrl, String qrUrl, String img1, String img2, String img3)
    {
        Intent i=new Intent(context, DetailActivity.class);

        //PACK DATA
        i.putExtra("NAME_KEY",name);
        i.putExtra("PROPELLANT_KEY",qrUrl);
        i.putExtra("DESCRIPTION_KEY",desc);
        i.putExtra("IMAGEURL_KEY",imageUrl);
        i.putExtra("IMAGEURL_1",img1);
        i.putExtra("IMAGEURL_2",img2);
        i.putExtra("IMAGEURL_3",img3);

        context.startActivity(i);
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    filterTrees = trees;
                } else {

                    ArrayList<Tree> filteredTrees = new ArrayList<>();


                    for (Tree tree : trees) {
                        if (tree.getTreeName().toLowerCase().contains(charString)) {
                            filteredTrees.add(tree);
                        }
                    }

                    filterTrees = filteredTrees;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filterTrees;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filterTrees = (ArrayList<Tree>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

}
