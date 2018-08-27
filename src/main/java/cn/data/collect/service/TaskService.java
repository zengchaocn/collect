package cn.data.collect.service;

import cn.data.collect.model.Task;

import java.util.List;

public interface TaskService {
    void addTask(Task task);

    void deleteTask(Integer taskid);

    List<Task> queryForList();
}
