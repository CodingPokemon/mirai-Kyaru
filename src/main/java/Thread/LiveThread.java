package Thread;

import Utils.HttpUtils;
import com.alibaba.fastjson.JSONObject;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.contact.Group;

import java.util.NoSuchElementException;

public class LiveThread implements Runnable{
    public ThreadLocal<Integer> threadLocal;
    private final String livername;
    private final String bilibiliuid;
    private final String qqnumber;
    private final String groupnum;

    public LiveThread(ThreadLocal<Integer> threadLocal, String livername, String bilibiliuid, String qqnumber, String groupnum) {
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
        JSONObject object = JSONObject.parseObject(HttpUtils.doGet("http://api.live.bilibili.com/room/v1/Room/getRoomInfoOld?mid="+bilibiliuid));
        JSONObject data = (JSONObject) object.get("data");
        String status = data.getString("liveStatus");
        String link = data.getString("link");
        if (status.equals("1")&&threadLocal.get().equals(0)) {
            group.sendMessage(livername+"正在直播“" + data.get("title")+"”");
            group.sendMessage("赶紧点击"+link.substring(0,link.indexOf('?'))+"观看吧！");
            threadLocal.set(1);
        }else if(!status.equals("1")){
            threadLocal.set(0);
        }
    }
}
