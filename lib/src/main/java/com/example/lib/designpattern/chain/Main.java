package com.example.lib.designpattern.chain;

/**
 * 实现request, 拦截HtmlFilter,JSONFilter, response拦截JSONFilter, HtmlFilter
 */
public class Main {
    public static void main(String[] args) {
        Request request = new Request();
        Response response = new Response();

        FilterChain filterChain = new FilterChain();
        filterChain.addFilter(new JSONFilter());
        filterChain.addFilter(new HtmlFilter());

        filterChain.doFilter(request, response);

        System.out.println("request: " + request.request + ", response: " + response.response);
    }
}
