package com.myapp.api;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.myapp.BuildConfig;
import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient client) {
        return new Retrofit.Builder().baseUrl(BuildConfig.BACKEND_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkhttpClient() {
        return new OkHttpClient().newBuilder()
            .addNetworkInterceptor(new StethoInterceptor())
            .build();
    }

    @Provides
    @Singleton
    TypicodeApi provideSherpaApi(Retrofit retrofit) {
        return retrofit.create(TypicodeApi.class);
    }

}
