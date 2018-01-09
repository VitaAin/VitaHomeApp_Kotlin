package com.vita.home.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @FileName: com.vita.home.bean.Category.java
 * @Author: Vita
 * @Date: 2018-01-09 14:36
 * @Usage:
 */
public class Category extends Base {

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private int id;
        private String name;
        private String description;
        @SerializedName("articles_count")
        private int articlesCount;
        @SerializedName("created_at")
        private String createdAt;
        @SerializedName("updated_at")
        private String updatedAt;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getArticlesCount() {
            return articlesCount;
        }

        public void setArticlesCount(int articlesCount) {
            this.articlesCount = articlesCount;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }
    }
}
