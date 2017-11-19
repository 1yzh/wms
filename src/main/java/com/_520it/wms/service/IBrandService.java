package com._520it.wms.service;

import com._520it.wms.domain.Brand;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;

import java.util.List;

public interface IBrandService {
     void save(Brand brand);

     void update(Brand brand);

     void delete(Brand brand);

     Brand get(Long id);

     List listAll();

     PageResult query(QueryObject qo);
}
