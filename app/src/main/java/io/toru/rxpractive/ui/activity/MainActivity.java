package io.toru.rxpractive.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import butterknife.BindView;
import io.toru.rxpractive.R;
import io.toru.rxpractive.base.activity.BaseActivity;
import io.toru.rxpractive.ui.adapter.MainViewAdapter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class MainActivity extends BaseActivity {
    @BindView(R.id.main_toolbar)
    public Toolbar toolbar;

    @BindView(R.id.main_recyclerView)
    public RecyclerView mainRecyclerView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        setSupportActionBar(toolbar);
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mainRecyclerView.setAdapter(new MainViewAdapter());

        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Test!!!");
                subscriber.onCompleted();
            }
        });

        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {}

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(String s) {
                Toast.makeText(MainActivity.this, "string :: " + s, Toast.LENGTH_SHORT).show();
            }
        };

//        observable.subscribe(subscriber);


        // 위와 동일한 코드가
        Observable.just("Hello world 2222")
                    .subscribe(new Action1<String>() {
                        @Override
                        public void call(String s) {
                            Toast.makeText(MainActivity.this, "string :: " + s, Toast.LENGTH_SHORT).show();
                        }
                    });
    }
}