package Events;

import kotlin.coroutines.CoroutineContext;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.event.events.MessageEvent;
import org.jetbrains.annotations.NotNull;

public class EventListener extends SimpleListenerHost {
    @Override
    public void handleException(@NotNull CoroutineContext context, @NotNull Throwable exception) {
        super.handleException(context, exception);
    }
    @EventHandler
    public void onMessage(@NotNull MessageEvent event){
        if(event.getMessage().contentToString().contains("hello"))
            event.getSubject().sendMessage("不能再色了");
    }
    @EventHandler
    public void setu(@NotNull GroupMessageEvent event){

    }
}
