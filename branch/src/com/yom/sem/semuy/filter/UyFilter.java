package com.yom.sem.semuy.filter;

import com.yom.sem.semuy.base.ServerBase;
import org.apache.struts2.dispatcher.Dispatcher;
import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;

import javax.servlet.FilterConfig;

public class UyFilter extends StrutsPrepareAndExecuteFilter {
    protected void postInit(Dispatcher dispatcher, FilterConfig filterConfig) {
        ServerBase server = new ServerBase();

        server.init();
    }
}
