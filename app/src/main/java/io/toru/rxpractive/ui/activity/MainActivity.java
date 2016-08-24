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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import de.psdev.licensesdialog.LicensesDialog;
import de.psdev.licensesdialog.licenses.ApacheSoftwareLicense20;
import de.psdev.licensesdialog.licenses.BSD2ClauseLicense;
import de.psdev.licensesdialog.licenses.License;
import de.psdev.licensesdialog.model.Notice;
import de.psdev.licensesdialog.model.Notices;
import io.toru.rxpractive.R;
import io.toru.rxpractive.base.activity.BaseActivity;
import io.toru.rxpractive.pattern.model.WeatherForecast;
import io.toru.rxpractive.pattern.presenter.MainPresenterImp;
import io.toru.rxpractive.pattern.view.MainView;
import io.toru.rxpractive.ui.adapter.MainViewAdapter;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

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

                final String name = "LicensesDialog";
                final String url = "http://psdev.de";
                final String copyright = "Copyright 2013 Philip Schiffer <admin@psdev.de>";
                final License license = new ApacheSoftwareLicense20();
                final Notice notice = new Notice(name, url, copyright, license);
                
                final String glide = "Glide";
                final String glideurl = "https://github.com/bumptech/glide";
                final String glidecopyright = "Copyright 2014 Google, Inc. ";
                final License glidelicense = new BSD2ClauseLicense();
                final Notice glidenotice = new Notice(glide, glideurl, glidecopyright, glidelicense);

                Notices notices = new Notices();
                notices.addNotice(notice);
                notices.addNotice(glidenotice);

                new LicensesDialog.Builder(this)
                        .setNotices(notices)
                        .build()
                        .showAppCompat();

                break;
        }
        return true;
    }
}