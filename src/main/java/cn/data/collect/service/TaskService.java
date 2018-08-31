package cn.data.collect.service;

import cn.data.collect.bean.PageBean;
import cn.data.collect.bean.PaginResult;
import cn.data.collect.model.Task;

import java.util.List;

public interface TaskService {
    Integer addTask(Task task);

    void deleteTask(Integer taskid);

    List<Task> queryForList();

    PaginResult<Task> queryForPage(PageBean<Task> pageBean);
}
