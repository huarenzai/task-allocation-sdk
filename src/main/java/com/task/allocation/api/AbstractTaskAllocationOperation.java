package com.task.allocation.api;

import com.alibaba.fastjson.JSONObject;
import com.task.allocation.exception.TaskAllocationException;
import com.task.allocation.qo.AllocationTaskQo;
import com.task.allocation.qo.TaskCallbackQo;
import com.task.allocation.util.MemCachedUtil;
import com.task.allocation.util.TaskAllocationSdkClient;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by Administrator on 2017/8/14.
 */
abstract public class AbstractTaskAllocationOperation implements TaskAllocationOperation {
    private Logger logger=Logger.getLogger(this.getClass());

    protected TaskAllocationSdkClient taskAllocationSdkClient;

    public AbstractTaskAllocationOperation(){
        try{
            taskAllocationSdkClient=TaskAllocationSdkClient.getInstance();
        }catch (TaskAllocationException e) {
            e.printStackTrace();
            logger.info("TaskAllocationSdkClient加载错误");
        }
    }

    /**
     * 处理数据
     */
    public void run(String params) {
        TaskCallbackQo taskCallbackQo = new TaskCallbackQo();
        AllocationTaskQo allocationTaskQo = JSONObject.parseObject(params, AllocationTaskQo.class);
        if (allocationTaskQo.getTaskAllocationId()==null){
            logger.info("异常数据!"+params);
            return;
        }
        //是否删除
        boolean remove = taskAllocationSdkClient.isRemove(allocationTaskQo.getTaskAllocationId());
        try {
            if (!remove)
                this.getListTasksCallback(allocationTaskQo);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("获取数据列表异常"+params);
            taskCallbackQo.setIsSuccess(0);//失败
            taskCallbackQo.setCallbackType(0);//初始化
            taskCallbackQo.setReason(e.getMessage());
            taskCallbackQo.setTaskAllocationId(allocationTaskQo.getTaskAllocationId());
            taskCallbackQo.setTaskTotal(0);
            taskAllocationSdkClient.resultCallback(taskCallbackQo);
        }
    }

    public void getListTasksCallback(AllocationTaskQo allocationTaskQo) {
        TaskCallbackQo taskCallbackQo = new TaskCallbackQo();
        List<Object> listTasks = this.getListTasks(allocationTaskQo.getParams());

        taskCallbackQo.setTaskAllocationId(allocationTaskQo.getTaskAllocationId());
        taskCallbackQo.setTaskTotal(listTasks.size());
        taskCallbackQo.setCallbackType(0);
        taskCallbackQo.setIsSuccess(1);
        taskAllocationSdkClient.resultCallback(taskCallbackQo);//成功回调

        taskCallbackQo.setCallbackType(1);
        taskCallbackQo.setTaskTotal(1);

        for (Object object:listTasks)
            try {
                //是否删除
                boolean remove = taskAllocationSdkClient.isRemove(allocationTaskQo.getTaskAllocationId());
                if (remove) break;//删除不往下运行
                this.dealSingle(object);
                taskCallbackQo.setTaskFailNum(0);
                taskCallbackQo.setIsSuccess(1);
                taskCallbackQo.setReason("");
            } catch (Exception e) {
                e.printStackTrace();
                logger.info("未知异常："+object);
                taskCallbackQo.setIsSuccess(0);
                taskCallbackQo.setTaskFailNum(1);
                taskCallbackQo.setReason(e.getMessage());
            }finally {
                taskAllocationSdkClient.resultCallback(taskCallbackQo);
            }
    }
}
