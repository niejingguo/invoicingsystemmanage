package com.kgc.hz.controller;

import com.alibaba.fastjson.JSON;
import com.kgc.hz.entity.ResponseResult;
import com.kgc.hz.entity.UserInfo;
import com.kgc.hz.service.UserInfoService;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @ClassName UserController
 * @Description: TODO
 * @Author NieJingGuo
 * @Date 2020/3/12 17:26
 * @Version V1.0
 **/
@Controller
public class UserController {
    //依赖注入Service层
    @Resource
    private UserInfoService userInfoService;
    @ResponseBody
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Object loginuSER(UserInfo userInfo, HttpSession session) {
        ResponseResult responseResult = userInfoService.loginUser(userInfo);

        if (!StringUtils.isEmpty(responseResult)) {
            if (responseResult.getResult() == true) {
                UserInfo user = (UserInfo) responseResult.getDate();
                if (!StringUtils.isEmpty(user)) {
                    session.setAttribute("user", user);
                }
            }
        }
        return JSON.toJSONString(responseResult);
    }
/**
 * @Author NieJingGuo
 * @Description TODO 退出登陆
 * @Date 11:11 2020/3/13
 * @Param [session]
 * @return java.lang.Object
 **/
    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public Object loginOut(HttpSession session){
        UserInfo userInfo= (UserInfo) session.getAttribute("user");
    if (!StringUtils.isEmpty(userInfo)){
        session.removeAttribute("user");
    }
    ResponseResult rs=new ResponseResult();
    rs.setFlag(1);
    rs.setResult(true);
    return JSON.toJSONString(rs);
    }

}
