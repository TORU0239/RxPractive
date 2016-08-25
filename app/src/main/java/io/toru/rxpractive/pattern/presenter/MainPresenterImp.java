package io.toru.rxpractive.pattern.presenter;

import android.util.Log;

import io.toru.rxpractive.network.NetworkOperator;
import io.toru.rxpractive.network.WeatherForecastApi;
import io.toru.rxpractive.pattern.model.WeatherForecastItemList;
import io.toru.rxpractive.pattern.view.MainView;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by toru on 2016. 8. 23..
 */
public class MainPresenterImp implements MainPresenter{
    private static final String TAG = MainPresenterImp.class.getSimpleName();

    private CompositeSubscription compositeSubscription;
    private MainView mainView;

    public MainPresenterImp(MainView mainView) {
        this.mainView = mainView;
        compositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onGetWeatherItem() {
        mainView.onLoadingStart();
        // weather api
        final WeatherForecastApi forecastApi = NetworkOperator.getRetrofit().create(WeatherForecastApi.class);
        Observable<WeatherForecastItemList> forecastObservable = forecastApi.getWeatherForecastResultList("Seoul", NetworkOperator.APIKEY);
        Subscription weatherSubscription = forecastObservable.subscribeOn(Schedulers.io()) // observable 에 subscribe가 이루어지는 thread 지정
                .observeOn(AndroidSchedulers.mainThread()) // event가 전달될 때 사용되는 thread 지정함
                .subscribe(new Subscriber<WeatherForecastItemList>() {
                    @Override
                    public void onCompleted() {
                        Log.w(TAG, "onCompleted");
                        mainView.onLoadingFinish();
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
        compositeSubscription.add(weatherSubscription);
    }

    @Override
    public void onUnsubscribe() {
        compositeSubscription.unsubscribe();
    }
}