
package com.pradeep.grab.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import com.pradeep.grab.utils.Constants;

@Entity(tableName = Constants.DB_NEWS_TABLE, indices = @Index(value = {"url"}, unique = true))
public class Article implements Parcelable {

  public Article() {
  }

  @PrimaryKey(autoGenerate = true)
  private int id;

  @ColumnInfo(name = "author")
  private String author;

  @ColumnInfo(name = "title")
  private String title;

  @ColumnInfo(name = "description")
  private String description;

  @ColumnInfo(name = "url")
  private String url;

  @ColumnInfo(name = "urlToImage")
  private String urlToImage;

  @ColumnInfo(name = "publishedAt")
  private String publishedAt;

  @ColumnInfo(name = "content")
  private String content;


  protected Article(Parcel in) {
    id = in.readInt();
    author = in.readString();
    title = in.readString();
    description = in.readString();
    url = in.readString();
    urlToImage = in.readString();
    publishedAt = in.readString();
    content = in.readString();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(id);
    dest.writeString(author);
    dest.writeString(title);
    dest.writeString(description);
    dest.writeString(url);
    dest.writeString(urlToImage);
    dest.writeString(publishedAt);
    dest.writeString(content);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public static final Creator<Article> CREATOR = new Creator<Article>() {
    @Override
    public Article createFromParcel(Parcel in) {
      return new Article(in);
    }

    @Override
    public Article[] newArray(int size) {
      return new Article[size];
    }
  };

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getUrlToImage() {
    return urlToImage;
  }

  public void setUrlToImage(String urlToImage) {
    this.urlToImage = urlToImage;
  }

  public String getPublishedAt() {
    return publishedAt;
  }

  public void setPublishedAt(String publishedAt) {
    this.publishedAt = publishedAt;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
