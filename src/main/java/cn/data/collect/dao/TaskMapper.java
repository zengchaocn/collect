package cn.data.collect.dao;

import cn.data.collect.model.Task;
import cn.data.collect.model.TaskExample;
import java.util.List;

public interface TaskMapper {
    int deleteByPrimaryKey(Integer taskid);

    int insert(Task record);

    int insertSelective(Task record);

    List<Task> selectByExample(TaskExample example);

    Task selectByPrimaryKey(Integer taskid);

    int updateByPrimaryKeySelective(Task record);

    int updateByPrimaryKey(Task record);
}