package com.github.tyranitarx.Kyaru.Thread;

import com.alibaba.fastjson.JSONObject;
import com.github.tyranitarx.Kyaru.Utils.HttpUtil;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.utils.MiraiLogger;
import net.mamoe.mirai.utils.Utils;

import java.util.NoSuchElementException;


public class XueeLive implements Runnable {
    MiraiLogger log = Utils.getDefaultLogger().invoke("liveThread");
    public ThreadLocal<Integer> threadLocal;
    private String livername;
    private String bilibiliuid;
    private String qqnumber;
    private String groupnum;

    public XueeLive(ThreadLocal<Integer> threadLocal, String livername, String bilibiliuid, String qqnumber, String groupnum) {
        this.threadLocal = threadLocal;
        this.livername = livername;
        this.bilibiliuid = bilibiliuid;
        this.qqnumber = qqnumber;
        this.groupnum = groupnum;
    }

    @Override
    public void run() {
        Bot bot = null;
        try {
            bot = Bot.getInstance(Long.parseLong(qqnumber));
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            log.info("ERROR");
        }
        Group group = bot.getGroup(Long.parseLong(groupnum));
        JSONObject object = HttpUtil.getxueeeeLiveStatus(bilibiliuid);
        JSONObject data = (JSONObject) object.get("data");
        String status = data.getString("liveStatus");
        if (status.equals("1")&&threadLocal.get().equals(0)) {
            group.sendMessage(livername+"正在直播“" + data.get("title")+"”");
            threadLocal.set(1);
        }else if(!status.equals("1")){
            threadLocal.set(0);
        }
    }
}
