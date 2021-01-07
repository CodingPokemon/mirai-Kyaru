package Thread;

import HttpUtils.HttpSender;
import com.alibaba.fastjson.JSONObject;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.contact.Group;

import java.util.NoSuchElementException;

public class XueeLive implements Runnable{
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
        }
        Group group = bot.getGroup(Long.parseLong(groupnum));
        JSONObject object = HttpSender.doGet("http://api.live.bilibili.com/room/v1/Room/getRoomInfoOld?mid="+bilibiliuid);
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
