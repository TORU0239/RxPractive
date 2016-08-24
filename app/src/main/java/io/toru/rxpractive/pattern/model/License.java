package io.toru.rxpractive.pattern.model;

import de.psdev.licensesdialog.licenses.ApacheSoftwareLicense20;
import de.psdev.licensesdialog.licenses.BSD2ClauseLicense;
import de.psdev.licensesdialog.model.Notice;
import de.psdev.licensesdialog.model.Notices;

/**
 * Created by toru on 2016. 8. 24..
 */
public class License {
    public License() {}

    public Notices builder(){
        final String rxAndroid = "RxAndroid";
        final String rxAndroidurl = "https://github.com/ReactiveX/RxAndroid";
        final String rxAndroidcopyright = "Copyright 2015 The RxAndroid authors";
        final de.psdev.licensesdialog.licenses.License rxAndroidlicense = new ApacheSoftwareLicense20();
        final Notice rxJavanotice = new Notice(rxAndroid, rxAndroidurl, rxAndroidcopyright, rxAndroidlicense);

        final String retrofit = "Retrofit";
        final String retrofiturl = " http://square.github.io/retrofit/";
        final String retrofitcopyright = "Copyright 2013 Square, Inc";
        final de.psdev.licensesdialog.licenses.License retrofitlicense = new ApacheSoftwareLicense20();
        final Notice retrofitnotice = new Notice(retrofit, retrofiturl, retrofitcopyright, retrofitlicense);

        final String okhttp = "Retrofit";
        final String okhttpurl = " http://square.github.io/okhttp/";
        final String okhttpcopyright = "Copyright 2013 Square, Inc";
        final de.psdev.licensesdialog.licenses.License okhttplicense = new ApacheSoftwareLicense20();
        final Notice okhttpnotice = new Notice(okhttp, okhttpurl, okhttpcopyright, okhttplicense);

        final String glide = "Glide";
        final String glideurl = "https://github.com/bumptech/glide";
        final String glidecopyright = "Copyright 2014 Google, Inc. ";
        final de.psdev.licensesdialog.licenses.License glidelicense = new BSD2ClauseLicense();
        final Notice glidenotice = new Notice(glide, glideurl, glidecopyright, glidelicense);

        final String btKnife = "ButterKnife";
        final String btKnifeurl = "http://jakewharton.github.io/butterknife/";
        final String btKnifecopyright = "Copyright 2013 Jake Wharton";
        final de.psdev.licensesdialog.licenses.License btKnifelicense = new ApacheSoftwareLicense20();
        final Notice btKnifenotice = new Notice(btKnife, btKnifeurl, btKnifecopyright, btKnifelicense);

        final String name = "LicensesDialog";
        final String url = "http://psdev.de";
        final String copyright = "Copyright 2013 Philip Schiffer <admin@psdev.de>";
        final de.psdev.licensesdialog.licenses.License license = new ApacheSoftwareLicense20();
        final Notice notice = new Notice(name, url, copyright, license);

        Notices notices = new Notices();

        notices.addNotice(rxJavanotice);
        notices.addNotice(retrofitnotice);
        notices.addNotice(btKnifenotice);
        notices.addNotice(okhttpnotice);
        notices.addNotice(glidenotice);
        notices.addNotice(btKnifenotice);
        notices.addNotice(notice);

        return notices;
    }
}
