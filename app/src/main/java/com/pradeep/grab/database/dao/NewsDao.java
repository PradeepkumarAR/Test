package com.pradeep.grab.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.pradeep.grab.model.Article;
import com.pradeep.grab.utils.Constants;
import io.reactivex.Completable;
import io.reactivex.Single;
import java.util.List;

@Dao
public interface NewsDao {

  @Query("SELECT * FROM " + Constants.DB_NEWS_TABLE)
  Single<List<Article>> getAll();

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  Completable insertAll(List<Article> articles);
}
