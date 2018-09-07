package cn.data.collect.task;

import cn.data.collect.util.Constants;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ShopTask implements Runnable {
    private static Logger logger = LoggerFactory.getLogger(ShopTask.class);
    private static final int LIMIT = 24;
    private static final int DEEP = 1;

    private Integer taskId;
    private String taskName;

    @Override
    public void run() {
        logger.info("{}:{} run", taskName, taskId);

    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    private String getJsonString() throws IOException {
        Map<String, String> argMap = new HashMap<>();
        argMap.put("extras[]", "activities");
        argMap.put("geohash", "ws0edengxq12");
        argMap.put("latitude", "23.132347");
        argMap.put("longitude", "113.322571");
        argMap.put("limit", "100");
        argMap.put("offset", "0");
        argMap.put("terminal", "web");
        Document document = Jsoup.connect(Constants.E_SCAN_SHOP_URL)
                .header("Accept", "application/json, text/plain, */*")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8")
                .header("Cache-Control", "no-cache")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36")
                .ignoreContentType(true).data(argMap).get();
        return document.text();
    }
}
