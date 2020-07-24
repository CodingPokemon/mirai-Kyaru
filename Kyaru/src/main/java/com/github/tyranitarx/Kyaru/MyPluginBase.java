package com.github.tyranitarx.Kyaru;

import net.mamoe.mirai.console.plugins.PluginBase;
import net.mamoe.mirai.message.GroupMessage;

public class MyPluginBase extends PluginBase {

    @Override
    public void onLoad() {
        getLogger().info("凯露 Plugin loadeding...");
    }

    @Override
    public void onEnable() {
        getLogger().info("凯露 Plugin loaded!");
        getEventListener().subscribeAlways(GroupMessage.class, new MyListener());

    }

}
