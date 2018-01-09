package com.vita.home.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @FileName: com.vita.home.bean.User.java
 * @Author: Vita
 * @Date: 2018-01-09 14:33
 * @Usage:
 */
public class User {
    int id;
    @SerializedName("github_id")
    int githubId;
    String name;
    String email;
    String avatar;
    @SerializedName("real_name")
    String realName;
    String sex;
    String qq;
    String city;
    String introduction;
    @SerializedName("articles_count")
    int articlesCount;
    @SerializedName("comments_count")
    int commentsCount;
    @SerializedName("images_count")
    int imagesCount;
    @SerializedName("words_count")
    int wordsCount;
    @SerializedName("likes_count")
    int likesCount;
    @SerializedName("followers_count")
    int followersCount;
    @SerializedName("followings_count")
    int followingsCount;
    @SerializedName("is_banned")
    int isBanned;
    @SerializedName("confirm_code")
    String confirmCode;
    @SerializedName("is_confirmed")
    int isConfirmed;
    @SerializedName("last_actived_at")
    String lastActivedAt;
    @SerializedName("created_at")
    String createdAt;
    @SerializedName("updated_at")
    String updatedAt;
    @SerializedName("deleted_at")
    String deletedAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGithubId() {
        return githubId;
    }

    public void setGithubId(int githubId) {
        this.githubId = githubId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public int getArticlesCount() {
        return articlesCount;
    }

    public void setArticlesCount(int articlesCount) {
        this.articlesCount = articlesCount;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

    public int getImagesCount() {
        return imagesCount;
    }

    public void setImagesCount(int imagesCount) {
        this.imagesCount = imagesCount;
    }

    public int getWordsCount() {
        return wordsCount;
    }

    public void setWordsCount(int wordsCount) {
        this.wordsCount = wordsCount;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(int followersCount) {
        this.followersCount = followersCount;
    }

    public int getFollowingsCount() {
        return followingsCount;
    }

    public void setFollowingsCount(int followingsCount) {
        this.followingsCount = followingsCount;
    }

    public int getIsBanned() {
        return isBanned;
    }

    public void setIsBanned(int isBanned) {
        this.isBanned = isBanned;
    }

    public String getConfirmCode() {
        return confirmCode;
    }

    public void setConfirmCode(String confirmCode) {
        this.confirmCode = confirmCode;
    }

    public int getIsConfirmed() {
        return isConfirmed;
    }

    public void setIsConfirmed(int isConfirmed) {
        this.isConfirmed = isConfirmed;
    }

    public String getLastActivedAt() {
        return lastActivedAt;
    }

    public void setLastActivedAt(String lastActivedAt) {
        this.lastActivedAt = lastActivedAt;
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

    public String getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(String deletedAt) {
        this.deletedAt = deletedAt;
    }
}
