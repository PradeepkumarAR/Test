
package com.pradeep.grab.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

public class News implements Parcelable {

  private List<Article> articles;

  public News() {
  }

  protected News(Parcel in) {
    articles = in.createTypedArrayList(Article.CREATOR);
  }

  public static final Creator<News> CREATOR = new Creator<News>() {
    @Override
    public News createFromParcel(Parcel in) {
      return new News(in);
    }

    @Override
    public News[] newArray(int size) {
      return new News[size];
    }
  };

  public List<Article> getArticles() {
    return articles;
  }

  public void setArticles(List<Article> articles) {
    this.articles = articles;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel parcel, int i) {
    parcel.writeTypedList(articles);
  }
}
