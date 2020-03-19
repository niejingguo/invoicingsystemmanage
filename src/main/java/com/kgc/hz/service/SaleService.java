package com.kgc.hz.service;

import com.kgc.hz.entity.*;

import java.util.List;

/**
 * @ClassName SaleService
 * @Description: TODO
 * @Author NieJingGuo
 * @Date 2020/3/13 15:19
 * @Version V1.0
 **/
public interface SaleService {
    List<Product> getProList();
    ResponseResult addSale(Sale sale);

    SalePage getSales(SaleParameter paramater);

    int getProQua(int id);

    ResponseResult delSale(int id);
}
