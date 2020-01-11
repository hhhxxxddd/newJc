package com.jinchi.app.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-08-24 14:46
 * @description:
 **/

public class Page<T> {
    long total;
    int size;
    int page;
    List<T> list;

    public Page(int size, int page, List<T> list) {
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
