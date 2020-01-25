package com.pradeep.grab.network;

import com.pradeep.grab.model.News;
import com.pradeep.grab.utils.Constants;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;
import retrofit2.Retrofit;

public class NewsService {

  public static final String TAG = "NewsService";
  private Retrofit mRetrofit;

  @Inject
  public NewsService(Retrofit retrofit) {
    mRetrofit = retrofit;
  }

  public Single<News> getNews() {
    NewsApi newsApi = mRetrofit.create(NewsApi.class);
    return newsApi.getNews("us", Constants.API_KEY)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
  }
}
