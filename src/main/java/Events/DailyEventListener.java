package Events;

import Utils.HttpUtils;
import com.alibaba.fastjson.JSONObject;
import kotlin.coroutines.CoroutineContext;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.message.data.Image;
import net.mamoe.mirai.utils.ExternalResource;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class DailyEventListener extends SimpleListenerHost {
    @Override
    public void handleException(@NotNull CoroutineContext context, @NotNull Throwable exception) {
        super.handleException(context, exception);
    }

    @EventHandler
    public void onMessage(@NotNull MessageEvent event) {
        String eventString = event.getMessage().contentToString();
        if (eventString.contains("/luck")) {
            Random random = new Random();
            Image luck = event.getSubject().uploadImage(ExternalResource.create(HttpUtils.getPic("http://www.55chaxun.com/images/gy/" + random.nextInt(100) + ".gif")));
            event.getSubject().sendMessage(Image.fromId(luck.getImageId()));
        }
    }

    @EventHandler
    public void setu(@NotNull MessageEvent event) {
        String eventString = event.getMessage().contentToString();
        if(eventString.startsWith("/hso")){
            System.out.println(eventString);
            String[] args = eventString.split(" ");
            if(args.length>=3){
                event.getSubject().sendMessage("参数数量错误,示例：/hso 蝎宝");
            }
            else{
                String apiurl = args.length==1?"https://api.lolicon.app/setu/?apikey=165869815f1ecda7c45c44":"https://api.lolicon.app/setu/?apikey=165869815f1ecda7c45c44&keyword="+args[1];
                JSONObject setujson = JSONObject.parseObject(HttpUtils.doGet(apiurl));
                String url = setujson.getJSONArray("data").getJSONObject(0).getString("url");
                Image setu = event.getSubject().uploadImage(ExternalResource.create(HttpUtils.getPic(url)));
                event.getSubject().sendMessage("好色哦");
                event.getSubject().sendMessage(Image.fromId(setu.getImageId()));
            }
        }
    }
}
