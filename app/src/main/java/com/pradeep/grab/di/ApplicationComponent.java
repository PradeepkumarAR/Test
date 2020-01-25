package com.pradeep.grab.di;

import com.pradeep.grab.ui.list.NewsListActivity;
import dagger.Component;
import javax.inject.Singleton;

@Component(modules = {RetrofitModule.class, AppModule.class, RoomModule.class, ViewModelModule.class})
@Singleton
public interface ApplicationComponent {

  void inject(NewsListActivity newsListActivity);

  //void inject(NewsDetailActivity newsDetailActivity);
}
