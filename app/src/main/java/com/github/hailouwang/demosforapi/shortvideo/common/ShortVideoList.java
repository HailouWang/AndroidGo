package com.github.hailouwang.demosforapi.shortvideo.common;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class ShortVideoList {
    @JSONField(name = "video_list")
    private List<ShortVideo> shortVideoList;

    public List<ShortVideo> getShortVideoList() {
        return shortVideoList;
    }

    public ShortVideoList setShortVideoList(List<ShortVideo> shortVideoList) {
        this.shortVideoList = shortVideoList;
        return this;
    }
}
