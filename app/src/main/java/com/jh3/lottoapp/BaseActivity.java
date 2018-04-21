package com.jh3.lottoapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by KIM on 2018-04-02.
 */

abstract public class BaseActivity extends AppCompatActivity {

    public static final String LOG_TAG = "LOTTOAPP";
    protected Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        configureToolbar();
    }

    protected abstract void initLayout();
    protected abstract void setDefaultSettings();
    protected abstract int getLayoutResource();
    protected abstract int getTitleToolBar();
    protected abstract int getHomeResource();

    private void configureToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(getTitleToolBar());
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(getHomeResource() == 0 ? 0 : getHomeResource() );
        }
    }

}
