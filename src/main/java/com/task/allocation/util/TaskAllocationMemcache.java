package com.task.allocation.util;

/**
 * Created by Administrator on 2017/8/13.
 */
public class TaskAllocationMemcache {
    /**
     * 设置parans 值
     * @param taskAllocationId
     * @param params
     */
    public static void setParams(String taskAllocationId,String params){
        String key=taskAllocationId+"_params";
        MemCachedUtil.setValue(key,params,60*1000*60*60);
    }
    public static String getParams(String taskAllocationId){
        String key=taskAllocationId+"_params";
        return (String) MemCachedUtil.getValue(key);
    }

    /**
     * 状态
     * @param taskAllocationId
     * @param status
     */
    public static void setStatus(String taskAllocationId,int status) {
        MemCachedUtil.setValue(taskAllocationId,status,60*1000*60*60);
    }
    public static int getStatus(String taskAllocationId) {
        return (Integer) MemCachedUtil.getValue(taskAllocationId);
    }

//    public static
}
