package io.toru.rxpractive.pattern.presenter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import io.toru.rxpractive.pattern.view.DetailView;

/**
 * Created by toru on 2016. 8. 26..
 */
public class DetailPresenterImp implements DetailPresenter {

    private DetailView detailView;

    public DetailPresenterImp(DetailView detailView) {
        this.detailView = detailView;
    }

    @Override
    public void onShowView(Object obj) {
        detailView.onShowView(obj);
    }

    @Override
    public void onUnsubscribe() {

    }
}
