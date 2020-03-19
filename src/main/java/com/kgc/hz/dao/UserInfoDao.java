package com.kgc.hz.dao;

import com.kgc.hz.entity.UserInfo;
import org.springframework.stereotype.Repository;

@Repository("userInfoDao")
public interface UserInfoDao {
    /**
     * @Author NieJingGuo
     * @Description TODO 
     * @Date 15:45 2020/3/13
     * @Param [userInfo]
     * @return com.kgc.hz.entity.UserInfo
     **/
    UserInfo getUserInfo(UserInfo userInfo);
}
