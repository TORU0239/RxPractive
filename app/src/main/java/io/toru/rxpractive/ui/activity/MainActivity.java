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
        presenter.onGetWeatherItem();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onUnsubscribe();
    }
}