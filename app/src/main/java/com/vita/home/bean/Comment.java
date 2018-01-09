package com.vita.home.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @FileName: com.vita.home.bean.Comment.java
 * @Author: Vita
 * @Date: 2018-01-09 14:38
 * @Usage:
 */
public class Comment {

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private int id;
        @SerializedName("user_id")
        private int userId;
        private String content;
        @SerializedName("commentable_id")
        private int commentableId;
        @SerializedName("commentable_type")
        private String commentableType;
        @SerializedName("parent_id")
        private int parentId;
        private int level;
        private int floor;
        @SerializedName("children_count")
        private int childrenCount;
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

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getCommentableId() {
            return commentableId;
        }

        public void setCommentableId(int commentableId) {
            this.commentableId = commentableId;
        }

        public String getCommentableType() {
            return commentableType;
        }

        public void setCommentableType(String commentableType) {
            this.commentableType = commentableType;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getFloor() {
            return floor;
        }

        public void setFloor(int floor) {
            this.floor = floor;
        }

        public int getChildrenCount() {
            return childrenCount;
        }

        public void setChildrenCount(int childrenCount) {
            this.childrenCount = childrenCount;
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
