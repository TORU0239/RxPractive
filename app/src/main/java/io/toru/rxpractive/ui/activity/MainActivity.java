package io.toru.rxpractive.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.toru.rxpractive.R;
import io.toru.rxpractive.base.activity.BaseActivity;
import io.toru.rxpractive.network.NetworkOperator;
import io.toru.rxpractive.network.WeatherForecastApi;
import io.toru.rxpractive.pattern.model.WeatherForecast;
import io.toru.rxpractive.pattern.model.WeatherForecastItemList;
import io.toru.rxpractive.pattern.presenter.MainPresenterImp;
import io.toru.rxpractive.pattern.view.MainView;
import io.toru.rxpractive.ui.adapter.MainViewAdapter;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity implements MainView {
    private static final String TAG = MainActivity.class.getSimpleName();

    private MainPresenterImp presenter;

    @BindView(R.id.main_toolbar)
    public Toolbar toolbar;

    @BindView(R.id.main_recyclerView)
    public RecyclerView mainRecyclerView;

    private MainViewAdapter adapter;

    private List<WeatherForecast> weatherList;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        setSupportActionBar(toolbar);
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        weatherList = new ArrayList<>();
        adapter = new MainViewAdapter(weatherList);
        mainRecyclerView.setAdapter(adapter);

        presenter = new MainPresenterImp(this);
        presenter.onGetWeatherItem();
    }

    @Override
    public void onList(List<WeatherForecast> weatherResult) {
        if(weatherResult != null && weatherResult.size() > 0){
            weatherList.clear();
            weatherList.addAll(weatherResult);
            adapter.notifyDataSetChanged();
        }
        else{
            Log.w(TAG, "onList: data null or size 0");
        }
    }
}