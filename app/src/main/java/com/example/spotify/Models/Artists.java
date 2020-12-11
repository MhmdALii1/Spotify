package com.example.spotify.Models;

import java.io.Serializable;
import java.util.ArrayList;

//this object contains some details and all the artists fetched from spotify api for the query from the search

public class Artists implements Serializable {

    String href;
    ArrayList<Artist> items;
    int limit;
    String next;
    int offset;
    String previous;
    int total;

    public Artists(){ }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public ArrayList<Artist> getItems() {
        return items;
    }

    public void setItems(ArrayList<Artist> items) {
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
