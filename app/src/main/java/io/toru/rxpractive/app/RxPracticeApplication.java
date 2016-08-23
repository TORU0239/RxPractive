package io.toru.rxpractive.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by toru on 2016. 8. 23..
 */
public class RxPracticeApplication extends Application {

    private static Context context;
    public static Context getContext(){
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
}
