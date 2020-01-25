package com.pradeep.grab.ui.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.pradeep.grab.R;
import com.pradeep.grab.ui.list.NewsListActivity;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link NewsListActivity}
 * in two-pane mode (on tablets) or a {@link NewsDetailActivity}
 * on handsets.
 */
public class NewsDetailFragment extends Fragment {

  /**
   * The fragment argument representing the item ID that this fragment
   * represents.
   */
  public static final String NEWS_URL = "news_url";
  private String mUrlToLoad;

  /**
   * Mandatory empty constructor for the fragment manager to instantiate the
   * fragment (e.g. upon screen orientation changes).
   */
  public NewsDetailFragment() {
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments().containsKey(NEWS_URL)) {
      mUrlToLoad = getArguments().getString(NEWS_URL);
    }
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return inflater.inflate(R.layout.item_detail, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    WebView webView = view.findViewById(R.id.news_details);
    
  }
}