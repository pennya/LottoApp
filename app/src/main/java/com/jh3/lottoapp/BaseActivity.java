package com.jh3.lottoapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Toolbar에 Menu를 inflate
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Toolbar에 추가된 Menu를 클릭했을 때 이벤트
        switch(item.getItemId()) {
            case R.id.action_alerm:
                Toast.makeText(this, "Alerm Clicked..", Toast.LENGTH_SHORT).show();
                return true;

            default:
                Toast.makeText(this, "menu Clicked..", Toast.LENGTH_SHORT).show();
                return super.onOptionsItemSelected(item);
        }
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
