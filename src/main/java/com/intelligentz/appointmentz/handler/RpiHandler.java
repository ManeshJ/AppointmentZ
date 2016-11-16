package com.intelligentz.appointmentz.handler;

import com.intelligentz.appointmentz.constants.AuthorizationTypes;
import com.intelligentz.appointmentz.constants.ContentTypes;
import com.intelligentz.appointmentz.constants.URLs;
import com.intelligentz.appointmentz.exception.IdeabizException;
import com.intelligentz.appointmentz.model.RequestMethod;

/**
 * Created by lakshan on 11/12/16.
 */
public class RpiHandler {
    public String updateRpiPin(String serial, String authcode, int pin, String action) throws IdeabizException {
        String body = "{\n" +
                "\"serial\": \""+serial+"_"+String.valueOf(pin)+"\", \"auth_code\": \""+authcode+"\" , \"action\":\""+action+"\"\n" +
                "}";
        System.out.println(body);
        return new IdeaBizAPIHandler().sendAPICall(URLs.CONNECTED_DEVICE_URL, RequestMethod.POST,body,"", ContentTypes.TYPE_JSON, ContentTypes.TYPE_TEXT, AuthorizationTypes.TYPE_BEARER);
    }
}
