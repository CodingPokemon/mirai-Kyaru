package com.github.codingpokemon.kyaru.coroutines

import com.alibaba.fastjson.JSONObject
import com.github.codingpokemon.kyaru.utils.HttpUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

suspend fun liveroutine(bilibiliuid:String){
    withContext(Dispatchers.IO){
//        var bot: Bot? = null
//        try {
//            bot = Bot.getInstance(qqnumber.toLong())
//        } catch (e: NoSuchElementException) {
//            e.printStackTrace()
//        }
//        val group = bot!!.getGroup(groupnum.toLong())
        val `object`: JSONObject = HttpUtil.getBlibiliLiveStatus(bilibiliuid)
        val data = `object`["data"] as JSONObject?
        val status = data!!.getString("liveStatus")
        println(status)
    }
}

fun main() {
    GlobalScope.launch {
       liveroutine("169837")
       liveroutine("145149047")
    }
    Thread.sleep(10000)
}