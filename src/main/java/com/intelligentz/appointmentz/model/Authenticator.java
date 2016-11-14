package com.intelligentz.appointmentz.model;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.intelligentz.appointmentz.constants.AuthorizationTypes;
import com.intelligentz.appointmentz.constants.ContentTypes;
import com.intelligentz.appointmentz.exception.IdeabizException;
import com.intelligentz.appointmentz.model.Data.DataInterface;
import org.apache.commons.codec.binary.Base64;

/**
 * Created by anushkas on 10/19/15.
 */
public class Authenticator {


    String accessToken;

     DataInterface dataInterface;
     String body="?grant_type=refresh_token&refresh_token=";

     String urlPara ="token";
     RequestHandller requestHandller;
     ApplicationInformation applicationInformation;

    public Authenticator(DataInterface dataInterface) throws IdeabizException {
        this.dataInterface = dataInterface;
        applicationInformation = dataInterface.loadPropFile();
        System.out.println(applicationInformation.getTokenAPIURL());
        setAccessToken(applicationInformation);
        requestHandller = new RequestHandller();
     }

    public  boolean renewToken() throws IdeabizException {

        String apiCallurl = applicationInformation.getTokenAPIURL()+urlPara;
        String bodyFull = body+applicationInformation.getRefreshToken()+"&scope=PRODUCTION";
        String enc = base64Enc(applicationInformation.getConsumerKey(),applicationInformation.getConsumerSecret());
        String response = "";
        try {
        JsonParser parser = new JsonParser();
        response = requestHandller.putHTTP(apiCallurl,bodyFull,enc, ContentTypes.TYPE_FORM_URL_ENCODED, ContentTypes.TYPE_JSON, AuthorizationTypes.TYPE_BASIC);
        JsonObject tokenOut = (JsonObject)parser.parse(response);

            applicationInformation.setRefreshToken(tokenOut.get("refresh_token").toString().replace("\"", ""));
            applicationInformation.setAccessToken(tokenOut.get("access_token").toString().replace("\"", ""));
        }
        catch (Exception e){

            System.out.println("Response: "+ response);
            throw new IdeabizException("Unable to refresh token: "+e.getMessage());

        }
       return dataInterface.saveTokenFile(applicationInformation);

    }

    public String base64Enc(String consumerKey, String consumerSecret){
        String enc = consumerKey+":"+consumerSecret;
        byte[] encodedBytes = Base64.encodeBase64(enc.getBytes());
        return new String(encodedBytes);
    }

    public String getAccessToken(){
        return  accessToken;
    }
    public void setAccessToken(ApplicationInformation applicationInformation) {
        this.accessToken = applicationInformation.getAccessToken();
    }


}
