package com.pradeep.grab.ui.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.pradeep.grab.R;
import com.pradeep.grab.model.Article;
import com.pradeep.grab.utils.OnItemClickListener;
import java.util.ArrayList;
import java.util.List;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.ViewHolder> {

  private final List<Article> mNewsList = new ArrayList<>(0);
  private OnItemClickListener<Article> mOnItemClickListener;

  NewsListAdapter(OnItemClickListener<Article> listener) {
    mOnItemClickListener = listener;
  }


  public void updateNews(List<Article> articles) {
    mNewsList.clear();
    mNewsList.addAll(articles);
    notifyDataSetChanged();
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_newst, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(final ViewHolder holder, final int position) {
    holder.mIdView.setText(mNewsList.get(position).getContent());
    holder.mContentView.setText(mNewsList.get(position).getAuthor());

    holder.itemView.setTag(mNewsList.get(position));
    holder.itemView.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        mOnItemClickListener.onItemClicked(mNewsList.get(position));
      }
    });
  }

  @Override
  public int getItemCount() {
    return mNewsList.size();
  }

  class ViewHolder extends RecyclerView.ViewHolder {

    final TextView mIdView;
    final TextView mContentView;

    ViewHolder(View view) {
      super(view);
      mIdView = view.findViewById(R.id.id_text);
      mContentView = view.findViewById(R.id.content);
    }
  }
}