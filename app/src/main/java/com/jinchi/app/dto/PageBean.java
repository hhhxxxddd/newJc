package com.jinchi.app.dto;


import java.util.List;

/**
 * 分页类
 *
 * @param <T> 范型类、用来表示查询出来的数据类型
 * @author lanyage
 * @since 2018/12/14
 */
public class PageBean<T> {
    private Integer pageNumber = 1;                                                                                         //当前页
    private Integer pageSize = 10;                                                                                           //每页大小
    private String sortField;                                                                                           //排序字段
    private String sortType;                                                                                            //排序类型ASC、DESC
    private Integer total;                                                                                              //总数量
    private List<T> list;                                                                                               //分页查询的起始记录                                                                                       //数据
    private Integer startIndex;

    public PageBean() {
    }


    public PageBean(Integer pageNumber, Integer pageSize, Integer total, List<T> list) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.total = total;
        this.list = list;
    }

    public Integer getStartIndex() {
        return (pageNumber - 1) * pageSize;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }


    public void setList(List<T> list) {
        this.list = list;
    }

    /*————————————————
    |    获取前一页    |
     ————————————————*/
    public Integer getPrePage() {
        return this.pageNumber > 1 ? this.pageNumber - 1 : 0;
    }

    /*————————————————
    |    获取下一页    |
     ————————————————*/
    public Integer getNextPage() {
        return this.pageNumber < getPages() ? this.pageNumber + 1 : 0;
    }

    /*————————————————
    |    获取总页数    |
     ————————————————*/
    public Integer getPages() {
        return this.total % this.pageSize == 0 ? this.total / this.pageSize : this.total / this.pageSize + 1;
    }

    /*————————————————
    |    是否第一页    |
     ————————————————*/
    public Boolean isLastPage() {
        return this.total > 0 && pageNumber == getPages();
    }

    /*————————————————
    |     是否末页    |
     ————————————————*/
    public Boolean isFirstPage() {
        return this.total > 0 && pageNumber == 1;
    }
}
