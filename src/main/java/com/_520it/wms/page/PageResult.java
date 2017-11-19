package com._520it.wms.page;

import lombok.Getter;
import lombok.Setter;
//高级查询结果集,封装高级查询参数等
import java.util.List;
@Setter@Getter
public class PageResult {
    private Integer totalCount;//定义结果总数
    private List listData;//定义查询结果集，
    private Integer currentPage;//定义当前页
    private Integer pageSize;//定义每页多少数据
    private Integer totalPage;//总共多少页
    private Integer prevPage;//上一页
    private Integer nextPage;//下一页



    //生成构造器，外部生成时需要传入四个数据，剩余三个数据由计算得出
    public PageResult(Integer totalCount, List listData, Integer currentPage, Integer pageSize) {
        this.totalCount = totalCount;
        this.listData = listData;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalPage=this.totalCount%this.pageSize==0?this.totalCount/this.pageSize:this.totalCount/this.pageSize+1;
        this.prevPage=this.currentPage-1>=1?this.currentPage-1:1;
        this.nextPage=this.currentPage+1<this.totalPage?this.currentPage+1:this.totalPage;
    }
}
