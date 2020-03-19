package com.kgc.hz.entity;

import java.io.Serializable;

/**
 * @ClassName ResponseResult
 * @Description: TODO 封装响应结果实体类
 * @Author niejingguo
 * @Date 2020/3/12 11:48
 * @Version V1.0
 * 响应结果
 **/
public class ResponseResult implements Serializable {
    private boolean result;//
    private Integer flag;//状态码
    private Object date;//数据

    public boolean getResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Object getDate() {
        return date;
    }

    public void setDate(Object date) {
        this.date = date;
    }
}
