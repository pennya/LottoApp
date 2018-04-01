package com.jh3.lottoapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by kim on 2018. 4. 2..
 */

public interface LottoService {
    @GET("/common.do")
    Call<LottoWinningNumber> requestLottoNumber(@Query("method") String method, @Query("drwNo") int drwNo);
}
