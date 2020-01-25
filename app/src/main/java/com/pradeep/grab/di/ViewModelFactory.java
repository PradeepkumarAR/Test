package com.pradeep.grab.di;


import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Provider;

/**
 * The view model injection with dagger is not quite straight forward when ever the ViewModel has dependencies
 * This Factory helps us providing the exiting instance of view model via DaggerProviders.
 *
 * @see {https://www.techyourchance.com/dependency-injection-viewmodel-with-dagger-2/}
 */
public class ViewModelFactory implements ViewModelProvider.Factory {

  private final Map<Class<? extends ViewModel>, Provider<ViewModel>> mProviderMap;

  @Inject
  public ViewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> providerMap) {
    mProviderMap = providerMap;
  }

  @SuppressWarnings("unchecked")
  @NonNull
  @Override
  public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
    return (T) mProviderMap.get(modelClass).get();
  }
}