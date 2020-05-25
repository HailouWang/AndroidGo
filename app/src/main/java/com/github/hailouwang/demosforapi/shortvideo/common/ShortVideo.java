package com.github.hailouwang.demosforapi.shortvideo.common;

import com.alibaba.fastjson.annotation.JSONField;

public class ShortVideo {
    @JSONField(name = "video_cover")
    private String videoCover;

    @JSONField(name = "video_title")
    private String videoTitle;

    @JSONField(name = "videoUrl")
    private String videoUrl;

    @JSONField(name = "video_like_text")
    private String videoLikeText;

    @JSONField(name = "author_avatar")
    private String authorAvatar;

    @JSONField(name = "is_like")
    private String isLike;

    @JSONField(name = "author_name")
    private String authorName;

    @JSONField(name = "width")
    private int width;

    @JSONField(name = "height")
    private int height;

    public String getVideoCover() {
        return videoCover;
    }

    public ShortVideo setVideoCover(String videoCover) {
        this.videoCover = videoCover;
        return this;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public ShortVideo setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public ShortVideo setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    public String getVideoLikeText() {
        return videoLikeText;
    }

    public ShortVideo setVideoLikeText(String videoLikeText) {
        this.videoLikeText = videoLikeText;
        return this;
    }

    public String getAuthorAvatar() {
        return authorAvatar;
    }

    public ShortVideo setAuthorAvatar(String authorAvatar) {
        this.authorAvatar = authorAvatar;
        return this;
    }

    public String getIsLike() {
        return isLike;
    }

    public ShortVideo setIsLike(String isLike) {
        this.isLike = isLike;
        return this;
    }

    public String getAuthorName() {
        return authorName;
    }

    public ShortVideo setAuthorName(String authorName) {
        this.authorName = authorName;
        return this;
    }

    public int getWidth() {
        return width;
    }

    public ShortVideo setWidth(int width) {
        this.width = width;
        return this;
    }

    public int getHeight() {
        return height;
    }

    public ShortVideo setHeight(int height) {
        this.height = height;
        return this;
    }
}
