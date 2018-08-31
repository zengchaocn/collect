package cn.data.collect.controller;

import cn.data.collect.bean.AjaxResult;
import cn.data.collect.bean.PageBean;
import cn.data.collect.bean.PaginResult;
import cn.data.collect.model.Task;
import cn.data.collect.service.TaskService;
import cn.data.collect.task.TaskManager;
import cn.data.collect.task.TaskState;
import cn.data.collect.util.StringUtils;
import com.sun.beans.editors.DoubleEditor;
import com.sun.beans.editors.FloatEditor;
import com.sun.beans.editors.IntegerEditor;
import com.sun.beans.editors.LongEditor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/task")
public class TaskController {
    private static Logger logger = LoggerFactory.getLogger(TaskController.class);
    @Autowired
    private TaskManager taskManager;
    @Autowired
    private TaskService taskService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sf1, true));
        binder.registerCustomEditor(int.class, new IntegerEditor());
        binder.registerCustomEditor(long.class, new LongEditor());
        binder.registerCustomEditor(double.class, new DoubleEditor());
        binder.registerCustomEditor(float.class, new FloatEditor());
    }

    @RequestMapping("listPage")
    public String taskList() {
        return "task/taskList";
    }

    @RequestMapping("list")
    @ResponseBody
    public PaginResult<Task> list(PageBean<Task> pageBean, Task task) {
        pageBean.setEntity(task);
        PaginResult<Task> result = taskService.queryForPage(pageBean);
        if(result.getList() != null) {
            for(Task t : result.getList()) {
                if(taskManager.isRunning(t.getTaskid())) {
                    t.setState(TaskState.RUNNING.getState());
                }else{
                    t.setState(TaskState.PAUSED.getState());
                }
            }
        }
        return result;
    }

    @RequestMapping("add")
    @ResponseBody
    public AjaxResult add(Task task) {
        AjaxResult result = new AjaxResult();
        try {
            taskService.addTask(task);
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }
        return result;
    }

    @RequestMapping("startup")
    @ResponseBody
    public AjaxResult startup(HttpServletRequest request, String ids) {
        AjaxResult result = new AjaxResult();
        try {
            if(StringUtils.isEmpty(ids)) {
                throw new IllegalArgumentException("未选择数据");
            }
            String[] idArray = ids.split(",");
            for (int i = 0; i < idArray.length; i++) {
                Task task = new Task();
                task.setTaskid(Integer.valueOf(request.getParameter(String.format("row[%d][taskid]", i))));
                task.setTaskname(request.getParameter(String.format("row[%d][taskname]", i)));
                task.setDescription(request.getParameter(String.format("row[%d][description]", i)));
                task.setCron(request.getParameter(String.format("row[%d][cron]", i)));
                taskManager.startup(task);
            }
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }
        return result;
    }

    @RequestMapping("shutdown")
    @ResponseBody
    public AjaxResult shutdown(String ids) {
        AjaxResult result = new AjaxResult();
        try {
            if(StringUtils.isEmpty(ids)) {
                throw new IllegalArgumentException("未选择数据");
            }
            String[] idArray = ids.split(",");
            for (int i = 0; i < idArray.length; i++) {
                taskManager.shutdown(Integer.valueOf(idArray[i]));
            }
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }
        return result;
    }
}
