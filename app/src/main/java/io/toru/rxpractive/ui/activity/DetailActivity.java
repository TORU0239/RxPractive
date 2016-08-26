package io.toru.rxpractive.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import butterknife.BindView;
import io.toru.rxpractive.R;
import io.toru.rxpractive.base.activity.BaseActivity;
import io.toru.rxpractive.pattern.presenter.DetailPresenter;
import io.toru.rxpractive.pattern.presenter.DetailPresenterImp;
import io.toru.rxpractive.pattern.view.DetailView;

public class DetailActivity extends BaseActivity implements DetailView {
    private DetailPresenter presenter;

    @BindView(R.id.detail_toolbar)
    Toolbar toolbar;

    @BindView(R.id.detail_shared_image)
    ImageView detailImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_detail;
    }

    @Override
    public void initView() {
        setSupportActionBar(toolbar);
        presenter = new DetailPresenterImp(this);
        presenter.onShowView(getIntent().getStringExtra("image_url"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onUnsubscribe();
    }

    @Override
    public void onShowView(Object obj) {
        String url = (String)obj;
        Glide.with(this).load(url)
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(detailImage);
    }
}