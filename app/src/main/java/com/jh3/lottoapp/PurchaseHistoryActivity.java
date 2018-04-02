package com.jh3.lottoapp;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class PurchaseHistoryActivity extends BaseActivity {

    private Button btnClearDatabase;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_history);

        initLayout();
        setDefaultSettings();
    }

    @Override
    protected void onResume() {
        super.onResume();
        realmFindAll();
    }

    @Override
    protected void initLayout() {
        btnClearDatabase = (Button) findViewById(R.id.btn_activity_purchase_history_delete_db);
        recyclerView = (RecyclerView) findViewById(R.id.rv_purchase_history_activity_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new RecyclerViewAdapter();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        btnClearDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Realm realm = Realm.getDefaultInstance();
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        ArrayList<LottoWinningResult> arrayLists = new ArrayList<>();
                        RealmResults<LottoWinningResult> results =
                                realm.where(LottoWinningResult.class).findAll();
                        results.deleteAllFromRealm();
                        adapter.setLottoWinningResults(arrayLists);
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

    @Override
    protected void setDefaultSettings() {

    }

    private void realmFindAll() {
        realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                ArrayList<LottoWinningResult> arrayLists = new ArrayList<>();
                RealmResults<LottoWinningResult> results =
                        realm.where(LottoWinningResult.class).findAll();
                for(LottoWinningResult result : results) {
                    arrayLists.add(result);
                }
                adapter.setLottoWinningResults(arrayLists);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
