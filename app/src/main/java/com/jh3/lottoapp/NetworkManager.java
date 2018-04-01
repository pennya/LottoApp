package com.jh3.lottoapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kim on 2017. 12. 8..
 */

public class NetworkManager {
    private static final NetworkManager networkManager = new NetworkManager();

    private NetworkManager() { }

    public static NetworkManager getInstance() {
        return networkManager;
    }

    public static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("http://www.nlotto.co.kr/")
            .addConverterFactory(GsonConverterFactory.create());

    public static Retrofit retrofit = builder.build();

    public <T> T getRetrofit(Class<T> service) {
        return retrofit.create(service);
    }

}