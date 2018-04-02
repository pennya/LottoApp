package com.jh3.lottoapp;

import io.realm.RealmObject;

/**
 * Created by kim on 2018. 4. 1..
 */

public class LottoWinningResult extends RealmObject {
    private String id;
    private int round;
    private String result;
    private int num1;
    private int num2;
    private int num3;
    private int num4;
    private int num5;
    private int num6;

    public static LottoWinningResult builder() {
        return new LottoWinningResult();
    }

    public String getId() {
        return id;
    }

    public LottoWinningResult setId(String id) {
        this.id = id;
        return this;
    }

    public int getRound() {
        return round;
    }

    public LottoWinningResult setRound(int round) {
        this.round = round;
        return this;
    }

    public String getResult() {
        return result;
    }

    public LottoWinningResult setResult(String result) {
        this.result = result;
        return this;
    }

    public int getNum1() {
        return num1;
    }

    public LottoWinningResult setNum1(int num1) {
        this.num1 = num1;
        return this;
    }

    public int getNum2() {
        return num2;
    }

    public LottoWinningResult setNum2(int num2) {
        this.num2 = num2;
        return this;
    }

    public int getNum3() {
        return num3;
    }

    public LottoWinningResult setNum3(int num3) {
        this.num3 = num3;
        return this;
    }

    public int getNum4() {
        return num4;
    }

    public LottoWinningResult setNum4(int num4) {
        this.num4 = num4;
        return this;
    }

    public int getNum5() {
        return num5;
    }

    public LottoWinningResult setNum5(int num5) {
        this.num5 = num5;
        return this;
    }

    public int getNum6() {
        return num6;
    }

    public LottoWinningResult setNum6(int num6) {
        this.num6 = num6;
        return this;
    }
}
