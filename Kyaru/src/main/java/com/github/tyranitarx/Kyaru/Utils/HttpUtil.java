package com.github.tyranitarx.Kyaru.Utils;

import net.dreamlu.mica.http.DomMapper;
import net.dreamlu.mica.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import lombok.experimental.UtilityClass;

/**
 * @author tyranitar
 * @email tyranitarx@163.com
 * @date 2020-07-23 18:24
 */

@UtilityClass
public class HttpUtil {
    /**
     * 获取彩虹屁
     *
     * @return
     */
    public static String getChp() {
        String url = "https://chp.shadiao.app/api.php";


        return HttpRequest.get(url)
                .execute()
                .asString();
    }


    /**
     * 获取对线用语
     *
     * @return
     */
    public static String getDuixian() {
        return getDuixian(2);

    }

    /**
     * 获取对线用语
     * * 1: 火力全开 2：🙂口吐莲花
     *
     * @return
     */
    public static String getDuixian(int level) {
        String url = "https://nmsl.shadiao.app/api.php";

        if (level == 2) {
            url = url + "?level=min";
        }
        return HttpRequest.get(url)
                .execute()
                .asString();
    }

    public static JSONObject getxueeeeLiveStatus(String bilibiliuid){
        String url= "http://api.live.bilibili.com/room/v1/Room/getRoomInfoOld?mid="+bilibiliuid;
        String body = HttpRequest.get(url).execute().asString();
        JSONObject object=JSON.parseObject(body);
        JSONObject data = (JSONObject) object.get("data");
        String status = data.getString("liveStatus");
        return object;
    }

    public static JSONObject getSeTu(){
        String url= "https://api.lolicon.app/setu/?apikey=165869815f1ecda7c45c44";
        String body = HttpRequest.get(url).execute().asString();
        JSONObject object=JSON.parseObject(body);
        return object;
    }

    /**
     * 获取 马丁路德骂人生成器，收集了他作品里所有的脏话，连出处都有…
     *
     * @return
     */
    public static String getMartinLuther() {

        String s = HttpRequest.get("http://ergofabulous.org/luther/")
                .execute()
                .asString();

        Document document = DomMapper.readDocument(s);

        Element element = document.selectFirst("body > main > p.larger");

        String trim = element.toString().trim();

        String result = Util.cleanHtmlTag(trim);

        return result;
    }


}
