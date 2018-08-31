package cn.data.collect.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShopTask implements Runnable {
    private static Logger logger = LoggerFactory.getLogger(ShopTask.class);

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
}
