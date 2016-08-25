package io.toru.rxpractive.ui.activity;

import android.app.ProgressDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.psdev.licensesdialog.LicensesDialog;
import io.toru.rxpractive.R;
import io.toru.rxpractive.base.activity.BaseActivity;
import io.toru.rxpractive.pattern.model.License;
import io.toru.rxpractive.pattern.model.WeatherForecast;
import io.toru.rxpractive.pattern.presenter.MainPresenterImp;
import io.toru.rxpractive.pattern.view.MainView;
import io.toru.rxpractive.ui.adapter.MainViewAdapter;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;

public class MainActivity extends BaseActivity implements MainView {
    private static final String TAG = MainActivity.class.getSimpleName();

    private MainPresenterImp presenter;

    @BindView(R.id.main_toolbar)
    public Toolbar toolbar;

    @BindView(R.id.main_recyclerView)
    public RecyclerView mainRecyclerView;

    @BindView(R.id.actionbtn)
    public FloatingActionButton actionButton;

    private ProgressDialog progressBar;

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

        progressBar = new ProgressDialog(this);
        progressBar.setIndeterminate(true);
        progressBar.setTitle("Notice");
        progressBar.setMessage("Now Loading...");
        progressBar.setCancelable(false);

        presenter = new MainPresenterImp(this);
//        presenter.onGetWeatherItem();

        // test code, observable 의 merge
        Observable<Integer> observable1 = Observable.just(1);
        Observable<String> obs1 = observable1.map(new Func1<Integer, String>() {
            @Override
            public String call(Integer integer) {
                return "Left";
            }
        });

        Observable<Integer> observable2 = Observable.just(2);
        Observable<String> obs2 = observable2.map(new Func1<Integer, String>() {
            @Override
            public String call(Integer integer) {
                return "Right";
            }
        });

        Observable<String> merged = Observable.merge(obs1, obs2);
        merged.map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                Log.w(TAG, "call: s:" + s);
                return s.toUpperCase();
            }
        })
        .subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.w(TAG, "onCompleted: merged");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(String s) {
                Log.w(TAG, "onNext: merged String :: " + s);
            }
        });


        Observable<Integer> minuses = Observable.just(-2);
        Observable<Integer> pluses  = Observable.just(1);

        Observable<Integer> together = Observable.merge(minuses, pluses);
        together.scan(-3, new Func2<Integer, Integer, Integer>() {
            @Override
            // 처음 값은 누적된 값이며 두 번째 값은 이번에 받은 값이다 이 코드에서는 이번에 받은 값을 쓰지 않기 때문에 아무것도 불리는 게 없다
            public Integer call(Integer integer, Integer integer2) {
                Log.w(TAG, "call: integer :: " + integer);
                Log.w(TAG, "call: integer2 :: " + integer2);
                return integer + 1;
            }
        })
        .subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.w(TAG, "subscribe call integer :: " + integer);
            }
        });
    }

    @Override
    public void onList(List<WeatherForecast> weatherResult) {
        if(weatherResult != null && weatherResult.size() > 0){
            if(weatherList != null){
                weatherList.clear();
                weatherList.addAll(weatherResult);
                adapter.notifyDataSetChanged();
            }
        }
        else{
            Log.w(TAG, "onList: data null or size 0");
        }
    }

    @OnClick(R.id.actionbtn)
    public void goActionButton(View view){
        presenter.onGetWeatherItem();
    }

    @Override
    public void onLoadingStart() {
        progressBar.show();
    }

    @Override
    public void onLoadingFinish() {
        progressBar.dismiss();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_setting:
            new LicensesDialog.Builder(this)
                        .setNotices(new License().builder())
                        .build()
                        .showAppCompat();
                break;
        }
        return true;
    }
}