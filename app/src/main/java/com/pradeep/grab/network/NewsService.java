package com.pradeep.grab.network;

import com.pradeep.grab.model.News;
import com.pradeep.grab.utils.Constants;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;
import retrofit2.Retrofit;

/**
 * Calls the remote apis
 *
 * @see Retrofit
 */
public class NewsService {

  public static final String TAG = "NewsService";
  private Retrofit mRetrofit;

  @Inject
  public NewsService(Retrofit retrofit) {
    mRetrofit = retrofit;
  }

  /**
   * Gets the news from the api based on country code supplies
   *
   * @param countryCode ex: us, uk
   * @return Observable<News>
   * @href = "https://newsapi.org/docs/endpoints/top-headlines"
   */
  public Observable<News> getNews(String countryCode) {
    return mRetrofit.create(NewsApi.class).getNews(countryCode, Constants.API_KEY)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
  }
}
