package com.jh3.lottoapp;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by KIM on 2018-04-02.
 */

abstract public class BaseActivity extends AppCompatActivity {

    public static final String LOG_TAG = "LOTTOAPP";

    abstract protected void initLayout();
    abstract protected void setDefaultSettings();
}
