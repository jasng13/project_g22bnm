package com.android.lurn.projectmanagement.Models.Events;

/**
 * Created by Emmett on 16/03/2016.
 */
public class PostFailureEvent {
    private Object result;

    public PostFailureEvent(Object result) {
        this.result = result;
    }

    public Object getResult() {
        return result;
    }
}
