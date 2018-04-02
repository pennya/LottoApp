package com.jh3.lottoapp;

import android.app.Application;
import android.util.Log;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by KIM on 2018-04-02.
 */

public class LottoApplication extends Application {
    public static final String LOG_TAG = "LOTTOAPP";
    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().name("myrealm.realm").build();
        Realm.setDefaultConfiguration(config);
        Log.d(LOG_TAG, "Realm init!");
    }
}
