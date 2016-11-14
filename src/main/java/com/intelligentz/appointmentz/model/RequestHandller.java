package com.intelligentz.appointmentz.model;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;


public class RequestHandller {
    public String getHTTP (String url, String key, String contenctType, String accpet, String authorizationType){

       return getResponse(url,"",RequestMethod.GET,key,"{}", contenctType,accpet,authorizationType);
    }
    public String postHTTP (String url, String body, String key, String jsonBody, String contenctType, String accpet, String authorizationType){
       return getResponse(url,body,RequestMethod.POST,key,jsonBody, contenctType,accpet,authorizationType);
    }
    public String putHTTP (String url, String body, String key, String contenctType, String accpet, String authorizationType){
        return getResponse(url,body,RequestMethod.PUT,key,"{}", contenctType,accpet,authorizationType);
    }

    private String getResponse(String url, String body, RequestMethod requestMethod, String key, String jsonBody,String contentType, String accept, String authorizationType ){
        String output ="{}" ;
        try {

            HttpClient client = HttpClientBuilder.create().build();
            HttpResponse response;
            switch (requestMethod) {
                case GET:
                    HttpGet getrequest = new HttpGet(url);
                    getrequest.addHeader("Content-Type",contentType);
                    getrequest.addHeader("Authorization:",authorizationType+key);
                    response = client.execute(getrequest);
                    output = IOUtils.toString(response.getEntity().getContent());
                    break;
                case POST:
                    HttpPost postrequest = new HttpPost(url);
                    postrequest.addHeader("Content-Type",contentType);
                    postrequest.addHeader("Accept", accept);
                    postrequest.addHeader("Authorization",authorizationType+key);
                    StringEntity param = new StringEntity(jsonBody);
                    postrequest.setEntity(param);
                    response = client.execute(postrequest);
                    output = IOUtils.toString(response.getEntity().getContent());
                    break;
                case PUT:
                    HttpPost putrequest = new HttpPost(url+body);
                    putrequest.addHeader("Content-Type",contentType);
                    //putrequest.addHeader("Accept", "application/json");
                    putrequest.addHeader("Authorization",authorizationType+key);
                    //StringEntity params = new StringEntity(jsonBody);
                    //putrequest.setEntity(params);
                    response = client.execute(putrequest);
                    output = IOUtils.toString(response.getEntity().getContent());
                    break;
            }


        } catch (Exception e) {

            e.printStackTrace();

        }
        return output;
    }




}
