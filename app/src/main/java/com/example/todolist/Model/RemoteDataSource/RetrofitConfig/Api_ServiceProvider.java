package com.example.todolist.Model.RemoteDataSource.RetrofitConfig;

import com.example.todolist.Model.RemoteDataSource.Api_Interface;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api_ServiceProvider {

    private static final String BASE_URL = "http://192.168.1.100/PersonToDo/";
    private static Api_Interface api_interface;

    public static Api_Interface getApi_interface() {
        if (api_interface == null){
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {
                        @Override
                        public okhttp3.Response intercept(Chain chain) throws IOException {
                            Request oldRequest = chain.request();
                            Request.Builder newRequestBuilder = oldRequest.newBuilder();
                            newRequestBuilder.addHeader("Acccept", "application/json");
//                        newRequestBuilder.addHeader("Authorization","YOUR TOKEN");
                            return chain.proceed(newRequestBuilder.build());
                        }
                    })
                    .addInterceptor(interceptor)
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();

            api_interface = retrofit.create(Api_Interface.class);
        }
        return api_interface;
    }

}
