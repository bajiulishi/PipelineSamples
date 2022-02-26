package com.example.lib.designpattern.chain;

public class JSONFilter implements Filter {
    @Override
    public void doFilter(Request request, Response response, FilterChain chain) {
        request.request += " JSONFilter";

        // 通过FilterChain的下标index, 执行下一个Filter
        chain.doFilter(request, response);
        response.response += " JSONFilter";
    }
}
