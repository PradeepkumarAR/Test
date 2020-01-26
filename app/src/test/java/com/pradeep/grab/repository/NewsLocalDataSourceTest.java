package com.pradeep.grab.repository;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import com.pradeep.grab.database.dao.NewsDao;
import com.pradeep.grab.model.Article;
import io.reactivex.Single;
import io.reactivex.observers.TestObserver;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(manifest= Config.NONE)
public class NewsLocalDataSourceTest {
  @Rule
  public InstantTaskExecutorRule testRule = new InstantTaskExecutorRule();

  private NewsLocalDataSource mSubject;
  private List<Article> mTestArticlesList = new ArrayList<>();
  private NewsDao mNewsDao;

  @Before
  public void setUp() {
    mTestArticlesList.add(new Article());
    mNewsDao = Mockito.mock(NewsDao.class);
    mSubject = new NewsLocalDataSource(mNewsDao);
  }

  @Test
  public void getNewsList_expectSameNewsData() {
    Mockito.doReturn(Single.just(mTestArticlesList)).when(mNewsDao).getAll();
    TestObserver<List<Article>> testObserverGet = mSubject.getNewsList().test();
    testObserverGet.assertValues(mTestArticlesList);
    testObserverGet.dispose();
  }

  @After
  public void tearDown() {
    mSubject = null;
  }
}