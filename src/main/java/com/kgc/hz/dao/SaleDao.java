package com.kgc.hz.dao;

import com.kgc.hz.entity.Product;
import com.kgc.hz.entity.Sale;
import com.kgc.hz.entity.SaleParameter;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName SaleDao
 * @Description: TODO
 * @Author NieJingGuo
 * @Date 2020/3/13 15:14
 * @Version V1.0
 **/
@Repository("salDap")
public interface SaleDao {
    List<Product> getProList();
    int addSaleReCord(Sale sale);
    int updateProduct(Product product);
    int selectProduct(@Param("id") int id);

    List<Sale> getSalePage(SaleParameter parameter);

    int getSaleCount(SaleParameter parameter);

    int delSale(@Param("id") int id);
}
