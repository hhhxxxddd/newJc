package com.jinchi.common.dto;

import java.util.ArrayList;
import java.util.List;

public class Page<T> {
    long total;
    int size;
    int page;
    List<T> list;

    public Page(List<T> list, int page, int size){
        total = list.size();
        this.list = new ArrayList<>();
        for(int i=(page-1)*size;i<page*size&&i<total;i++) {
            this.list.add(list.get(i));
        }
        this.size = size;
        this.page = page;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
