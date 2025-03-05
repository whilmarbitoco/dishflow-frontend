package org.whilmarbitoco.dishflowfrontend.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;

abstract class Service{

    protected ObjectMapper mapper;
    protected OkHttpClient client;
    protected String endpoint;


    public Service(String endpoint) {
        mapper = new ObjectMapper();
        client = new OkHttpClient();
        this.endpoint = HttpService.BASE_URL + endpoint;
    }

}
