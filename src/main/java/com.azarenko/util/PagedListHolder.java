package com.azarenko.util;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class PagedListHolder<T> {
    boolean newPageSet;
    private List<T> list;
    private Integer pageSize;
    private int page;
    private String nameList;

    public void setNameList(String nameList) {
        this.nameList = nameList;
    }

    public PagedListHolder(List<T> list) {
        this.list = list;
        this.pageSize = 10;
        this.nameList = "list";
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

    public String getNameList() {
        return nameList;
    }

    public void setPadding(HttpServletRequest req) {
        Integer page = 0;
        try {
            page = Integer.parseInt(req.getParameter("page"));
        } catch (NumberFormatException e) {

        }
        req.setAttribute("maxPages", this.getPageCount());
        String URI = req.getRequestURI();
        req.setAttribute("currentsort", URI + "?action=page");
        if (page == null || page < 1 || page > this.getPageCount())
            page = 1;

        req.setAttribute("page", page);
        setPage(page - 1);
        req.setAttribute(getNameList(), getPageList());
    }

}

