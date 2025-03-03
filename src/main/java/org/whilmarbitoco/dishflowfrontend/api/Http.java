package org.whilmarbitoco.dishflowfrontend.api;

import java.net.http.HttpRequest;

public interface Http {

    public String get(String endpoint) throws Exception;

    public String post(String endpoint, String jsonBody) throws Exception;

    public String put(String endpoint, String jsonBody) throws Exception;

    public String delete(String endpoint) throws Exception;


}
