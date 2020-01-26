package com.pradeep.grab.repository;

import com.pradeep.grab.database.dao.NewsDao;
import com.pradeep.grab.model.Article;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import javax.inject.Inject;

/**
 * Deals with local data read/write operations on Room db
 */
public class NewsLocalDataSource {

  private NewsDao mNewsDao;

  @Inject
  public NewsLocalDataSource(NewsDao newsDao) {
    mNewsDao = newsDao;
  }

  /**
   * Returns the list of articles present in the db
   *
   * @return Single<List < Article>>
   * @see NewsDao
   */
  public Single<List<Article>> getNewsList() {
    return mNewsDao
        .getAll()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
  }

  /**
   * Inserts the list of articles into the db
   *
   * @param articles - of type List
   * @see NewsDao
   */
  public Completable insertArticles(List<Article> articles) {
    return mNewsDao.insertAll(articles)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
  }
}