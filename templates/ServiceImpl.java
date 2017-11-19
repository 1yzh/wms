package ${basePkg}.service.impl;

import ${basePkg}.dao.I${className}DAO;
import ${basePkg}.domain.${className};
import ${basePkg}.domain.Permission;
import ${basePkg}.domain.Role;
import ${basePkg}.page.PageResult;
import ${basePkg}.query.QueryObject;
import ${basePkg}.service.I${className}Service;
import ${basePkg}.util.MD5;
import ${basePkg}.util.UserContext;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ${className}ServiceImpl implements I${className}Service {
    @Setter
    private I${className}DAO ${objectName}DAO;

    public void save(${className} ${objectName}) {
        ${objectName}DAO.save(${objectName});
    }

    public void update(${className} ${objectName}) {
        ${objectName}DAO.update(${objectName});
    }

    public void delete(${className} ${objectName}) {
        ${objectName}DAO.delete(${objectName});
    }

    public ${className} get(Long id) {
        return ${objectName}DAO.get(id);
    }

    public List listAll() {
        return ${objectName}DAO.listAll();
    }

    public PageResult query(QueryObject qo) {
        return ${objectName}DAO.query(qo);
    }
}
