package cn.data.collect.service.impl;

import cn.data.collect.bean.PageBean;
import cn.data.collect.bean.PaginResult;
import cn.data.collect.dao.TaskMapper;
import cn.data.collect.model.Task;
import cn.data.collect.model.TaskExample;
import cn.data.collect.service.TaskService;
import cn.data.collect.task.TaskManager;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private TaskManager taskManager;

    @Transactional
    @Override
    public Integer addTask(Task task) {
        task.setCreatetime(new Date());
        taskMapper.insert(task);
        if(task.getState().equals("R")) {
            taskManager.startup(task);
        } else {
            CronTrigger ct = new CronTrigger(task.getCron());
        }
        return task.getTaskid();
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

    @Override
    public PaginResult<Task> queryForPage(PageBean<Task> pageBean) {
        TaskExample example = new TaskExample();
        Task entity = pageBean.getEntity();
        TaskExample.Criteria criteria = example.createCriteria();
        if(entity.getTaskname() != null) {
            criteria.andTasknameLike(entity.getTaskname()+"%");
        }
        if(entity.getCreatetimeStart() != null && entity.getCreatetimeEnd() != null) {
            criteria.andCreatetimeBetween(entity.getCreatetimeStart(), entity.getCreatetimeEnd());
        }
        PageHelper.startPage(pageBean.getPage(), pageBean.getLimit());
        List<Task> list = taskMapper.selectByExample(example);
        PaginResult<Task> pageInfo = new PaginResult<>(list);
        return pageInfo;
    }
}
