package cn.data.collect.task;

import cn.data.collect.model.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

@Component
public class TaskManager {
    private static Logger logger = LoggerFactory.getLogger(TaskManager.class);

    private Map<Integer, Task> taskMap = new ConcurrentHashMap<>();
    private Map<Integer, ScheduledFuture> futureMap = new ConcurrentHashMap<>();

    @Autowired
    private ThreadPoolTaskScheduler taskScheduler;

    public void startup(Task task) {
        if(taskMap.containsKey(task.getTaskid())) {
            logger.info("Task {} already running", task.getTaskid());
            return;
        }
        ShopTask shopTask = new ShopTask();
        shopTask.setTaskId(task.getTaskid());
        shopTask.setTaskName(task.getTaskname());
        Trigger trigger = new CronTrigger(task.getCron());
        ScheduledFuture future = taskScheduler.schedule(shopTask, trigger);
        taskMap.put(task.getTaskid(), task);
        futureMap.put(task.getTaskid(), future);
        logger.info("Task {} is running", task.getTaskid());
    }

    public void shutdown(Integer taskid) {
        Task task = taskMap.get(taskid);
        ScheduledFuture future = futureMap.get(taskid);
        if(task == null || future == null) {
            logger.info("Not found task {}", taskid);
            return;
        }
        future.cancel(true);
        taskMap.remove(taskid);
        futureMap.remove(taskid);
//        taskScheduler.shutdown();
        logger.info("Task {} has stop", taskid);
    }

    @Bean
    public ThreadPoolTaskScheduler createThreadPoolTaskScheduler() {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(100);
        threadPoolTaskScheduler.setThreadGroupName("TaskThreadPool");
        return threadPoolTaskScheduler;
    }

    public boolean isRunning(Integer taskid) {
        if(taskMap.containsKey(taskid)) {
            return true;
        }
        return false;
    }
}
