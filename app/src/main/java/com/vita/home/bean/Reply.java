package com.vita.home.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @FileName: com.vita.home.bean.Reply.java
 * @Author: Vita
 * @Date: 2018-01-11 16:10
 * @Usage:
 */
public class Reply {

    private Commentable commentable;
    private String content;
    @SerializedName("created_at")
    private String createdAt;

    public Commentable getCommentable() {
        return commentable;
    }

    public void setCommentable(Commentable commentable) {
        this.commentable = commentable;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public static class Commentable {
        @SerializedName("comments_count")
        private int commentsCount;
        private int id;
        @SerializedName("likes_count")
        private int likesCount;
        private String title;

        public int getCommentsCount() {
            return commentsCount;
        }

        public void setCommentsCount(int commentsCount) {
            this.commentsCount = commentsCount;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getLikesCount() {
            return likesCount;
        }

        public void setLikesCount(int likesCount) {
            this.likesCount = likesCount;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
