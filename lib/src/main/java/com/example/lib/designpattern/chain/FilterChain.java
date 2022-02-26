package com.example.lib.designpattern.chain;

import java.util.ArrayList;
import java.util.List;

public class FilterChain {
    private List<Filter> filters = new ArrayList<>();

    /**
     * 实现寻找下一个filter
     */
    private int index;

    public void addFilter(Filter filter) {
        filters.add(filter);
    }

    public void doFilter(Request request, Response response) {
        if (index >= filters.size()) {
            return;
        }

        filters.get(index++).doFilter(request, response, this);
    }
}
