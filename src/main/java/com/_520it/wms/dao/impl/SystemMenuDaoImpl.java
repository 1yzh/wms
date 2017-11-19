package com._520it.wms.dao.impl;

import com._520it.wms.dao.ISystemMenuDAO;
import com._520it.wms.domain.Role;
import com._520it.wms.domain.SystemMenu;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class SystemMenuDaoImpl extends GenericDaoImpl<SystemMenu> implements ISystemMenuDAO {
    public List listMenus() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT obj FROM SystemMenu obj WHERE obj.parent IS NOT NULL ");
        return query.list();
    }

    public List<SystemMenu> queryMenusByParentSn(String parentSn) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select obj from SystemMenu obj where obj.parent.sn=?")
                .setParameter(0, parentSn)
                .list();
    }

    public List<SystemMenu> queryMenusByParentSn(String parentSn, List<Role> roles) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select m from Role r join r.menus m where m.parent.sn=:parentSn and r in :roles")
                .setParameter("parentSn", parentSn)
                .setParameterList("roles", roles)
                .list();
    }
}

