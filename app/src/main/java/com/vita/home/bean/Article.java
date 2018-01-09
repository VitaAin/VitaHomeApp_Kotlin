package com.vita.home.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @FileName: com.vita.home.bean.Article.java
 * @Author: Vita
 * @Date: 2018-01-09 13:53
 * @Usage:
 */
public class Article extends Base {

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private int id;
        private String title;
        private String body;
        @SerializedName("user_id")
        private int userId;
        @SerializedName("last_comment_user_id")
        private int lastCommentUserId;
        @SerializedName("category_id")
        private int categoryId;
        @SerializedName("view_count")
        private int viewCount;
        @SerializedName("comments_count")
        private int commentsCount;
        @SerializedName("likes_count")
        private int likesCount;
        @SerializedName("close_comment")
        private boolean closeComment;
        @SerializedName("is_public")
        private boolean isPublic;
        @SerializedName("is_top")
        private boolean isTop;
        @SerializedName("is_excellent")
        private boolean isExcellent;
        @SerializedName("last_comment_time")
        private String lastCommentTime;
        @SerializedName("created_at")
        private String createdAt;
        @SerializedName("updated_at")
        private String updatedAt;
        private User user;
        private Category category;
        private List<Tag> tags;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getLastCommentUserId() {
            return lastCommentUserId;
        }

        public void setLastCommentUserId(int lastCommentUserId) {
            this.lastCommentUserId = lastCommentUserId;
        }

        public int getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
        }

        public int getViewCount() {
            return viewCount;
        }

        public void setViewCount(int viewCount) {
            this.viewCount = viewCount;
        }

        public int getCommentsCount() {
            return commentsCount;
        }

        public void setCommentsCount(int commentsCount) {
            this.commentsCount = commentsCount;
        }

        public int getLikesCount() {
            return likesCount;
        }

        public void setLikesCount(int likesCount) {
            this.likesCount = likesCount;
        }

        public boolean isCloseComment() {
            return closeComment;
        }

        public void setCloseComment(boolean closeComment) {
            this.closeComment = closeComment;
        }

        public boolean isPublic() {
            return isPublic;
        }

        public void setPublic(boolean aPublic) {
            isPublic = aPublic;
        }

        public boolean isTop() {
            return isTop;
        }

        public void setTop(boolean top) {
            isTop = top;
        }

        public boolean isExcellent() {
            return isExcellent;
        }

        public void setExcellent(boolean excellent) {
            isExcellent = excellent;
        }

        public String getLastCommentTime() {
            return lastCommentTime;
        }

        public void setLastCommentTime(String lastCommentTime) {
            this.lastCommentTime = lastCommentTime;
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

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public Category getCategory() {
            return category;
        }

        public void setCategory(Category category) {
            this.category = category;
        }

        public List<Tag> getTags() {
            return tags;
        }

        public void setTags(List<Tag> tags) {
            this.tags = tags;
        }
    }
}
