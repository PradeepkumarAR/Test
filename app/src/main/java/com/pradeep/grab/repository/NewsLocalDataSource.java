package com.pradeep.grab.repository;

import android.util.Log;
import com.pradeep.grab.database.dao.NewsDao;
import com.pradeep.grab.model.Article;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import javax.inject.Inject;

public class NewsLocalDataSource {

  public static final String TAG = "NewsLocalDataSource";

  private NewsDao mNewsDao;

  @Inject
  public NewsLocalDataSource(NewsDao newsDao) {
    mNewsDao = newsDao;
  }

  public Single<List<Article>> getNewsList() {
    return mNewsDao
        .getAll()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
  }

  public void updateCache(final List<Article> articles) {
    mNewsDao.clearTable()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new SingleObserver() {
          @Override
          public void onSubscribe(Disposable d) {

          }

          @Override
          public void onSuccess(Object o) {
            Log.d(TAG, "Clear db success");
            insertArticlesToDb(articles);
          }

          @Override
          public void onError(Throwable e) {
            Log.d(TAG, "Clear db failure");
          }
        });
  }

  public void insertArticlesToDb(List<Article> articles) {
    mNewsDao.insertAll(articles.toArray(new Article[articles.size()]))
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
  }
}