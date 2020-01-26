package com.pradeep.grab.network;

import com.pradeep.grab.model.News;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/*
 * Builds the complete api for getting remote results.
 */
public interface NewsApi {

  /**
   * Builds complete api for getting news from remote api
   *
   * @param country ex: us, uk
   * @return Observable<News>
   * @href = "https://newsapi.org/docs/endpoints/top-headlines"
   */
  @GET("top-headlines")
  Observable<News> getNews(@Query("country") String country, @Query("apiKey") String apiKey);
}
