package com.github.tyranitarx.Kyaru;

import com.github.tyranitarx.Kyaru.Listener.TotalListener;
import com.github.tyranitarx.Kyaru.Thread.XueeLive;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.BotFactoryJvm;
import net.mamoe.mirai.console.plugins.Config;
import net.mamoe.mirai.console.plugins.PluginBase;
import net.mamoe.mirai.message.GroupMessageEvent;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MyPluginBase extends PluginBase {
    public ThreadLocal<Integer> threadLocal = new InheritableThreadLocal<Integer>();
    private Config setting;
    @Override
    public void onLoad() {
        getLogger().info("凯露 Plugin loading...");
        this.setting = this.loadConfig("config.yml");
    }

    @Override
    public void onEnable() {
        String bilibiliuid = setting.getString("bilibiliuid");
        String qqnumber = setting.getString("qqnumber");
        String password = setting.getString("password");
        String livername = setting.getString("livername");
        String groupnum = setting.getString("groupnum");
        threadLocal.set(0);
        Bot bot = BotFactoryJvm.newBot(Long.parseLong(qqnumber),password);
        bot.login();
        getLogger().info("凯露 Plugin loaded!");
        getEventListener().subscribeAlways(GroupMessageEvent.class, new TotalListener());
        try {
            ScheduledExecutorService scheduledExecutorService =
                    Executors.newSingleThreadScheduledExecutor();
            scheduledExecutorService.scheduleAtFixedRate(new XueeLive(threadLocal,livername,bilibiliuid,qqnumber,groupnum),
                    0, 2, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
