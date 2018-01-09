package com.vita.home.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @FileName: com.vita.home.bean.Articles.java
 * @Author: Vita
 * @Date: 2018-01-09 15:55
 * @Usage:
 */
public class Articles {
    @SerializedName("current_page")
    int currentPage;
    @SerializedName("first_page_url")
    String firstPageUrl;
    int from;
    @SerializedName("last_page")
    int lastPage;
    @SerializedName("last_page_url")
    String lastPageUrl;
    @SerializedName("next_page_url")
    String nextPageUrl;
    String path;
    @SerializedName("per_page")
    int perPage;
    @SerializedName("prev_page_url")
    String prevPageUrl;
    int to;
    int total;
    List<Article> data;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public String getFirstPageUrl() {
        return firstPageUrl;
    }

    public void setFirstPageUrl(String firstPageUrl) {
        this.firstPageUrl = firstPageUrl;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public String getLastPageUrl() {
        return lastPageUrl;
    }

    public void setLastPageUrl(String lastPageUrl) {
        this.lastPageUrl = lastPageUrl;
    }

    public String getNextPageUrl() {
        return nextPageUrl;
    }

    public void setNextPageUrl(String nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public String getPrevPageUrl() {
        return prevPageUrl;
    }

    public void setPrevPageUrl(String prevPageUrl) {
        this.prevPageUrl = prevPageUrl;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Article> getData() {
        return data;
    }

    public void setData(List<Article> data) {
        this.data = data;
    }
}
