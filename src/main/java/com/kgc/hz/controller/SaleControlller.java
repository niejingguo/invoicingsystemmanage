package com.kgc.hz.controller;

import com.alibaba.fastjson.JSON;
import com.kgc.hz.entity.*;
import com.kgc.hz.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @ClassName SaleControlller
 * @Description: TODO
 * @Author NieJingGuo
 * @Date 2020/3/13 15:22
 * @Version V1.0
 **/
@Controller
public class SaleControlller {
    @Autowired
    private SaleService saleService;

    /**
     * @return java.lang.String
     * @Author NieJingGuo
     * @Description TODO
     * @Date 15:33 2020/3/13
     * @Param [model]
     **/
    @RequestMapping(value = "/sale", method = RequestMethod.GET)
    public String getAddSale(Model model) {
        List<Product> list = saleService.getProList();
        model.addAttribute("proList", list);
        return "addSale";
    }

    /**
     * @return java.lang.Object
     * @Author NieJingGuo
     * @Description TODO  添加销售记录
     * @Date 10:48 2020/3/17
     * @Param [sale, session]
     **/
    @ResponseBody
    @RequestMapping(value = "/sale", method = RequestMethod.POST)
    public Object addSale(Sale sale, HttpSession session) {
        UserInfo userInfo = (UserInfo) session.getAttribute("user");
        ResponseResult rs = null;
        if (!StringUtils.isEmpty(userInfo)) {
            sale.setUserId(userInfo.getId());
            rs = saleService.addSale(sale);
        } else {
            rs = new ResponseResult();
            rs.setFlag(7);
            rs.setDate("用户没登陆");
        }
        return JSON.toJSONString(rs);
    }

    @RequestMapping(value = "/saleList", method = RequestMethod.GET)
    public String getSales(SaleParameter parameter, HttpSession session, Model model) {
        UserInfo userInfo = (UserInfo) session.getAttribute("user");
        ResponseResult rs = new ResponseResult();
        if (!StringUtils.isEmpty(userInfo)) {
            parameter.setUserName(userInfo.getUserName());
            SalePage page = saleService.getSales(parameter);
            rs.setResult(true);
            rs.setFlag(1);
            rs.setDate(page);
        } else {
            rs.setFlag(7);
            rs.setDate("用户没登陆");
        }
        model.addAttribute("responseResult", rs);
        return "sale_list";
    }
    /**
     * @Author NieJingGuo
     * @Description TODO  查看库存页面
     * @Date 11:35 2020/3/18
     * @Param [session, model]
     * @return java.lang.String
     **/
    @RequestMapping(value = "/qua",method = RequestMethod.GET)
    public String getViewQua(HttpSession session,Model model){
        UserInfo userInfo= (UserInfo) session.getAttribute("user");
        ResponseResult rs = new ResponseResult();
        if (!StringUtils.isEmpty(userInfo)){
            List<Product> list=saleService.getProList();
            model.addAttribute("proList",list);
        }
        return "view_quantity";
    }
    /**
     * @Author NieJingGuo
     * @Description TODO 根据商品ID查看该商品的库存
     * @Date 11:41 2020/3/18
     * @Param [pId, session, model]
     * @return java.lang.String
     **/
@RequestMapping(value = "/queryQua",method = RequestMethod.GET)
    public String getQuantity(@RequestParam("pId") Integer pId, HttpSession session,Model model){
        UserInfo userInfo= (UserInfo) session.getAttribute("user");
        ResponseResult rs = new ResponseResult();
        if (!StringUtils.isEmpty(userInfo)){
            int count = saleService.getProQua(pId);
            model.addAttribute("quantity",count);
        }
        return "qua";
    }
@ResponseBody
@RequestMapping(value = "/delSale",method = RequestMethod.GET)
    public Object delSale(@RequestParam("id") Integer id,HttpSession session){
        UserInfo userInfo = (UserInfo) session.getAttribute("user");
        ResponseResult rs=new ResponseResult();
        if(!StringUtils.isEmpty(userInfo)){
            rs=saleService.delSale(id);
        }else {
            rs.setFlag(7);
            rs.setDate("用户没登陆");
        }
        return JSON.toJSONString(rs);
    }
}