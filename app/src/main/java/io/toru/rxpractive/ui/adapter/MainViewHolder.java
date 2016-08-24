package io.toru.rxpractive.ui.adapter;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.toru.rxpractive.R;
import io.toru.rxpractive.base.adapter.BaseViewHolder;
import io.toru.rxpractive.network.NetworkOperator;
import io.toru.rxpractive.pattern.model.WeatherForecast;

/**
 * Created by toru on 2016. 8. 18..
 */
public class MainViewHolder extends BaseViewHolder {

    @BindView(R.id.weather_img)
    ImageView weatherImage;

    @BindView(R.id.main_textview)
    TextView textView;

    public MainViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void update(Object model) {
        WeatherForecast forecast = (WeatherForecast)model;
        String iconUrl = NetworkOperator.IMAGE_URL.concat(forecast.weather[0].icon).concat(".png");
        Glide.with(itemView.getContext())
                .load(iconUrl)
                .into(weatherImage);
        textView.setText(new StringBuilder(forecast.dt_txt).append(" , ").append(forecast.main.temp_max).append(" / ").append(forecast.main.temp_min).toString());
    }
}