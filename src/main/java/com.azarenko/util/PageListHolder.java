package com.azarenko.util;

import java.util.List;

public class PageListHolder<T> {
    boolean newPageSet;
    private List<T> list;
    private Integer pageSize;
    private List<T> pageList;
    private Integer pageCount;
    private int page;

    public PageListHolder(List<T> list) {
        this.list = list;
        this.pageSize = 10;
    }

    public int getPage() {
        this.newPageSet = false;
        if (this.page >= this.getPageCount()) {
            this.page = this.getPageCount() - 1;
        }

        return this.page;
    }

    public List<T> getList() {
        return list;
    }

    public void setPage(int page) {
        this.page = page;
        this.newPageSet = true;
    }

    public List<T> getPageList() {
        return this.getList().subList(this.getFirstElementToPage(), this.getLastElementToPage() + 1);
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        if (pageSize != this.pageSize) {
            this.pageSize = pageSize;
            if (!this.newPageSet) {
                this.page = 0;
            }
        }
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    private void init() {
    }

    public Integer getPageCount() {
        float nrOfPages = (float) this.getNumberOfElements() / (float) this.getPageSize();
        return (int) (nrOfPages <= (float) ((int) nrOfPages) && (double) nrOfPages != 0.0D ? nrOfPages : nrOfPages + 1.0F);
    }

    public int getNumberOfElements() {
        return this.list.size();
    }

    public int getFirstElementToPage() {
        return this.getPageSize() * getPage();
    }

    public int getLastElementToPage() {
        int endIndex = this.getPageSize() * (this.getPage() + 1);
        int size = this.getNumberOfElements();
        return (endIndex > size ? size : endIndex) - 1;
    }

}

