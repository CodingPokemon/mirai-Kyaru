package Events;

import Entity.DotaMatch;
import Utils.HttpUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import kotlin.coroutines.CoroutineContext;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.MessageEvent;
import org.jetbrains.annotations.NotNull;


public class DotaEventListener extends SimpleListenerHost {
    @Override
    public void handleException(@NotNull CoroutineContext context, @NotNull Throwable exception) {
        super.handleException(context, exception);
    }

    @EventHandler
    public void recentMatches(@NotNull MessageEvent event) {
        String eventString = event.getMessage().contentToString();
        if (eventString.startsWith("/dota")) {
            String[] args = eventString.split(" ");
            if (args.length >= 3 && args[1].equals("recent")) {
                System.out.println(args[2]);
                JSONArray array = JSONArray.parseArray(HttpUtils.doGet("https://api.opendota.com/api/players/" + args[2] + "/recentMatches"));
                System.out.println(array);
                DotaMatch latest = array.getObject(0,DotaMatch.class);
                System.out.println(latest);
                String playerslot = latest.getPlayer_slot() > 127 ? "夜魇" : "天辉";
                String win = latest.isRadiant_win() && playerslot.equals("天辉")?"获胜":"战败";
                event.getSubject().sendMessage(args[2]+"的最近一把dota2比赛为"+playerslot+"阵营"+"\n"+
                "击杀"+latest.getKills()+"\n"+
                "死亡"+latest.getDeaths()+"\n"+
                "助攻"+latest.getAssists()+"\n"+
                "最终"+win);
            }
        }
    }
}
