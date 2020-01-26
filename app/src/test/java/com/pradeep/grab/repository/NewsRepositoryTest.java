package com.pradeep.grab.repository;

import com.pradeep.grab.model.Article;
import com.pradeep.grab.model.News;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.observers.TestObserver;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class NewsRepositoryTest {

  private NewsRemoteDataSource mNewsRemoteDataSource;
  private NewsLocalDataSource mNewsLocalDataSource;

  private NewsRepository mSubject;
  private News mTestNewsData = new News();
  private List<Article> mTestArticlesList;

  @Before
  public void setUp() {
    mTestArticlesList = new ArrayList<>(0);
    mTestArticlesList.add(new Article());
    mTestArticlesList.add(new Article());
    mTestNewsData.setArticles(mTestArticlesList);

    mNewsRemoteDataSource = Mockito.mock(NewsRemoteDataSource.class);
    mNewsLocalDataSource = Mockito.mock(NewsLocalDataSource.class);
    mSubject = new NewsRepository(mNewsLocalDataSource, mNewsRemoteDataSource);
  }

  @Test
  public void getNewsList_expectSameArticles() {
    Mockito.doReturn(Observable.just(mTestNewsData)).when(mNewsRemoteDataSource).getNewsList("us");
    Mockito.doReturn(Single.just(mTestArticlesList)).when(mNewsLocalDataSource).getNewsList();
    // Trigger
    TestObserver<List<Article>> testObserver = mSubject.getNewsList("us").test();
    // Validation
    testObserver.assertValues(mTestArticlesList);
    // clean up
    testObserver.dispose();
  }

  @After
  public void tearDown() {
    mTestNewsData = null;
    mTestArticlesList = null;
    mSubject = null;
  }
}