package com.pradeep.grab.network;

import static org.junit.Assert.assertNotNull;

import com.pradeep.grab.model.News;
import com.pradeep.grab.utils.Constants;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class NewsServiceTest {

  private NewsService mSubject;
  private Retrofit mRetrofit;

  @Before
  public void setUp() {
    mRetrofit = new retrofit2.Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build();
    mSubject = new NewsService(mRetrofit);
  }

  @Test
  public void getNews_expectExpectResult() {

    mSubject.getNews("us").subscribe(new Observer<News>() {
      @Override
      public void onSubscribe(Disposable d) {

      }

      @Override
      public void onNext(News news) {
        assertNotNull(news);
      }

      @Override
      public void onError(Throwable e) {

      }

      @Override
      public void onComplete() {

      }
    });
  }

  @After
  public void tearDown() {
    mSubject = null;
  }
}