package com.pradeep.grab.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.pradeep.grab.database.dao.NewsDao;
import com.pradeep.grab.model.Article;
import com.pradeep.grab.utils.Constants;

@Database(entities = {Article.class}, version = Constants.DB_VERSION)
public abstract class NewsDataBase extends RoomDatabase {

  public abstract NewsDao newsDao();
}