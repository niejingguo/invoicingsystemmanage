package com.kgc.hz.service.impl;

import com.kgc.hz.dao.SaleDao;
import com.kgc.hz.entity.*;
import com.kgc.hz.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @ClassName SaleServiceImpl
 * @Description: TODO
 * @Author NieJingGuo
 * @Date 2020/3/13 15:20
 * @Version V1.0
 **/
@Service("saleService")
public class SaleServiceImpl implements SaleService {
    /**
     * @Author NieJingGuo
     * @Description TODO 
     * @Date 15:33 2020/3/13
     * @Param 
     * @return 
     **/
    @Autowired
    private SaleDao saleDao;
    /**
     * @Author NieJingGuo
     * @Description TODO 
     * @Date 15:29 2020/3/13
     * @Param []
     * @return java.util.List<com.kgc.hz.entity.Product>
     **/
    @Override
    public List<Product> getProList() {
        return saleDao.getProList();
    }
    /**
     * @Author NieJingGuo
     * @Description TODO 添加销售
     * @Date 10:30 2020/3/17
     * @Param [sale]
     * @return com.kgc.hz.entity.ResponseResult
     **/
    @Transactional
    @Override
    public ResponseResult addSale(Sale sale) {
        ResponseResult rs=new ResponseResult();
        if (StringUtils.isEmpty(sale)){
            rs.setFlag(0);
            rs.setDate("传参为空");
        }
        if (StringUtils.isEmpty(sale.getPrice())){
            rs.setFlag(1);
            rs.setDate("销售单价为空");
        }
        if (StringUtils.isEmpty(sale.getProductId())){
            rs.setFlag(2);
            rs.setDate("销售商品为空");
        }
        if (StringUtils.isEmpty(sale.getQuantity())){
            rs.setFlag(3);
            rs.setDate("销售数量为空");
        }
        int count=saleDao.selectProduct(sale.getProductId());
        if (count<sale.getQuantity()){
            rs.setFlag(4);
            rs.setDate("商品库存不够");
        }else {
            Product product=new Product();
            product.setId(sale.getProductId());
            product.setQuantity(sale.getQuantity());
            sale.setTotalPrice(sale.getQuantity()*sale.getPrice());
            if (saleDao.updateProduct(product)>0 && saleDao.addSaleReCord(sale)>0){
                rs.setFlag(5);
                rs.setDate("销售商品成功");
                rs.setResult(true);
            }
        }

        return rs;
    }

    @Override
    public SalePage getSales(SaleParameter parameter) {
        if (StringUtils.isEmpty(parameter)){
            parameter=new SaleParameter();
        }
        parameter.setPageIndex((parameter.getCurPage()-1)*parameter.getPageSize());
       //判断条件
        if (!StringUtils.isEmpty(parameter.getOrderBy())){
            if (parameter.getOrderBy().equals("saleDate")){
                parameter.setOrderBy("s.saleDate");
            }
            if (parameter.getOrderBy().equals("totalPrice")){
                parameter.setOrderBy("s.totalPrice");
            }
        }else {
            parameter.setOrderBy("s.saleDate");
        }
        if (!StringUtils.isEmpty(parameter.getOrderType())){
            if (parameter.getOrderType().equals("DESC")){
                parameter.setOrderType("DESC");
            }
            if (parameter.getOrderType().equals("ASC")){
                parameter.setOrderType("ASC");
            }
        }else {
            parameter.setOrderType("DESC");
        }
        int count=saleDao.getSaleCount(parameter);
        List<Sale> list=saleDao.getSalePage(parameter);
        SalePage page=new SalePage();
        page.setTotalCount(count);
        page.setSaleList(list);
        page.setCurPage(parameter.getCurPage());
        page.setPageSize(parameter.getPageSize());
        page.setTotalPageCount((count/page.getPageSize())+((count%page.getPageSize())==0?0:1));
if (parameter.getOrderBy().equals("s.saleDate")){
    page.setOrderBy("saleDate");
}
        if (parameter.getOrderBy().equals("s.totalPrice")){
            page.setOrderBy("totalPrice");
        }
        page.setOrderType(parameter.getOrderType());
        return page;
    }

    @Override
    public int getProQua(int id) {
        return saleDao.selectProduct(id);
    }

    @Override
    public ResponseResult delSale(int id) {
        int flag = saleDao.delSale(id);
        ResponseResult rs=new ResponseResult();
        if (flag>0) {
            rs.setResult(true);
            rs.setFlag(1);
        }else{
            rs.setFlag(2);
        }
        return rs;
    }

}
