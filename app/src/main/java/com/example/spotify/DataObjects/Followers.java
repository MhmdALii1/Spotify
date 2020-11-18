package com.example.spotify.DataObjects;

import java.io.Serializable;

public class Followers implements Serializable {
    String href;
    int total;

    public Followers(){}

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Followers{" +
                "href='" + href + '\'' +
                ", total=" + total +
                '}';
    }
}
