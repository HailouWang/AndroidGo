package com.github.hailouwang.demosforapi.shortvideo.data;

import com.alibaba.fastjson.JSON;
import com.github.hailouwang.demosforapi.shortvideo.common.ShortVideo;
import com.github.hailouwang.demosforapi.shortvideo.common.ShortVideoList;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class ShortVideoRepo {
    private static String data = "{\"video_list\":[{\"video_cover\":\"https://p9-dy.byteimg.com/img/tos-cn-p-0015/35474e356f6245eda225739b72ffec73~c5_300x400.jpeg?from=2563711402_large\",\"video_title\":\"分享我解决皮肤问题的方法\uD83D\uDC4C\uD83C\uDFFC\",\"video_url\":\"https://file.17gwx.com/spider_video/6826898900176096512.mp4\",\"video_like_text\":\"8.8万\",\"author_avatar\":\"https://p3-dy-ipv6.byteimg.com/aweme/720x720/3159800019c1303eda251.jpeg\",\"is_like\":0,\"author_name\":\"鹏翼Link\",\"width\":\"720\",\"height\":\"1280\",\"video_length\":\"41\"},{\"video_cover\":\"https://gw1.alicdn.com/tfscom/tuitui/O1CN01DcSyAV1G9gJLKeh54_!!2696600580-0-beehive-scenes.jpg\",\"video_title\":\"转盘做起泡胶PK，看谁做的颜值高\",\"video_url\":\"https://cloud.video.taobao.com/play/u/2696600580/p/1/e/6/t/1/d/ld/258513292604.mp4\",\"video_like_text\":\"2560\",\"author_avatar\":\"https://gw.alicdn.com/imgextra/i4/2696600580/O1CN01raWBPQ1G9gF9QumNt_!!2696600580-0-beehive-scenes.jpg\",\"is_like\":0,\"author_name\":\"妙艺手工\",\"width\":\"1920\",\"height\":\"1080\",\"video_length\":\"145\"},{\"video_cover\":\"https://gw3.alicdn.com/tfscom/tuitui/O1CN01qOUzRB2B0022eqy9J_!!2321518275-0-beehive-scenes.jpg\",\"video_title\":\"酸奶洗液态玻璃会化水？输的吃芥末\",\"video_url\":\"https://cloud.video.taobao.com/play/u/2321518275/p/1/e/6/t/1/d/ld/254910967551.mp4\",\"video_like_text\":3722,\"author_avatar\":\"https://gw.alicdn.com/imgextra/i2/2321518275/O1CN01juhCyi2AzzvVMPMHU_!!2321518275-2-beehive-scenes.png\",\"is_like\":0,\"author_name\":\"菠萝姐姐手工坊\",\"width\":\"1920\",\"height\":\"1080\",\"video_length\":\"110\"},{\"video_cover\":\"https://img2.feigua.cn/img/tos-cn-p-0015/ae8994da213a4271a82e3300fd476557_1588937498~c5_300x400.jpeg?from=2563711402_large-thumb\",\"video_title\":\"155以上的姐妹都可以穿的白色连衣裙，划重点 露腰设计很心机撩人哦～  #穿搭\",\"video_url\":\"https://file.17gwx.com/spider_video/6824434572185455872.mp4\",\"video_like_text\":\"6.6万\",\"author_avatar\":\"https://img2.feigua.cn/aweme/720x720/2e1fc0007b90738ae303c.jpeg?from=4010531038-thumb\",\"is_like\":0,\"author_name\":\"马马马琼仪\",\"width\":\"720\",\"height\":\"1280\",\"video_length\":\"7\"},{\"video_cover\":\"https://gw2.alicdn.com/tfscom/tuitui/O1CN01V7BrXe20NOUFSr7XA_!!2795766837-0-beehive-scenes.jpg\",\"video_title\":\"黑金泥改造棉花泥，学会了请点赞\",\"video_url\":\"https://cloud.video.taobao.com/play/u/2795766837/p/1/e/6/t/1/d/ld/253820839612.mp4\",\"video_like_text\":4538,\"author_avatar\":\"https://gw.alicdn.com/imgextra/i2/2795766837/O1CN019O6N0Z20NOSkmxqCj_!!2795766837-0-beehive-scenes.jpg\",\"is_like\":0,\"author_name\":\"架子手作\",\"width\":\"1280\",\"height\":\"720\",\"video_length\":\"67\"},{\"video_cover\":\"https://img2.feigua.cn/img/tos-cn-p-0015/b2c5ba0975494293b740cc9d706435c5~c5_300x400.jpeg?from=2563711402_large-thumb\",\"video_title\":\"渣男们，想知道少女收割机的味道吗？ #斩女香\",\"video_url\":\"https://file.17gwx.com/spider_video/6816496256534252812.mp4\",\"video_like_text\":\"7.2万\",\"author_avatar\":\"https://img2.feigua.cn/aweme/720x720/30f11000b7eb5c48c010a.jpeg-thumb\",\"is_like\":0,\"author_name\":\"橙子\uD83C\uDF4A\uD83C\uDF4A\",\"width\":\"720\",\"height\":\"1274\",\"video_length\":\"8\"}]}";

    public static Observable<List<ShortVideo>> getShortVideoData() {
        return Observable.just(data)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map(new Function<String, List<ShortVideo>>() {
                    @Override
                    public List<ShortVideo> apply(String s) throws Exception {
                        try {
                            ShortVideoList shortVideoList = JSON.parseObject(s, ShortVideoList.class);
                            if (shortVideoList == null || shortVideoList.getShortVideoList() == null) {
                                throw new Exception("Invalid Datas!!!");
                            }
                            return shortVideoList.getShortVideoList();
                        } catch (Exception e) {
                            return new ArrayList<>();
                        }
                    }
                });
    }
}
