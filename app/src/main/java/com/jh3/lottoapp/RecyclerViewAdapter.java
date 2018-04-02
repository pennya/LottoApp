package com.jh3.lottoapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by KIM on 2018-04-02.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<LottoWinningResult> lottoWinningResults;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        protected TextView result;
        public MyViewHolder(View itemView) {
            super(itemView);
            result = itemView.findViewById(R.id.tv_activity_purchase_history_result);
        }
    }

    public RecyclerViewAdapter() {
        lottoWinningResults = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_purchase_history_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final int itemPosition = position;

        if( holder instanceof MyViewHolder) {
            MyViewHolder viewHolder = (MyViewHolder) holder;
            String printStirng = showWinningResult(lottoWinningResults.get(itemPosition));
            viewHolder.result.setText(printStirng);

        }
    }

    @Override
    public int getItemCount() {
        return lottoWinningResults.size();
    }

    private String showWinningResult(LottoWinningResult lottoWinningResult) {
        String result = "";
        result += lottoWinningResult.getId() + "\n";
        result += lottoWinningResult.getRound() + "회\t";
        result += lottoWinningResult.getResult() + "\t";
        result += lottoWinningResult.getNum1() + " ";
        result += lottoWinningResult.getNum2() + " ";
        result += lottoWinningResult.getNum3() + " ";
        result += lottoWinningResult.getNum4() + " ";
        result += lottoWinningResult.getNum5() + " ";
        result += lottoWinningResult.getNum6() + " ";
        return result;
    }

    public void setLottoWinningResults(ArrayList<LottoWinningResult> lottoWinningResults) {
        this.lottoWinningResults = lottoWinningResults;
    }
}
