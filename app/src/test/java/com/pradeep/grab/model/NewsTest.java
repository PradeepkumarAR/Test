package com.pradeep.grab.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class NewsTest {

  News news;

  @Before
  public void setUp() {
    news = new News();
  }

  @Test
  public void getArticles_ExpectSizeZero() {
    news.setArticles(new ArrayList<Article>(0));
    assertEquals(0, news.getArticles().size());
  }

  @Test
  public void getArticles_ExpectSizeNonZero() {
    List<Article> list = new ArrayList<>();
    list.add(new Article());
    news.setArticles(list);
    assertNotEquals(0, news.getArticles().size());
  }

  @Test
  public void getSource_ExpectNotNull() {
    assertTrue(news != null);
  }

}
