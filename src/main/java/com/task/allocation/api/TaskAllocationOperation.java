package com.task.allocation.api;

import com.task.allocation.exception.TaskAllocationException;
import com.task.allocation.qo.AllocationTaskQo;

import java.util.List;

/**
 * Created by Administrator on 2017/8/14.
 */
public interface TaskAllocationOperation {
    public void run(String params);
    public List<Object> getListTasks(String params);
    public void getListTasksCallback(AllocationTaskQo allocationTaskQo);//回调
    public void dealSingle(Object obj) throws TaskAllocationException;
}
