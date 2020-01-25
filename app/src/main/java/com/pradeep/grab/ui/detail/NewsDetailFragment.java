package com.pradeep.grab.ui.detail;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;
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
  private WebView mWebView;
  private ProgressBar mProgressIndicator;

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
    return inflater.inflate(R.layout.fragment_detail, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mWebView = view.findViewById(R.id.news_details);
    mProgressIndicator = view.findViewById(R.id.progress_indicator);

    if (mUrlToLoad != null && !TextUtils.isEmpty(mUrlToLoad)) {
      loadPage();
    } else {
      Toast.makeText(getActivity(), "News Details not fount", Toast.LENGTH_LONG).show();
    }

  }

  private void loadPage() {
    mWebView.getSettings().setJavaScriptEnabled(true);
    mWebView.setWebViewClient(new WebViewClient() {

      @Override
      public boolean shouldOverrideUrlLoading(WebView view, String url) {
        showProgressIndicator(true);
        view.loadUrl(url);
        return true;
      }

      public void onPageFinished(WebView view, String url) {
        showProgressIndicator(false);

      }
    });

    mWebView.loadUrl(mUrlToLoad);
  }

  private void showProgressIndicator(boolean isVisible) {
    mProgressIndicator.setVisibility(isVisible ? View.VISIBLE : View.GONE);
  }
}