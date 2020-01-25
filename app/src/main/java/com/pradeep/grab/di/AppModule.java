package com.pradeep.grab.di;


import android.app.Application;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public class AppModule {

  Application application;

  public AppModule(Application app) {
    application = app;
  }

  @Provides
  @Singleton
  public Application providesApplication() {
    return application;
  }

}
