package com.kgc.hz.service;

import com.kgc.hz.entity.ResponseResult;
import com.kgc.hz.entity.UserInfo;

/**
 * @ClassName UserInfoService
 * @Description: TODO 用户信息业务逻辑层
 * @Author Administrator
 * @Date 2020/3/12 11:56
 * @Version V1.0
 **/
public interface UserInfoService {
    /**
     * @Author NieJingGuo
     * @Description TODO 登陆接口
     * @Date 16:46 2020/3/12
     * @Param [userInfo]
     * @return com.kgc.hz.entity.ResponseResult
     **/
    ResponseResult loginUser(UserInfo userInfo);
}
