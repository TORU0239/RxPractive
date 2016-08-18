package io.toru.rxpractive.base.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by toru on 2016. 8. 18..
 */
public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);

    }

    public abstract void update(Object model);
}
