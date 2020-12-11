package com.example.spotify.Models;

import java.io.Serializable;
import java.util.ArrayList;

//this object contains some details and all the albums fetched from spotify api of a given artist

public class Albums implements Serializable {

    String href;
    ArrayList<Album> items;
    int limit;
    String next;
    int offset;
    String previous;
    int total;

    public Albums(){ }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public ArrayList<Album> getItems() {
        return items;
    }

    public void setItems(ArrayList<Album> items) {
        this.items = items;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Artists{" +
                "href='" + href + '\'' +
                ", \nitems=" + items +
                ", \nlimit=" + limit +
                ", \nnext='" + next + '\'' +
                ", \noffset='" + offset + '\'' +
                ", \nprevious='" + previous + '\'' +
                ", \ntotal='" + total + '\'' +
                '}';
    }
}
