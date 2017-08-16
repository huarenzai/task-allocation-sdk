package com.task.allocation.qo;

import java.io.Serializable;

/**
 * 队列分配协议
 * Created by Administrator on 2017/8/13.
 */
public class AllocationTaskQo implements Serializable {
    private String taskAllocationId;
    private String params;

    public String getTaskAllocationId() {
        return taskAllocationId;
    }

    public void setTaskAllocationId(String taskAllocationId) {
        this.taskAllocationId = taskAllocationId;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }
}
