package com.task.allocation.qo;

import java.io.Serializable;

/**
 * 任务回调
 * Created by Administrator on 2017/8/13.
 */
public class TaskCallbackQo implements Serializable {
    private String taskAllocationId;//任务id
    private int callbackType=0;//状态类型 0分配 1处理中
    private int taskTotal;//任务总数
    private int taskFailNum;//任务失败数
    private String reason;//原因
    private int isSuccess;//是否异常

    public int getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(int isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getTaskAllocationId() {
        return taskAllocationId;
    }

    public void setTaskAllocationId(String taskAllocationId) {
        this.taskAllocationId = taskAllocationId;
    }

    public int getCallbackType() {
        return callbackType;
    }

    public void setCallbackType(int callbackType) {
        this.callbackType = callbackType;
    }

    public int getTaskTotal() {
        return taskTotal;
    }

    public void setTaskTotal(int taskTotal) {
        this.taskTotal = taskTotal;
    }

    public int getTaskFailNum() {
        return taskFailNum;
    }

    public void setTaskFailNum(int taskFailNum) {
        this.taskFailNum = taskFailNum;
    }
}
