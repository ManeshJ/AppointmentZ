package com.intelligentz.appointmentz.handler;

/**
 * Created by lakshan on 11/12/16.
 */


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.intelligentz.appointmentz.constants.AuthorizationTypes;
import com.intelligentz.appointmentz.constants.ContentTypes;
import com.intelligentz.appointmentz.constants.IdeaBizConstants;
import com.intelligentz.appointmentz.constants.URLs;
import com.intelligentz.appointmentz.exception.IdeabizException;
import com.intelligentz.appointmentz.model.RequestMethod;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Malinda_07654 on 6/10/2016.
 */
public class SMSHandler {

    public static final String senderName = "APPointmentz";
    public static  final String senderAddress = "87711" ;
    public static Logger logger = Logger.getLogger(SMSHandler.class);

    //sending sms with custom sender name and address
    public String sendSMS(JsonArray msisdn, String message) throws IdeabizException{

        JsonObject outboundSMSTextMessage = new JsonObject();
        outboundSMSTextMessage.addProperty("message", message);

        JsonObject outboundSMSMessageRequest = new JsonObject();
        outboundSMSMessageRequest.add("address",msisdn);
        outboundSMSMessageRequest.addProperty("senderAddress",senderAddress);
        outboundSMSMessageRequest.add("outboundSMSTextMessage", outboundSMSTextMessage);
        outboundSMSMessageRequest.addProperty("senderName", "");

        JsonObject receiptRequest = new JsonObject();
        receiptRequest.addProperty("notifyURL","");
        receiptRequest.addProperty("callbackData","");
        outboundSMSMessageRequest.add("receiptRequest",receiptRequest);
        JsonObject requestBodyObject = new JsonObject();
        requestBodyObject.add("outboundSMSMessageRequest", outboundSMSMessageRequest);

        String reqBody  = new Gson().toJson(requestBodyObject);
        logger.info("Sending sms :"+ reqBody);
        String URL = URLs.SMS_URL + senderAddress + "/requests";
        String response = new IdeaBizAPIHandler().sendAPICall(URL, RequestMethod.POST, reqBody,"", ContentTypes.TYPE_JSON,ContentTypes.TYPE_JSON, AuthorizationTypes.TYPE_BEARER);
        logger.info("SMS Response :"+ response);
        if (response.contains("requestError")){

            response+="\n......\n Request: "+reqBody;
        }
        return response;
    }

}