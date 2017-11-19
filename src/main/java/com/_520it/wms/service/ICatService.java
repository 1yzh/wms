package com._520it.wms.service;

import com._520it.wms.domain.Cat;
import com._520it.wms.page.PageResult;
import com._520it.wms.query.QueryObject;

import java.util.List;

public interface ICatService {
     void save(Cat cat);

     void update(Cat cat);

     void delete(Cat cat);

     Cat get(Long id);

     List listAll();

     PageResult query(QueryObject qo);
}
