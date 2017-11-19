package ${basePkg}.service;

import ${basePkg}.domain.${className};
import ${basePkg}.page.PageResult;
import ${basePkg}.query.QueryObject;

import java.util.List;

public interface I${className}Service {
     void save(${className} ${objectName});

     void update(${className} ${objectName});

     void delete(${className} ${objectName});

     ${className} get(Long id);

     List listAll();

     PageResult query(QueryObject qo);
}
