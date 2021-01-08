package Events;

import Utils.HttpUtils;
import kotlin.coroutines.CoroutineContext;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.message.data.Image;
import net.mamoe.mirai.utils.ExternalResource;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class EventListener extends SimpleListenerHost {
    @Override
    public void handleException(@NotNull CoroutineContext context, @NotNull Throwable exception) {
        super.handleException(context, exception);
    }

    @EventHandler
    public void onMessage(@NotNull MessageEvent event) {
        if (event.getMessage().contentToString().contains("/luck")) {
            Random random = new Random();
            Image luck = event.getSubject().uploadImage(ExternalResource.create(HttpUtils.getPic("http://www.55chaxun.com/images/gy/" + random.nextInt(100) + ".gif")));
            event.getSubject().sendMessage(Image.fromId(luck.getImageId()));
        }
    }

    @EventHandler
    public void setu(@NotNull GroupMessageEvent event) {
        if(event.getMessage().contentToString().contains("/hso")){

        }
    }
}
