package io.toru.rxpractive.network;

import io.toru.rxpractive.pattern.model.WeatherForecastItemList;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by toru on 2016. 8. 23..
 */
public interface WeatherForecastApi {
    @GET("forecast")
    Observable<WeatherForecastItemList> getWeatherForecastResultList(@Query("q") String city,
                                                                     @Query("appid") String appId);
}
