package io.toru.rxpractive.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.toru.rxpractive.R;

/**
 * Created by toru on 2016. 8. 18..
 */

public class MainViewAdapter extends RecyclerView.Adapter<MainViewHolder> {

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_main, parent, false);
        return new MainViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        holder.update(position);
    }

    @Override
    public int getItemCount() {
        return 20;
    }
}
