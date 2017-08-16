package com.task.allocation.util;

import com.alibaba.fastjson.JSONObject;
import com.gic.mq.sdk.GicMQClient;
import com.task.allocation.TaskAllocationConifg;
import com.task.allocation.exception.TaskAllocationException;
import com.task.allocation.qo.InitTaskQo;
import com.task.allocation.qo.TaskCallbackQo;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Administrator on 2017/8/13.
 */
public class TaskAllocationSdkClient {
    private static TaskAllocationSdkClient client=null;
    private GicMQClient gicMQClient=null;

    private final String INIT_TASK_MQ_KEY="taskAllocationInitTask";
    private final String CALLBACK_TASK_MQ_KEY="taskAllocationCallback";

    private TaskAllocationSdkClient(){
        gicMQClient=TaskAllocationConifg.gicMQClient;
    }

    /**
     * 获取客户端
     * @return
     */
    public static TaskAllocationSdkClient getInstance() throws TaskAllocationException {
        if (TaskAllocationConifg.gicMQClient==null&&false) {
            throw new TaskAllocationException("请加载队列sdk");
        }
        if (client==null) {
            synchronized (TaskAllocationSdkClient.class) {
                if (client==null) {
                    client=new TaskAllocationSdkClient();
                }
            }
        }
        return client;
    }

    /**
     *初始化队列
     */
    public void initTask(InitTaskQo initTaskQo) throws Exception {
        if(StringUtils.isBlank(initTaskQo.getEnterpriseId())||StringUtils.isBlank(initTaskQo.getOperationUserId())||
                StringUtils.isBlank(initTaskQo.getParams())||StringUtils.isBlank(initTaskQo.getTaskMqKey())||
                StringUtils.isBlank(initTaskQo.getTaskSignKey())) {
            throw new Exception("initTask参数有空值");
        }
//        gicMQClient.sendMessage(INIT_TASK_MQ_KEY, JSONObject.toJSONString(initTaskQo));
        sendMessage(INIT_TASK_MQ_KEY,JSONObject.toJSONString(initTaskQo));
    }

    public void resultCallback(TaskCallbackQo taskCallbackQo) {
        try {
//            gicMQClient.sendMessage(INIT_TASK_MQ_KEY, JSONObject.toJSONString(taskCallbackQo));
            sendMessage(CALLBACK_TASK_MQ_KEY, JSONObject.toJSONString(taskCallbackQo));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void sendMessage(String key,String val) throws Exception {
        if (TaskAllocationConifg.isTest==null) {
            gicMQClient.sendMessage(key, val);
        }else {
            TaskAllocationConifg.isTest.run(val);
        }
    }
    /**
     * 是否删除
     * @param taskAllocationId
     * @return
     */
    public boolean isRemove(String taskAllocationId) {
        int status = TaskAllocationMemcache.getStatus(taskAllocationId);
        if (status==TaskAllocationConifg.TASK_STATUS_FAIL||status==TaskAllocationConifg.TASK_STATUS_EXCEPTION) {
            return true;
        }
        return false;
    }
}
