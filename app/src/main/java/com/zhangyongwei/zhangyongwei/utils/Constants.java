package com.zhangyongwei.zhangyongwei.utils;

/**
 * Created by 张永卫on 2017/3/9.
 */

public class Constants {

    //公网连接
    public static final String BASE_URL = "http://182.92.5.3/web_home";
    //本地地址
//    public static final String BASE_URL = "http://192.168.1.25:8080/Tests/web_home";
    /**
     * 新闻中心的网络地址
     */
    public static final String NEWSCENTER_PAGER_URL = BASE_URL + "/static/api/news/10007/list_1.json";



    private static final String BASE_URL_JSON = BASE_URL+"/json/";
    /**
     * 主页面的路径
     */
    public static String HOME_URL  = BASE_URL_JSON+"HOME_URL.json";
    /**
     * 图片的基本路径
     */
    public static String BASE_URL_IMAGE  = BASE_URL+"/img";
    //发现页面-的-新特路径
    public static final String NEW_POST_URL = BASE_URL_JSON + "NEW_POST_URL.json";
    public static final String HOT_POST_URL = BASE_URL_JSON + "HOT_POST_URL.json";
}
