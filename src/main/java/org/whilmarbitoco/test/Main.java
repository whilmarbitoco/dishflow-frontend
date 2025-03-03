package org.whilmarbitoco.test;

import org.whilmarbitoco.dishflowfrontend.api.HttpService;
import org.whilmarbitoco.dishflowfrontend.api.MenuService;

public class Main {

    public static void main(String[] args) throws Exception {
        MenuService m = new MenuService();

        m.all();
    }
}
