package com.eagledeveloper.newkpop.networking;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by eapple on 29/08/2018.
 */

public class ApiClient {

    //http://muhammednaveed.com/mcqs/public/api/
    public static final String BASE_URL = "https://muhammednaveed.com/NewProject/public/api/";
    public static Retrofit retrofit = null;

    public static Retrofit getApiClient(){

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder().readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS);

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                // Customize the request

                Request request = original.newBuilder()
                        .header("Accept", "application/json")
                        .header("Content-Type", "form-data")
                        .method(original.method(), original.body())
                        .build();

                Response response = chain.proceed(request);

                // Customize or return the response
                return response;
            }
        });

        OkHttpClient OkHttpClient = httpClient.build();


        if(retrofit == null){
          retrofit = new Retrofit.Builder().baseUrl(BASE_URL).
                  addConverterFactory(GsonConverterFactory.create()).build();
        }

        return retrofit;
    }
}
