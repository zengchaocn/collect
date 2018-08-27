package cn.data.collect.service.impl;

import cn.data.collect.dao.TaskMapper;
import cn.data.collect.model.Task;
import cn.data.collect.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskMapper taskMapper;

    @Transactional
    @Override
    public void addTask(Task task) {
        taskMapper.insert(task);
    }

    @Transactional
    @Override
    public void deleteTask(Integer taskid) {
        taskMapper.deleteByPrimaryKey(taskid);
    }

    @Override
    public List<Task> queryForList() {
        return taskMapper.selectByExample(null);
    }
}
