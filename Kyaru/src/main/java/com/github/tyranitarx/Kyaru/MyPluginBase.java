package com.github.tyranitarx.Kyaru;

import net.mamoe.mirai.console.plugins.PluginBase;
import net.mamoe.mirai.message.GroupMessage;
import net.mamoe.mirai.message.GroupMessageEvent;

public class MyPluginBase extends PluginBase {

    @Override
    public void onLoad() {
        getLogger().info("凯露 Plugin loadeding...");
    }

    @Override
    public void onEnable() {
        getLogger().info("凯露 Plugin loaded!");
        getEventListener().subscribeAlways(GroupMessageEvent.class, new TotalListener());
    }


}
