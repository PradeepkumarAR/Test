package com.pradeep.grab.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.pradeep.grab.model.Article;
import com.pradeep.grab.repository.NewsRepository;
import com.pradeep.grab.utils.ResultState;
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
  private MutableLiveData<ResultState> mNewsLiveData;

  @Inject
  public NewsListViewModel(NewsRepository newsRepository) {
    mNewsRepository = newsRepository;
  }

  public LiveData<ResultState> getNewsLiveData(String country) {

    if (mNewsLiveData == null) {
      mNewsLiveData = new MutableLiveData<>();
    }

    //loading state
    mNewsLiveData.postValue(new ResultState().onLoading());

    mNewsRepository.getNewsList(country).subscribe(new Observer<List<Article>>() {
      @Override
      public void onSubscribe(Disposable d) {

      }

      @Override
      public void onNext(List<Article> articles) {
        mNewsLiveData.setValue(new ResultState().onSuccess(articles));
      }

      @Override
      public void onError(Throwable e) {
        mNewsLiveData.setValue(new ResultState().onError(e));
      }

      @Override
      public void onComplete() {

      }
    });

    return mNewsLiveData;
  }
}
