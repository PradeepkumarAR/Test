package com.pradeep.grab.ui.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.pradeep.grab.R;
import com.pradeep.grab.model.Article;
import com.pradeep.grab.utils.OnItemClickListener;
import com.squareup.picasso.Picasso;
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
        .inflate(R.layout.item_news, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(final ViewHolder holder, final int position) {
    Article article = mNewsList.get(position);
    holder.title.setText(article.getTitle());
    holder.author.setText(article.getAuthor());
    Picasso.get().load(article.getUrlToImage()).into(holder.bannerImage);
    holder.itemView.setOnClickListener(view -> mOnItemClickListener.onItemClicked(mNewsList.get(position)));
  }

  @Override
  public int getItemCount() {
    return mNewsList.size();
  }

  class ViewHolder extends RecyclerView.ViewHolder {

    final TextView title;
    final TextView author;
    final ImageView bannerImage;

    ViewHolder(View view) {
      super(view);
      title = view.findViewById(R.id.title);
      author = view.findViewById(R.id.author);
      bannerImage = view.findViewById(R.id.banner_img);
    }
  }
}