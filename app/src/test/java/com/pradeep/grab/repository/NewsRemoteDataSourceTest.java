package com.pradeep.grab.repository;

import com.pradeep.grab.model.News;
import com.pradeep.grab.network.NewsService;
import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class NewsRemoteDataSourceTest {

  private NewsRemoteDataSource mSubject;
  private News mTestNewsData = new News();
  private NewsService mNewsService;

  @Before
  public void setUp() {
    mNewsService = Mockito.mock(NewsService.class);
    mSubject = new NewsRemoteDataSource(mNewsService);
  }

  @Test
  public void getNewsList_expectSameNewsData() {
    Mockito.doReturn(Observable.just(mTestNewsData)).when(mNewsService).getNews("us");
    // Trigger
    TestObserver<News> testObserver = mSubject.getNewsList("us").test();
    // Validation
    testObserver.assertValues(mTestNewsData);
    // clean up
    testObserver.dispose();
  }

  @After
  public void tearDown() {
    mSubject = null;
  }
}