package com.jh3.lottoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends BaseActivity implements View.OnClickListener {

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
                startActivity(new Intent(this, PurchaseHistoryActivity.class));
                break;
        }
    }

    @Override
    protected void initLayout() {
        btnQrScan = (Button) findViewById(R.id.btn_activity_main_qr_scan);
        btnHistory = (Button) findViewById(R.id.btn_activity_main_show_history);
        btnQrScan.setOnClickListener(this);
        btnHistory.setOnClickListener(this);
    }

    @Override
    protected void setDefaultSettings() {

    }
}
