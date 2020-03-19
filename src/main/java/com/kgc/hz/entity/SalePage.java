package com.kgc.hz.entity;

import java.util.List;

/**
 * @ClassName SalePage
 * @Description: TODO
 * @Author NieJingGuo
 * @Date 2020/3/17 20:28
 * @Version V1.0
 **/
public class SalePage extends Page {
    private List<Sale> saleList;

    public List<Sale> getSaleList() {
        return saleList;
    }

    public void setSaleList(List<Sale> saleList) {
        this.saleList = saleList;
    }
}
