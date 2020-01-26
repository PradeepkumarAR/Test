package com.pradeep.grab.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ArticleTest {

  Article article;

  @Before
  public void setUp() {
    article = new Article();
    article.setTitle("title");
    article.setUrlToImage("imageurl");
    article.setUrl("url");
    article.setPublishedAt("2019-12-17");
    article.setDescription("description");
    article.setContent("content");
    article.setAuthor("author");
  }

  @Test
  public void getTitle_ExpectRightValues() {
    assertEquals("title", article.getTitle());
  }

  @Test
  public void getUrlToImage_ExpectRightValues() {
    assertEquals("imageurl", article.getUrlToImage());
  }

  @Test
  public void getUrl_ExpectRightValues() {
    assertEquals("url", article.getUrl());
  }

  @Test
  public void getPublishedDateAt_ExpectRightValues() {
    assertEquals("2019-12-17", article.getPublishedAt());
  }

  @Test
  public void getDescription_ExpectRightValues() {
    assertEquals("description", article.getDescription());
  }

  @Test
  public void getContent_ExpectRightValues() {
    assertEquals("content", article.getContent());
  }

  @Test
  public void getAuthor_ExpectRightValues() {
    assertEquals("author", article.getAuthor());
  }

  @Test
  public void getTitle_ExpectNotNull() {
    assertTrue(article.getTitle() != null);
  }

  @Test
  public void getUrlToImage_ExpectNotNull() {
    assertTrue(article.getUrlToImage() != null);
  }

  @Test
  public void getUrl_ExpectNotNull() {
    assertTrue(article.getUrl() != null);
  }

  @Test
  public void getPublishedDateAt_ExpectNotNull() {
    assertTrue(article.getPublishedAt() != null);
  }

  @Test
  public void getDescription_ExpectNotNull() {
    assertTrue(article.getDescription() != null);
  }

  @Test
  public void getContent_ExpectNotNull() {
    assertTrue(article.getContent() != null);
  }

  @Test
  public void getAuthor_ExpectNotNull() {
    assertTrue(article.getAuthor() != null);
  }
}
