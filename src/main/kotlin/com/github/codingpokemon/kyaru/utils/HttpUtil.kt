package com.github.codingpokemon.kyaru.utils

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONObject
import net.dreamlu.mica.http.HttpRequest

class HttpUtil() {
    companion object {
        /**
         * 夸人
         */
        fun getKua(): String {
            val url = "https://chp.shadiao.app/api.php"
            return HttpRequest.get(url).execute().asString()
        }

        /**
         * 对线用语
         */
        fun getMa(): String {
            val url = "https://nmsl.shadiao.app/api.php?level=min"
            return HttpRequest.get(url).execute().asString()
        }

        /**
         * 获取bilibili直播间信息
         */
        fun getBlibiliLiveStatus(bilibiliuid: String): JSONObject {
            val url = "http://api.live.bilibili.com/room/v1/Room/getRoomInfoOld?mid=$bilibiliuid"
            val body = HttpRequest.get(url).execute().asString()
            return JSONObject.parseObject(body)
        }

        /**
         * 获取setu
         * @return
         */
        fun getSeTu(keyword: String): JSONObject? {
            val url = "https://api.lolicon.app/setu/?apikey=165869815f1ecda7c45c44&keyword=$keyword"
            val body = HttpRequest.get(url).execute().asString()
            return JSON.parseObject(body)
        }
    }
}