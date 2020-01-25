package com.pradeep.grab.di;

import android.app.Application;
import androidx.room.Room;
import com.pradeep.grab.database.NewsDataBase;
import com.pradeep.grab.database.dao.NewsDao;
import com.pradeep.grab.utils.Constants;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public class RoomModule {

  @Singleton
  @Provides
  public NewsDataBase provideNewsDatabase(Application application) {
    return Room.databaseBuilder(application, NewsDataBase.class, Constants.DB_NAME).build();
  }

  @Provides
  @Singleton
  public NewsDao provideNewsDao(NewsDataBase newsDataBase) {
    return newsDataBase.newsDao();
  }
}
