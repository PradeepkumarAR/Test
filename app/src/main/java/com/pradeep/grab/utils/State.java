package com.pradeep.grab.utils;

public class State<T> {

  public static final int STATE_LOADING = 0;
  public static final int STATE_SUCCESS = 1;
  public static final int STATE_ERROR = 2;

  private int state;
  private Throwable error;
  private T response;

  public State<T> onLoading() {
    this.state = STATE_LOADING;
    this.response = null;
    this.error = null;
    return this;
  }

  public State<T> onSuccess(T data) {
    this.state = STATE_SUCCESS;
    this.response = data;
    this.error = null;
    return this;
  }

  public State<T> onError(Throwable error) {
    this.state = STATE_ERROR;
    this.response = null;
    this.error = error;
    return this;
  }

  public int getState() {
    return state;
  }

  public Throwable getError() {
    return error;
  }

  public T getResponse() {
    return response;
  }
}