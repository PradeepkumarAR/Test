package com.pradeep.grab.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.pradeep.grab.model.Article;
import com.pradeep.grab.model.News;
import com.pradeep.grab.repository.NewsRepository;
import com.pradeep.grab.utils.State;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import java.util.List;
import javax.inject.Inject;

public class NewsListViewModel extends ViewModel {

  private NewsRepository mNewsRepository;
  private MutableLiveData<State> mNewsLiveData;

  @Inject
  public NewsListViewModel(NewsRepository newsRepository) {
    mNewsRepository = newsRepository;
  }

  public LiveData<State> getNewsLiveData() {

    if (mNewsLiveData == null) {
      mNewsLiveData = new MutableLiveData<>();
    }

    mNewsLiveData.postValue(new State().onLoading());
    mNewsRepository.getNewsList().subscribe(new SingleObserver() {
      @Override
      public void onSubscribe(Disposable d) {
      }

      @Override
      public void onSuccess(Object result) {
        if (result instanceof News) {
          List<Article> list = ((News) result).getArticles();
          mNewsLiveData.postValue(new State().onSuccess(list));

        } else {
          mNewsLiveData.postValue(new State().onSuccess(result));
        }
      }

      @Override
      public void onError(Throwable error) {
        mNewsLiveData.postValue(new State().onError(error));
      }
    });

    return mNewsLiveData;
  }
}
