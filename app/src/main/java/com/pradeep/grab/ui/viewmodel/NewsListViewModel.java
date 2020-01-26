package com.pradeep.grab.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.pradeep.grab.model.Article;
import com.pradeep.grab.repository.NewsRepository;
import com.pradeep.grab.utils.State;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import java.util.List;
import javax.inject.Inject;

/**
 * Responsible for knotting NewsListActivity with repository.
 *
 * @see NewsRepository
 */
public class NewsListViewModel extends ViewModel {

  private NewsRepository mNewsRepository;
  private MutableLiveData<State> mNewsLiveData;

  @Inject
  public NewsListViewModel(NewsRepository newsRepository) {
    mNewsRepository = newsRepository;
  }

  public LiveData<State> getNewsLiveData(String country) {

    if (mNewsLiveData == null) {
      mNewsLiveData = new MutableLiveData<>();
    }

    //loading state
    mNewsLiveData.postValue(new State().onLoading());

    mNewsRepository.getNewsList(country).subscribe(new Observer<List<Article>>() {
      @Override
      public void onSubscribe(Disposable d) {

      }

      @Override
      public void onNext(List<Article> articles) {
        mNewsLiveData.setValue(new State().onSuccess(articles));
      }

      @Override
      public void onError(Throwable e) {
        mNewsLiveData.setValue(new State().onError(e));
      }

      @Override
      public void onComplete() {

      }
    });

    return mNewsLiveData;
  }
}
