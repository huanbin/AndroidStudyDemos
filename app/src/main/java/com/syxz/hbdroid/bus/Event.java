package com.szy100.szyapp.bus;

/**
 * 作者：JsonYang
 * 时间:2018/11/23:19:11
 */
public class Event<T> {

    private String tag;
    private T t;

    public Event() {
    }

    public Event(String tag, T t) {
        this.tag = tag;
        this.t = t;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    @Override
    public String toString() {
        return "Event{" +
                "tag='" + tag + '\'' +
                ", t=" + t +
                '}';
    }
}
