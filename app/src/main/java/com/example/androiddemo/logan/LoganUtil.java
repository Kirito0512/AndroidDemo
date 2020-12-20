package com.example.androiddemo.logan;

import com.dianping.logan.Logan;
import com.dianping.logan.LoganConfig;
import com.dianping.logan.SendLogDefaultRunnable;
import com.dianping.logan.Util;
import com.example.androiddemo.ApplicationUtil;
import com.example.androiddemo.BuildConfig;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class LoganUtil {
    public static final int LOGAN_VERBOSE = 2;

    public static final int LOGAN_DEBUG = 3;

    public static final int LOGAN_INFO = 4;

    public static final int LOGAN_WARN = 5;

    public static final int LOGAN_ERROR = 6;

    public static final int LOGAN_ASSERT = 7;

    private static final String UPLOAD_LOGAN_URL = "https://openlogan.inf.test.sankuai.com/logan/upload.json";//接受日志的服务器完整url.

    public static void init() {
        LoganConfig config = new LoganConfig.Builder()
                .setCachePath(ApplicationUtil.getInstance().getApplication().getFilesDir().getAbsolutePath())
                .setPath(ApplicationUtil.getInstance().getApplication().getExternalFilesDir(null).getAbsolutePath() + File.separator + "logan_v1")
                .setEncryptKey16("0123456789012345".getBytes())
                .setEncryptIV16("0123456789012345".getBytes())// IV称为初始向量，加密和解密需要相同的IV，既然IV看起来和key一样，却还要多一个IV的目的，对于每个块来说，key是不变的，但是只有第一个块的IV是用户提供的，其他块IV都是自动生成。
                .build();
        Logan.init(config);
        Logan.setDebug(BuildConfig.DEBUG);
    }

    public static void v(String msg) {
        String log = msg + getDefaultKtvParams();
        Logan.w(log, LOGAN_VERBOSE);
    }

    public static void d(String msg) {
        String log = msg + getDefaultKtvParams();
        Logan.w(log, LOGAN_DEBUG);
    }

    public static void i(String msg) {
        String log = msg + getDefaultKtvParams();
        Logan.w(log, LOGAN_INFO);
    }

    public static void w(String msg) {
        String log = msg + getDefaultKtvParams();
        Logan.w(log, LOGAN_WARN);
    }
    public static void e(String msg) {
        String log = msg + getDefaultKtvParams();
        Logan.w(log, LOGAN_ERROR);
    }

    private static String getDefaultKtvParams() {
        return "";
    }

    // 时间戳信息，多个时间戳用 , 分隔开 时间戳最好给13位的，如果给10位的，客户端 *1000L再使用Util.getDateStr
    public static void sendLog(String date) {
        if (date == null || date.length() == 0) {
            sendAllLog();
            return;
        }
        List<String> dateList = Arrays.asList(date.split(","));
        sendLog(dateList);
    }

    public static void sendLog(List<String> dateList) {
        if (dateList == null || dateList.size() == 0)
            return;
        String[] dateArray = new String[dateList.size()];
        int index= 0;
        for (String dateStr : dateList) {
            // 把时间戳信息转换成对应的日期 如 2020-12-10
            dateArray[index++] = Util.getDateStr(Long.parseLong(dateStr));
        }
        SendLogDefaultRunnable runnable = new SendLogDefaultRunnable();
        runnable.setUrl(UPLOAD_LOGAN_URL);
        runnable.setRequestHeader(getDefaultLoganHeaders());
        Logan.s(dateArray, runnable);
    }

    private static Map<String, String> getDefaultLoganHeaders() {
        return null;
    }

    // 上报本地全部日志
    public static void sendAllLog() {
        Map<String, Long> map = Logan.getAllFilesInfo();
        if (map != null) {
            List<String> dateList = new ArrayList<>(map.size());
            for (Map.Entry<String, Long> entry : map.entrySet()) {
                dateList.add(entry.getKey());
            }
            sendLog(dateList);
        }
    }
}
