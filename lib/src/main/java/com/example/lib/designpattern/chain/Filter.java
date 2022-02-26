package com.example.lib.designpattern.chain;

public interface Filter {

    /**
     * FilterChain的作用是让Filter自己处理往后执行，从而实现拦截response
     */
    void doFilter(Request request, Response response, FilterChain chain);
}
