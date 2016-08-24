package io.toru.rxpractive.pattern.view;

import java.util.List;

import io.toru.rxpractive.pattern.model.WeatherForecast;

/**
 * Created by toru on 2016. 8. 23..
 */
public interface MainView {
    void onLoadingStart();
    void onLoadingFinish();
    void onList(List<WeatherForecast> weatherResult);
}