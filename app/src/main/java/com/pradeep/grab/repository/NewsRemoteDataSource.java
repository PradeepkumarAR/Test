package com.pradeep.grab.repository;

import com.pradeep.grab.model.News;
import com.pradeep.grab.network.NewsService;
import io.reactivex.Observable;
import javax.inject.Inject;

public class NewsRemoteDataSource {

  private NewsService mNewsService;

  @Inject
  public NewsRemoteDataSource(NewsService newsService) {
    mNewsService = newsService;
  }

  public Observable<News> getNewsList(String countryCode) {
    return mNewsService.getNews(countryCode);
  }
}
