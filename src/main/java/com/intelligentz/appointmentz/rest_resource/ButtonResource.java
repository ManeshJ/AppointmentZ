package com.intelligentz.appointmentz.rest_resource;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.intelligentz.appointmentz.constants.RpiPinActions;
import com.intelligentz.appointmentz.handler.IdeaBizAPIHandler;
import com.intelligentz.appointmentz.handler.RpiHandler;
import com.intelligentz.appointmentz.model.Data.DataImp;
import com.intelligentz.appointmentz.model.RequestMethod;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by lakshan on 11/12/16.
 */
@Path("/")
public class ButtonResource {

    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @POST
    @Path("/yesButton")
    public Response get(String request) {
        // TODO: Implementation for HTTP GET request
        JsonObject jsonObject = new JsonParser().parse(request).getAsJsonObject();
        String serial = "";//request.get("serial").getAsString();
        String authcode = "";//request.get("auth_code").getAsString();
        int pin = 26;

        Gson gson = new Gson();
        String json = gson.toJson(jsonObject);
        System.out.println(json);
//        try {
//            String result = new RpiHandler().updateRpiPin(serial,authcode,pin, RpiPinActions.ACTION_ON);
//        } catch (IdeaBizException e) {
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
//        }
        return Response.status(Response.Status.OK).entity(json).build();
    }

}
