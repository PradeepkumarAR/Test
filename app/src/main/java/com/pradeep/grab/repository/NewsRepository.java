package com.pradeep.grab.repository;

import io.reactivex.Single;
import javax.inject.Inject;

public class NewsRepository {

  private NewsLocalDataSource mLocalDataSource;
  private NewsRemoteDataSource mRemoteDataSource;
  private boolean connected = true;

  @Inject
  public NewsRepository(NewsLocalDataSource localDataSource, NewsRemoteDataSource remoteDataSource) {
    mLocalDataSource = localDataSource;
    mRemoteDataSource = remoteDataSource;
    mRemoteDataSource.getNewsList();
  }

  public Single getNewsList() {
    return (connected) ? mRemoteDataSource.getNewsList() : mLocalDataSource.getNewsList();
  }

}
