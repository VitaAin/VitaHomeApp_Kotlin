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

    /**
     * status : 1
     * message : OK
     * data : {"id":1,"title":"111111111","body":"位我我我我我我我我我我我我","user_id":1,"last_comment_user_id":0,"category_id":1,"view_count":5,"comments_count":0,"likes_count":0,"close_comment":false,"is_public":true,"is_top":false,"is_excellent":false,"last_comment_time":"2018-01-09 14:30:24","created_at":"2018-01-04 15:53:28","updated_at":"2018-01-09 14:30:24","user":{"id":1,"github_id":0,"name":"苍澜阁主","email":"1191859716@qq.com","avatar":"http://api.vitain.top/image/avatar.jpeg","real_name":null,"sex":null,"qq":null,"city":null,"introduction":null,"articles_count":1,"comments_count":0,"images_count":0,"words_count":0,"likes_count":0,"followers_count":0,"followings_count":0,"is_banned":"0","confirm_code":"YYP6Lj8VEkqG7rZ6RsBf5Smj7h9PiazmCiuPWcSVKCayHmQBH06YCLQ8bRBe","is_confirmed":1,"last_actived_at":null,"created_at":"2018-01-04 15:06:32","updated_at":"2018-01-04 15:53:28","deleted_at":null},"tags":[{"id":1,"name":"标签一","description":"","articles_count":1,"created_at":"2018-01-04 15:53:22","updated_at":"2018-01-04 15:53:27","pivot":{"article_id":1,"tag_id":1,"created_at":"2018-01-04 15:53:28","updated_at":"2018-01-04 15:53:28"}}],"category":{"id":1,"name":"分类一","description":"","articles_count":1,"created_at":"2018-01-04 15:53:08","updated_at":"2018-01-04 15:53:27"}}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * title : 111111111
         * body : 位我我我我我我我我我我我我
         * user_id : 1
         * last_comment_user_id : 0
         * category_id : 1
         * view_count : 5
         * comments_count : 0
         * likes_count : 0
         * close_comment : false
         * is_public : true
         * is_top : false
         * is_excellent : false
         * last_comment_time : 2018-01-09 14:30:24
         * created_at : 2018-01-04 15:53:28
         * updated_at : 2018-01-09 14:30:24
         * user : {"id":1,"github_id":0,"name":"苍澜阁主","email":"1191859716@qq.com","avatar":"http://api.vitain.top/image/avatar.jpeg","real_name":null,"sex":null,"qq":null,"city":null,"introduction":null,"articles_count":1,"comments_count":0,"images_count":0,"words_count":0,"likes_count":0,"followers_count":0,"followings_count":0,"is_banned":"0","confirm_code":"YYP6Lj8VEkqG7rZ6RsBf5Smj7h9PiazmCiuPWcSVKCayHmQBH06YCLQ8bRBe","is_confirmed":1,"last_actived_at":null,"created_at":"2018-01-04 15:06:32","updated_at":"2018-01-04 15:53:28","deleted_at":null}
         * tags : [{"id":1,"name":"标签一","description":"","articles_count":1,"created_at":"2018-01-04 15:53:22","updated_at":"2018-01-04 15:53:27","pivot":{"article_id":1,"tag_id":1,"created_at":"2018-01-04 15:53:28","updated_at":"2018-01-04 15:53:28"}}]
         * category : {"id":1,"name":"分类一","description":"","articles_count":1,"created_at":"2018-01-04 15:53:08","updated_at":"2018-01-04 15:53:27"}
         */

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
        private UserBean user;
        private CategoryBean category;
        private List<TagsBean> tags;

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

        public boolean isIsPublic() {
            return isPublic;
        }

        public void setIsPublic(boolean isPublic) {
            this.isPublic = isPublic;
        }

        public boolean isIsTop() {
            return isTop;
        }

        public void setIsTop(boolean isTop) {
            this.isTop = isTop;
        }

        public boolean isIsExcellent() {
            return isExcellent;
        }

        public void setIsExcellent(boolean isExcellent) {
            this.isExcellent = isExcellent;
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

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public CategoryBean getCategory() {
            return category;
        }

        public void setCategory(CategoryBean category) {
            this.category = category;
        }

        public List<TagsBean> getTags() {
            return tags;
        }

        public void setTags(List<TagsBean> tags) {
            this.tags = tags;
        }

        public static class UserBean {
            /**
             * id : 1
             * github_id : 0
             * name : 苍澜阁主
             * email : 1191859716@qq.com
             * avatar : http://api.vitain.top/image/avatar.jpeg
             * real_name : null
             * sex : null
             * qq : null
             * city : null
             * introduction : null
             * articles_count : 1
             * comments_count : 0
             * images_count : 0
             * words_count : 0
             * likes_count : 0
             * followers_count : 0
             * followings_count : 0
             * is_banned : 0
             * confirm_code : YYP6Lj8VEkqG7rZ6RsBf5Smj7h9PiazmCiuPWcSVKCayHmQBH06YCLQ8bRBe
             * is_confirmed : 1
             * last_actived_at : null
             * created_at : 2018-01-04 15:06:32
             * updated_at : 2018-01-04 15:53:28
             * deleted_at : null
             */

            private int id;
            @SerializedName("github_id")
            private int githubId;
            private String name;
            private String email;
            private String avatar;
            @SerializedName("real_name")
            private Object realName;
            private Object sex;
            private Object qq;
            private Object city;
            private Object introduction;
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
            private String isBanned;
            @SerializedName("confirm_code")
            private String confirmCode;
            @SerializedName("is_confirmed")
            private int isConfirmed;
            @SerializedName("last_actived_at")
            private Object lastActivedAt;
            @SerializedName("created_at")
            private String createdAt;
            @SerializedName("updated_at")
            private String updatedAt;
            @SerializedName("deleted_at")
            private Object deletedAt;

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

            public Object getRealName() {
                return realName;
            }

            public void setRealName(Object realName) {
                this.realName = realName;
            }

            public Object getSex() {
                return sex;
            }

            public void setSex(Object sex) {
                this.sex = sex;
            }

            public Object getQq() {
                return qq;
            }

            public void setQq(Object qq) {
                this.qq = qq;
            }

            public Object getCity() {
                return city;
            }

            public void setCity(Object city) {
                this.city = city;
            }

            public Object getIntroduction() {
                return introduction;
            }

            public void setIntroduction(Object introduction) {
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

            public String getIsBanned() {
                return isBanned;
            }

            public void setIsBanned(String isBanned) {
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

            public Object getLastActivedAt() {
                return lastActivedAt;
            }

            public void setLastActivedAt(Object lastActivedAt) {
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

            public Object getDeletedAt() {
                return deletedAt;
            }

            public void setDeletedAt(Object deletedAt) {
                this.deletedAt = deletedAt;
            }
        }

        public static class CategoryBean {
            /**
             * id : 1
             * name : 分类一
             * description :
             * articles_count : 1
             * created_at : 2018-01-04 15:53:08
             * updated_at : 2018-01-04 15:53:27
             */

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

        public static class TagsBean {
            /**
             * id : 1
             * name : 标签一
             * description :
             * articles_count : 1
             * created_at : 2018-01-04 15:53:22
             * updated_at : 2018-01-04 15:53:27
             * pivot : {"article_id":1,"tag_id":1,"created_at":"2018-01-04 15:53:28","updated_at":"2018-01-04 15:53:28"}
             */

            private int id;
            private String name;
            private String description;
            @SerializedName("articles_count")
            private int articlesCount;
            @SerializedName("created_at")
            private String createdAt;
            @SerializedName("updated_at")
            private String updatedAt;
            private PivotBean pivot;

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

            public PivotBean getPivot() {
                return pivot;
            }

            public void setPivot(PivotBean pivot) {
                this.pivot = pivot;
            }

            public static class PivotBean {
                /**
                 * article_id : 1
                 * tag_id : 1
                 * created_at : 2018-01-04 15:53:28
                 * updated_at : 2018-01-04 15:53:28
                 */

                @SerializedName("article_id")
                private int articleId;
                @SerializedName("tag_id")
                private int tagId;
                @SerializedName("created_at")
                private String createdAt;
                @SerializedName("updated_at")
                private String updatedAt;

                public int getArticleId() {
                    return articleId;
                }

                public void setArticleId(int articleId) {
                    this.articleId = articleId;
                }

                public int getTagId() {
                    return tagId;
                }

                public void setTagId(int tagId) {
                    this.tagId = tagId;
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
    }
}
