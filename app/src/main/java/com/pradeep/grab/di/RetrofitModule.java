package com.pradeep.grab.di;


import com.pradeep.grab.utils.Constants;
import dagger.Module;
import dagger.Provides;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class RetrofitModule {

  @Provides
  public GsonConverterFactory providesGsonConverterFactory() {
    return GsonConverterFactory.create();
  }


  @Provides
  public RxJava2CallAdapterFactory providesRxJava2CallAdapterFactory() {
    return RxJava2CallAdapterFactory.create();
  }

  @Provides
  public OkHttpClient providesOkHttpClient() {
    return new OkHttpClient.Builder()
        .readTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .build();
  }


  @Provides
  public Retrofit providesRetrofit(GsonConverterFactory gsonConverterFactory, RxJava2CallAdapterFactory rxJava2CallAdapterFactory,
      OkHttpClient okHttpClient) {
    return new retrofit2.Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(gsonConverterFactory)
        .addCallAdapterFactory(rxJava2CallAdapterFactory)
        .client(okHttpClient)
        .build();
  }
}
