package com.pradeep.grab;

import android.app.Application;
import com.pradeep.grab.di.AppModule;
import com.pradeep.grab.di.ApplicationComponent;
import com.pradeep.grab.di.DaggerApplicationComponent;

public class NewsApp extends Application {

  public ApplicationComponent mAppComponent = DaggerApplicationComponent.builder().appModule(new AppModule(this)).build();

  @Override
  public void onCreate() {
    super.onCreate();
  }

}
