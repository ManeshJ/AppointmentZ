package com.intelligentz.appointmentz.controllers;

import com.google.gson.JsonArray;
import com.intelligentz.appointmentz.constants.IdeaBizConstants;
import com.intelligentz.appointmentz.exception.IdeabizException;
import com.intelligentz.appointmentz.handler.SMSHandler;
import com.intelligentz.appointmentz.handler.SubscriptionHandler;
import com.intelligentz.appointmentz.model.Session;
import com.intelligentz.appointmentz.model.SessonCustomer;

import javax.ws.rs.core.Response;
import java.util.ArrayList;

/**
 * Created by lakshan on 11/16/16.
 */

public class SMSController {
    public void sendSMS(Session session){
        ArrayList<SessonCustomer> sessonCustomers = session.getSessonCustomers();
        for (SessonCustomer sessonCustomer:
             sessonCustomers) {
            JsonArray numbers = new JsonArray();
            numbers.add(IdeaBizConstants.MSISDN_PREFIX+sessonCustomer.getMobile());
            int moreToGo = sessonCustomer.getAppointment_num() - session.getCurrent_no()-1;
            String message = "Dr. "+session.getDoctor().getName()+" Room "+session.getRoom().getRoom_number()+" at "+
                    session.getDoctor().getHospital().getHospital_name()+", current appointment number  is "+
                    String.valueOf(session.getCurrent_no()+1)+", "+String.valueOf(moreToGo)+ " more to go before you";
            try {
                if (new SubscriptionHandler().suscribe(IdeaBizConstants.MSISDN_PREFIX+sessonCustomer.getMobile())){
                    new SMSHandler().sendSMS(numbers,message);
                }else {
                    throw new IdeabizException("User Could Not be Subscribed");
                }
            } catch (IdeabizException e) {

            }
        }

    }
}
