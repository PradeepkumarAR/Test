package com.pradeep.grab.ui.viewmodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import com.pradeep.grab.repository.NewsRepository;
import com.pradeep.grab.utils.ResultState;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

public class NewsListViewModelTest {

  @Rule
  public InstantTaskExecutorRule testRule = new InstantTaskExecutorRule();
  private NewsRepository newsRepository;
  private NewsListViewModel mSubject;

  @Before
  public void setUp() {
    newsRepository = Mockito.mock(NewsRepository.class);
    mSubject = new NewsListViewModel(newsRepository);
  }

  @Test
  public void getLiveData_ExpectNotNull() {
    assertNotNull(mSubject.getLiveData());
  }

  @Test
  public void getLiveData_settingLoading_ExpectLoading() {
    mSubject.getLiveData().observeForever(resultState -> {
      assertEquals(ResultState.STATE_LOADING, resultState.getState());
    });
    mSubject.getLiveData().setValue(new ResultState().onLoading());
  }

  @Test
  public void getLiveData_settingSuccess_ExpectSuccess() {
    mSubject.getLiveData().observeForever(resultState -> {
      assertEquals(ResultState.STATE_SUCCESS, resultState.getState());
    });
    mSubject.getLiveData().setValue(new ResultState().onSuccess(new Object()));
  }

  @Test
  public void getLiveData_settingError_ExpectError() {
    mSubject.getLiveData().observeForever(resultState -> {
      assertEquals(ResultState.STATE_ERROR, resultState.getState());
    });
    mSubject.getLiveData().setValue(new ResultState().onError(new Throwable()));
  }

  @After
  public void tearDown() {
    mSubject = null;
  }
}
