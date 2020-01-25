package com.pradeep.grab.di;


import androidx.lifecycle.ViewModel;
import com.pradeep.grab.repository.NewsRepository;
import com.pradeep.grab.ui.viewmodel.NewsListViewModel;
import dagger.MapKey;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;
import javax.inject.Provider;

@Module
public class ViewModelModule {

  @Target(ElementType.METHOD)
  @Retention(RetentionPolicy.RUNTIME)
  @MapKey
  @interface ViewModelKey {

    Class<? extends ViewModel> value();
  }

  @Provides
  ViewModelFactory providesViewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> providerMap) {
    return new ViewModelFactory(providerMap);
  }


  @Provides
  @IntoMap
  @ViewModelKey(NewsListViewModel.class)
  ViewModel providesNewsListViewModel(NewsRepository newsRepository) {
    return new NewsListViewModel(newsRepository);
  }
}
