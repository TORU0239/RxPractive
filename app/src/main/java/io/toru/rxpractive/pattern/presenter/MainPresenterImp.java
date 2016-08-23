package io.toru.rxpractive.pattern.presenter;

import android.util.Log;

import io.toru.rxpractive.network.NetworkOperator;
import io.toru.rxpractive.network.WeatherForecastApi;
import io.toru.rxpractive.pattern.model.WeatherForecastItemList;
import io.toru.rxpractive.pattern.view.MainView;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by toru on 2016. 8. 23..
 */
public class MainPresenterImp implements MainPresenter{
    private static final String TAG = MainPresenterImp.class.getSimpleName();

    private MainView mainView;

    public MainPresenterImp(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void onGetWeatherItem() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okClient = new OkHttpClient.Builder()
//                .addInterceptor(new Interceptor() {
//                    @Override
//                    public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
//                        okhttp3.Response response = chain.proceed(chain.request());
//                        return response;
//                    }
//                })
                .addInterceptor(logging)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NetworkOperator.BASE_URL)
                .client(okClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // weather api
        final WeatherForecastApi forecastApi = retrofit.create(WeatherForecastApi.class);
        Observable<WeatherForecastItemList> forecastObservable = forecastApi.getWeatherForecastResultList("Seoul", NetworkOperator.APIKEY);
        forecastObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<WeatherForecastItemList>() {
                    @Override
                    public void onCompleted() {
                        Log.w(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(WeatherForecastItemList weatherForecastItemList) {
                        Log.w(TAG, "onNext: weather forecast size :: " + weatherForecastItemList.list.size());
                        Log.w(TAG, "onNext: " + weatherForecastItemList.list.get(1).dt);
                        Log.w(TAG, "onNext: " + weatherForecastItemList.list.get(1).dt_txt);
                        Log.w(TAG, "onNext: " + weatherForecastItemList.list.get(1).main);

                        Log.w(TAG, "onNext: temp :: " + weatherForecastItemList.list.get(0).main.temp);
                        Log.w(TAG, "onNext: temp max :: " + weatherForecastItemList.list.get(0).main.temp_max);
                        Log.w(TAG, "onNext: temp min :: " + weatherForecastItemList.list.get(0).main.temp_min);

                        mainView.onList(weatherForecastItemList.list);
                    }
                });
    }
}