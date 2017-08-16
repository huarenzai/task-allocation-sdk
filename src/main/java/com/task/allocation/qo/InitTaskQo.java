package com.task.allocation.qo;

import java.io.Serializable;

/**
 * 任务初始化qo
 * Created by Administrator on 2017/8/11.
 */
public class InitTaskQo implements Serializable {
    private int taskType;//任务类型
    private String operationUserId;//任务操作人
    private String params;//请求参数
    private String taskSignKey;//任务唯一标识
    private String enterpriseId;//
    private String taskMqKey;//任务队列key

    public int getTaskType() {
        return taskType;
    }

    public void setTaskType(int taskType) {
        this.taskType = taskType;
    }

    public String getOperationUserId() {
        return operationUserId;
    }

    public void setOperationUserId(String operationUserId) {
        this.operationUserId = operationUserId;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getTaskSignKey() {
        return taskSignKey;
    }

    public void setTaskSignKey(String taskSignKey) {
        this.taskSignKey = taskSignKey;
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getTaskMqKey() {
        return taskMqKey;
    }

    public void setTaskMqKey(String taskMqKey) {
        this.taskMqKey = taskMqKey;
    }
}
