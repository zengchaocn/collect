package cn.data.collect.task;

import cn.data.collect.model.Task;
import cn.data.collect.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskStartupRunner implements ApplicationRunner {
    private static Logger logger = LoggerFactory.getLogger(TaskStartupRunner.class);

    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskManager taskManager;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Task> taskList = taskService.queryForList();
        for(Task task : taskList) {
            if(task.getState().equals(TaskState.RUNNING.getState())) {
                taskManager.startup(task);
            }
        }
    }
}
