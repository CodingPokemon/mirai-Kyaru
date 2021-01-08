package Utils;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;

import java.io.IOException;

public class HttpUtils {
    private static OkHttpClient client = new OkHttpClient();

    public static JSONObject doGet(String urlstr) {
        Request request = new Request.Builder()
                .url(urlstr)
                .addHeader("User-Agent","Mozilla/4.0 (compatible; MSIE 7.0; Windows 7)")
                .addHeader("Cookie","_uuid=AA5E7DB7-D717-B149-7B38-C85B985B612F01763infoc; buvid3=8A0F4813-0E72-4EB9-AAB2-E4D4B219A95E40961infoc; sid=8m0jtn4c; rpdid=|(mmJ|~mmRJ0J'ulmRJJmJ)m; LIVE_BUVID=AUTO6215947743895599; blackside_state=1; CURRENT_FNVAL=80; _ga=GA1.2.960828246.1606744451; fingerprint3=d3354728192e76bdc99572c372668e03; fingerprint_s=0d9aaa337268a4dbd926ca3702313da5; CURRENT_QUALITY=0; Hm_lvt_8a6e55dbd2870f0f5bc9194cddf32a02=1607655107,1608705624,1609152103,1609155033; bp_article_offset_297821=474412471084593363; PVID=1; bp_t_offset_297821=476796792115870256; fingerprint=d2f029af48d14114b9737774ac2d6126; buvid_fp=8A0F4813-0E72-4EB9-AAB2-E4D4B219A95E40961infoc; buvid_fp_plain=B4F7D67C-716C-47DB-9F3D-BB399D40C24595172infoc; DedeUserID=297821; DedeUserID__ckMd5=6d27cd0ef9c5d466; SESSDATA=59f1ed2f%2C1625482148%2C369ba*11; bili_jct=1146ad77ae65a60e235b3e42b1e80638; bp_video_offset_297821=477180620457983129")
                .build();
        try (Response response = client.newCall(request).execute()) {
            return JSONObject.parseObject(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JSONObject doPost(String urlstr,String json){
        final MediaType JSON = MediaType.get("application/json; charset=utf-8");
        RequestBody body =RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(urlstr)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return JSONObject.parseObject(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] getPic(String urlstr){
        Request request = new Request.Builder()
                .url(urlstr)
                .addHeader("User-Agent","Mozilla/4.0 (compatible; MSIE 7.0; Windows 7)")
                .addHeader("Cookie","_uuid=AA5E7DB7-D717-B149-7B38-C85B985B612F01763infoc; buvid3=8A0F4813-0E72-4EB9-AAB2-E4D4B219A95E40961infoc; sid=8m0jtn4c; rpdid=|(mmJ|~mmRJ0J'ulmRJJmJ)m; LIVE_BUVID=AUTO6215947743895599; blackside_state=1; CURRENT_FNVAL=80; _ga=GA1.2.960828246.1606744451; fingerprint3=d3354728192e76bdc99572c372668e03; fingerprint_s=0d9aaa337268a4dbd926ca3702313da5; CURRENT_QUALITY=0; Hm_lvt_8a6e55dbd2870f0f5bc9194cddf32a02=1607655107,1608705624,1609152103,1609155033; bp_article_offset_297821=474412471084593363; PVID=1; bp_t_offset_297821=476796792115870256; fingerprint=d2f029af48d14114b9737774ac2d6126; buvid_fp=8A0F4813-0E72-4EB9-AAB2-E4D4B219A95E40961infoc; buvid_fp_plain=B4F7D67C-716C-47DB-9F3D-BB399D40C24595172infoc; DedeUserID=297821; DedeUserID__ckMd5=6d27cd0ef9c5d466; SESSDATA=59f1ed2f%2C1625482148%2C369ba*11; bili_jct=1146ad77ae65a60e235b3e42b1e80638; bp_video_offset_297821=477180620457983129")
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().bytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
