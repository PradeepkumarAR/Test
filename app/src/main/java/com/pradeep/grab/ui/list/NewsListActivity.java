package com.pradeep.grab.ui.list;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import com.pradeep.grab.NewsApp;
import com.pradeep.grab.R;
import com.pradeep.grab.di.ViewModelFactory;
import com.pradeep.grab.model.Article;
import com.pradeep.grab.ui.detail.NewsDetailActivity;
import com.pradeep.grab.ui.detail.NewsDetailFragment;
import com.pradeep.grab.ui.viewmodel.NewsListViewModel;
import com.pradeep.grab.utils.OnItemClickListener;
import com.pradeep.grab.utils.State;
import java.util.List;
import javax.inject.Inject;

/**
 * Master detail flow.
 */
public class NewsListActivity extends AppCompatActivity implements OnItemClickListener<Article> {

  @Inject
  ViewModelFactory mViewModelFactory;

  private NewsListAdapter mNewsListAdapter;
  private ProgressBar mProgressIndicator;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    ((NewsApp) getApplicationContext()).mAppComponent.inject(this);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_newslist);
    mProgressIndicator = findViewById(R.id.progress_indicator);
    setupToolBar();
    setupRecyclerView();

    NewsListViewModel newsListViewModel = ViewModelProviders.of(this, mViewModelFactory).get(NewsListViewModel.class);
    newsListViewModel.getNewsLiveData().observe(this, new Observer<State>() {
      @Override
      public void onChanged(State state) {
        updateUi(state);
      }
    });
  }

  @Override
  public void onItemClicked(Article article) {
    gotoDetails(article);
  }

  private void gotoDetails(Article article) {
    String url = article.getUrl();
    if (isTwoPane()) {
      Bundle arguments = new Bundle();
      arguments.putString(NewsDetailFragment.NEWS_URL, url);
      NewsDetailFragment fragment = new NewsDetailFragment();
      fragment.setArguments(arguments);
      getSupportFragmentManager().beginTransaction().replace(R.id.item_detail_container, fragment).commit();
    } else {
      Intent intent = new Intent(NewsListActivity.this, NewsDetailActivity.class);
      intent.putExtra(NewsDetailFragment.NEWS_URL, url);
      startActivity(intent);
    }

  }

  private void updateUi(State state) {
    switch (state.getState()) {
      case State.STATE_LOADING:
        showProgressIndicator(true);
        break;
      case State.STATE_ERROR:
        showProgressIndicator(false);
        Toast.makeText(NewsListActivity.this, "An Error occurred :(", Toast.LENGTH_LONG).show();
        break;
      case State.STATE_SUCCESS:
        showProgressIndicator(false);

        List<Article> articles = (List<Article>) state.getResponse();
        mNewsListAdapter.updateNews(articles);

        //In case of tablets load the detail fragment with 1st news
        if (articles.size() > 0 && isTwoPane()) {
          gotoDetails(articles.get(0));
        }
        break;
    }
  }

  private void setupToolBar() {
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    toolbar.setTitle(getTitle());
  }

  private void setupRecyclerView() {
    RecyclerView recyclerView = findViewById(R.id.item_list);
    mNewsListAdapter = new NewsListAdapter(this);
    recyclerView.setAdapter(mNewsListAdapter);
  }

  private boolean isTwoPane() {
    return findViewById(R.id.item_detail_container) != null;
  }

  private void showProgressIndicator(boolean isVisible) {
    mProgressIndicator.setVisibility(isVisible ? View.VISIBLE : View.GONE);
  }

}
