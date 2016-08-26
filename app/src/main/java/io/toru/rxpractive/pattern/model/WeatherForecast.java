package io.toru.rxpractive.pattern.model;

/**
 * Created by toru on 2016. 8. 23..
 */
public class WeatherForecast {
    public String dt;
    public String dt_txt;

    public WeatherCityInfo city;
    public WeatherInfo main;
    public WeatherIconInfo[] weather;
}
