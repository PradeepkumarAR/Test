package com.pradeep.grab.repository;

import com.pradeep.grab.model.Article;
import io.reactivex.Observable;
import java.util.List;
import javax.inject.Inject;

/**
 * Updates UI with most recent in case of no error in case of error it tries to get most recent ie. Last fetched results.
 *
 * @see NewsLocalDataSource
 * @see NewsRemoteDataSource
 */
public class NewsRepository {

  private NewsLocalDataSource mLocalDataSource;
  private NewsRemoteDataSource mRemoteDataSource;

  @Inject
  public NewsRepository(NewsLocalDataSource localDataSource, NewsRemoteDataSource remoteDataSource) {
    mLocalDataSource = localDataSource;
    mRemoteDataSource = remoteDataSource;
  }

  /**
   * Fetches data from n/w and updates db in case of no errors. Incase of error supplies the data from db.
   * Every time  you call this function will update the db, thus assuring the latest results all the time.
   * Expects the internet connection at-least to for the first launch to see some data.
   *
   * @param countryCode - for which country news to be fetched.
   * @return Observable<List < Article>>
   */
  public Observable<List<Article>> getNewsList(String countryCode) {
    return Observable.concat(getNewsFromLocalSource(), getNewsFromRemoteSource(countryCode));
  }

  private Observable<List<Article>> getNewsFromRemoteSource(String countryCode) {
    return Observable.concat(mRemoteDataSource.getNewsList(countryCode)
        .map(news -> news.getArticles())
        .flatMap(articles -> mLocalDataSource.insertArticles(articles).toObservable()), mLocalDataSource.getNewsList().toObservable());
  }

  private Observable<List<Article>> getNewsFromLocalSource() {
    return mLocalDataSource.getNewsList().toObservable();
  }

}
