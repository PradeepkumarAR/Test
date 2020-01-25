package com.pradeep.grab.repository;

import com.pradeep.grab.model.News;
import com.pradeep.grab.network.NewsService;
import io.reactivex.Single;
import javax.inject.Inject;

public class NewsRemoteDataSource {

  private NewsService mNewsService;

  @Inject
  public NewsRemoteDataSource(NewsService newsService) {
    mNewsService = newsService;
  }

  public Single<News> getNewsList() {
    return mNewsService.getNews();
  }
}
