package com._520it.wms.service;

import com._520it.wms.domain.Product;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;

import java.util.List;

public interface IProductService {
     void save(Product product);

     void update(Product product);

     void delete(Product product);

     Product get(Long id);

     List listAll();

     PageResult query(QueryObject qo);
}
