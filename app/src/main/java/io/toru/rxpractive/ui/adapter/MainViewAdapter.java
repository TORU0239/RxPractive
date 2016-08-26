package io.toru.rxpractive.ui.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import io.toru.rxpractive.R;
import io.toru.rxpractive.pattern.model.WeatherForecast;
import io.toru.rxpractive.ui.activity.DetailActivity;

/**
 * Created by toru on 2016. 8. 18..
 */

public class MainViewAdapter extends RecyclerView.Adapter<MainViewHolder> {

    private List<WeatherForecast> itemList;

    public MainViewAdapter(List<WeatherForecast> itemList) {
        this.itemList = itemList;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_main, parent, false);
        return new MainViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        holder.update(itemList.get(position));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
