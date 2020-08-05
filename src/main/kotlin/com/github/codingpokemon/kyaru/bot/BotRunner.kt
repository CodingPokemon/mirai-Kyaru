package com.github.codingpokemon.kyaru.bot

import com.alibaba.fastjson.JSONArray
import com.alibaba.fastjson.JSONObject
import com.github.codingpokemon.kyaru.utils.HttpUtil
import kotlinx.coroutines.runBlocking
import net.mamoe.mirai.Bot
import net.mamoe.mirai.alsoLogin
import net.mamoe.mirai.event.subscribeGroupMessages
import net.mamoe.mirai.join
import net.mamoe.mirai.message.data.Image
import net.mamoe.mirai.message.data.content
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.net.URL

@Component
class BotRunner(val bot: Bot) : CommandLineRunner {
    override fun run(vararg args: String?) {
        runBlocking {
            bot.alsoLogin()//新建Bot并登录
            bot.subscribeGroupMessages {
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
                            val data = (json["data"] as JSONArray)[0] as JSONObject
                            val url = URL("https://www.pixivdl.net/" + data.getString("url").substring(20))
                            val setu = uploadImage(url)
                            reply(Image(setu.imageId))
                        }
                    }
                }
            }
            // 等待 Bot 离线, 避免主线程退出
            bot.join()
        }
    }
}