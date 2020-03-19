package com.kgc.hz.service.impl;

import com.alibaba.fastjson.JSON;
import com.kgc.hz.dao.UserInfoDao;
import com.kgc.hz.entity.ResponseResult;
import com.kgc.hz.entity.UserInfo;
import com.kgc.hz.service.UserInfoService;
import com.kgc.hz.util.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * @ClassName UserInfoServiceImpl
 * @Description: TODO
 * @Author NieJingGuo
 * @Date 2020/3/12 16:48
 * @Version V1.0
 **/
@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {
    @Resource
    private UserInfoDao userInfoDao;

    @Override
    public ResponseResult loginUser(UserInfo userInfo) {
        ResponseResult rs = new ResponseResult();
        if (StringUtils.isEmpty(userInfo)) {
            rs.setFlag(0);
            rs.setDate("传参为空");
        }
        if (StringUtils.isEmpty(userInfo.getUserName())) {
            rs.setFlag(1);
            rs.setDate("传参用户名为空");
        }
        if (StringUtils.isEmpty(userInfo.getPassword())) {
            rs.setFlag(2);
            rs.setDate("传参密码为空");
        } else {
            //调用SecurityUtils工具类进行密码加密
            userInfo.setPassword(SecurityUtils.md5Hex(userInfo.getPassword()));
        }
        //调用Dao层的登陆接口，返回一个UserInfo对象
        UserInfo user = userInfoDao.getUserInfo(userInfo);
        if (StringUtils.isEmpty(user)) {
            rs.setFlag(2);
            rs.setDate("用户名或密码不对");
        } else {
            rs.setResult(true);
            rs.setFlag(3);
            rs.setDate(user);
        }
        return rs;
    }
}
