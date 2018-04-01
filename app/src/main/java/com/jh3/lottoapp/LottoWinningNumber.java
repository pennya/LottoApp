package com.jh3.lottoapp;

/**
 * Created by kim on 2018. 4. 1..
 */

public class LottoWinningNumber {
    private int bnusNo;             // 보너스 번호
    private long firstAccumamnt;     // 1등 당첨 총금액
    private long firstWinamnt;       // 1등 1인당 당첨금
    private String returnValue;     // 결과값
    private long totSellamnt;        // 누적상금
    private int drwtNo1;            // 번호1
    private int drwtNo2;            // 번호2
    private int drwtNo3;            // 번호3
    private int drwtNo4;            // 번호4
    private int drwtNo5;            // 번호5
    private int drwtNo6;            // 번호6
    private String drwNoDate;       // 당첨일
    private int drwNo;              // 회차
    private int firstPrzwnerCo;     // 1등당첨인

    public int getBnusNo() {
        return bnusNo;
    }

    public long getFirstAccumamnt() {
        return firstAccumamnt;
    }

    public long getFirstWinamnt() {
        return firstWinamnt;
    }

    public String getReturnValue() {
        return returnValue;
    }

    public long getTotSellamnt() {
        return totSellamnt;
    }

    public int getDrwtNo1() {
        return drwtNo1;
    }

    public int getDrwtNo2() {
        return drwtNo2;
    }

    public int getDrwtNo3() {
        return drwtNo3;
    }

    public int getDrwtNo4() {
        return drwtNo4;
    }

    public int getDrwtNo5() {
        return drwtNo5;
    }

    public int getDrwtNo6() {
        return drwtNo6;
    }

    public String getDrwNoDate() {
        return drwNoDate;
    }

    public int getDrwNo() {
        return drwNo;
    }

    public int getFirstPrzwnerCo() {
        return firstPrzwnerCo;
    }
}
