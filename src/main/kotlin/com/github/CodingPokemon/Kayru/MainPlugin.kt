package com.github.CodingPokemon.Kayru

import com.alibaba.fastjson.JSONArray
import com.alibaba.fastjson.JSONObject
import com.github.CodingPokemon.Kayru.Utils.HttpUtil
import net.mamoe.mirai.Bot
import net.mamoe.mirai.alsoLogin
import net.mamoe.mirai.event.subscribeGroupMessages
import net.mamoe.mirai.join
import net.mamoe.mirai.message.data.Image
import net.mamoe.mirai.message.data.content
import net.mamoe.mirai.utils.DefaultLogger
import java.net.URL

suspend fun main() {
    val logger = DefaultLogger("main")
    val qqId = 3044668489L//Bot的QQ号，需为Long类型，在结尾处添加大写L
    val password = "a3447664"//Bot的密码
    val miraiBot = Bot(qqId, password).alsoLogin()//新建Bot并登录
    miraiBot.subscribeGroupMessages {
        contains("123") reply "你好"
        (contains("色图")){
            var messages = message.toString()
            if (messages.contains("mirai:at:3044668489")) {
                val content = message.content
                val truecontent = content.split(" ")[1]
                val keyword = truecontent.split("色图")
                val json = HttpUtil.getSeTu(keyword[0].trim()) as JSONObject
                if (json.getString("code").equals("404")) {
                    reply("没有这样的色图呢")
                } else {
                    println("有色图")
                    val datas = json["data"] as JSONArray
                    val data = datas[0] as JSONObject
                    val url = URL("https://www.pixivdl.net/" + data.getString("url").substring(20))
                    val setu = uploadImage(url)
                    reply(Image(setu.imageId))
                }
            }
        }
    }
    miraiBot.join() // 等待 Bot 离线, 避免主线程退出
}