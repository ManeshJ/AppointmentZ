package com.intelligentz.appointmentz.rest_resource;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.intelligentz.appointmentz.constants.IdeaBizConstants;
import com.intelligentz.appointmentz.exception.IdeabizException;
import com.intelligentz.appointmentz.handler.SMSHandler;
import com.intelligentz.appointmentz.handler.SubscriptionHandler;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by lakshan on 11/12/16.
 */
@Path("/")
public class SMSResource {

    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    @GET
    @Path("/sendSMS/{number}/{message}")
    public Response get(@PathParam("number") String number, @PathParam("message") String message) {
        String result = "";
        JsonArray numbers = new JsonArray();
        numbers.add(IdeaBizConstants.MSISDN_PREFIX+number);

        try {
            if (new SubscriptionHandler().suscribe(IdeaBizConstants.MSISDN_PREFIX+number)){
                result = new SMSHandler().sendSMS(numbers,message);
            }else {
                throw new IdeabizException("User Could Not be Subscribed");
            }

        } catch (IdeabizException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
        return Response.status(Response.Status.OK).entity(result).build();
    }

}
