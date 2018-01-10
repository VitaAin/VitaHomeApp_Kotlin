package com.vita.home.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @FileName: com.vita.home.bean.User.java
 * @Author: Vita
 * @Date: 2018-01-09 14:33
 * @Usage:
 */
public class User {
    private int id;
    @SerializedName("github_id")
    private int githubId;
    private String name;
    private String email;
    private String avatar;
    @SerializedName("real_name")
    private String realName;
    private String sex;
    private String qq;
    private String city;
    private String introduction;
    @SerializedName("articles_count")
    private int articlesCount;
    @SerializedName("comments_count")
    private int commentsCount;
    @SerializedName("images_count")
    private int imagesCount;
    @SerializedName("words_count")
    private int wordsCount;
    @SerializedName("likes_count")
    private int likesCount;
    @SerializedName("followers_count")
    private int followersCount;
    @SerializedName("followings_count")
    private int followingsCount;
    @SerializedName("is_banned")
    private int isBanned;
    @SerializedName("confirm_code")
    private String confirmCode;
    @SerializedName("is_confirmed")
    private int isConfirmed;
    @SerializedName("last_actived_at")
    private String lastActivedAt;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;
    @SerializedName("deleted_at")
    private String deletedAt;

    @SerializedName("jwt_token")
    private JwtToken jwtToken;

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

    public JwtToken getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(JwtToken jwtToken) {
        this.jwtToken = jwtToken;
    }

    public static class JwtToken {
        @SerializedName("access_token")
        private String accessToken;
        @SerializedName("expires_in")
        private Long expiresIn;
        @SerializedName("token_type")
        private String tokenType;

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public Long getExpiresIn() {
            return expiresIn;
        }

        public void setExpiresIn(Long expiresIn) {
            this.expiresIn = expiresIn;
        }

        public String getTokenType() {
            return tokenType;
        }

        public void setTokenType(String tokenType) {
            this.tokenType = tokenType;
        }
    }
}
