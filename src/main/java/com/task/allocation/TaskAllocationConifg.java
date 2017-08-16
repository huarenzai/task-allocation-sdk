package com.task.allocation;

import com.gic.mq.sdk.GicMQClient;
import com.task.allocation.api.TestResultBack;

/**
 * Created by Administrator on 2017/8/13.
 */
public class TaskAllocationConifg {
    public static GicMQClient gicMQClient=null;//队列
    public static String ip;
    public static int port;
    public static TestResultBack isTest=null;

    public static  int TASK_STATUS_INIT=1;//初始化
    public static  int TASK_STATUS_ALLOCATION_DEAL=2;//队列发送 等待分配处理结果
    public static  int TASK_STATUS_DEAL=3;//分配完成 等处理
    public static  int TASK_STATUS_SUCCESS=4;//成功
    public static  int TASK_STATUS_FAIL=5;//取消
    public static  int TASK_STATUS_EXCEPTION=6;//异常

    public static int CALLNACK_TYPE_INIT=0;//等待分配处理
    public static int CALLBACK_TYPE_DEAL=1;//处理回调中

    public static void initMemecache(String ip,int port) {
        TaskAllocationConifg.ip=ip;
        TaskAllocationConifg.port=port;
    }
//    private static String
}
