package com.pradeep.grab.network;

import com.pradeep.grab.model.News;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {

  @GET("top-headlines")
  Single<News> getNews(@Query("country") String country, @Query("apiKey") String apiKey);
}
