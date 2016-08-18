package io.toru.rxpractive.ui.adapter;

import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.toru.rxpractive.R;
import io.toru.rxpractive.base.adapter.BaseViewHolder;

/**
 * Created by toru on 2016. 8. 18..
 */
public class MainViewHolder extends BaseViewHolder {

    @BindView(R.id.main_textview)
    TextView textView;

    public MainViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void update(Object model) {
        textView.setText("shit!!");
    }
}
