import Events.DailyEventListener;
import Events.DotaEventListener;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.event.EventChannel;
import net.mamoe.mirai.utils.BotConfiguration;
import Thread.LiveThread;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class KayruBotMain {
    public static void main(String[] args) {
        ThreadLocal<Integer> threadLocal = new InheritableThreadLocal<Integer>();
        Bot bot = BotFactory.INSTANCE.newBot(12345, "12345", new BotConfiguration() {{
            fileBasedDeviceInfo("./device.json"); // 使用 device.json 存储设备信息
            setProtocol(MiraiProtocol.ANDROID_PAD);
        }});
        bot.login();
        EventChannel channel = bot.getEventChannel();
        channel.registerListenerHost(new DailyEventListener());
        channel.registerListenerHost(new DotaEventListener());
        try {
            ScheduledExecutorService scheduledExecutorService =
                    Executors.newSingleThreadScheduledExecutor();
            scheduledExecutorService.scheduleAtFixedRate(new LiveThread(threadLocal,"你们的爱抖露","297821","3479193691","10158005"),
                    0, 2, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
