package com.jh3.lottoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String LOG_TAG = "LOTTOAPP";
    private Button btnQrScan, btnHistory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initLayout();
        setDefaultSettings();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_activity_main_qr_scan:
                startActivity(new Intent(this, ScanQrCodeActivity.class));
                break;

            case R.id.btn_activity_main_show_history:
                break;
        }
    }

    private void initLayout() {
        btnQrScan = (Button) findViewById(R.id.btn_activity_main_qr_scan);
        btnHistory = (Button) findViewById(R.id.btn_activity_main_show_history);
        btnQrScan.setOnClickListener(this);
        btnHistory.setOnClickListener(this);
    }

    private void setDefaultSettings() {

    }
}
