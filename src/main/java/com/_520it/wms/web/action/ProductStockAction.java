package com._520it.wms.web.action;

import com._520it.wms.query.ProductStockQueryObject;
import com._520it.wms.service.IBrandService;
import com._520it.wms.service.IDepotService;
import com._520it.wms.service.IProductStockService;
import com._520it.wms.util.RequiredPermission;
import lombok.Getter;
import lombok.Setter;

public class ProductStockAction extends BaseAction {
    @Setter
    IProductStockService productStockService;
    @Setter
    IDepotService depotService;
    @Setter
    IBrandService brandService;
    @Getter
    ProductStockQueryObject qo = new ProductStockQueryObject();
    @Override
    @RequiredPermission("及时库存列表")
    public String execute() throws Exception {
            putContext("pageResult", productStockService.query(qo));
            putContext("brands",brandService.listAll());
            putContext("depots",depotService.listAll());
        return LIST;
    }
}
