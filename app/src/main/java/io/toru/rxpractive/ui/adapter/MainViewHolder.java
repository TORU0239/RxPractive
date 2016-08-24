package io.toru.rxpractive.ui.adapter;

import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.toru.rxpractive.R;
import io.toru.rxpractive.base.adapter.BaseViewHolder;
import io.toru.rxpractive.pattern.model.WeatherForecast;

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
        WeatherForecast forecast = (WeatherForecast)model;
        textView.setText(new StringBuilder(forecast.dt_txt).append(" , ").append(forecast.main.temp_max).append(" / ").append(forecast.main.temp_min).toString());
    }
}