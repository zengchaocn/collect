package cn.data.collect.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FoodTask implements Runnable {
    private static Logger logger = LoggerFactory.getLogger(FoodTask.class);

    @Override
    public void run() {
        logger.info("running foodtask");
    }
}
