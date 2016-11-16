package com.intelligentz.appointmentz.rest_resource;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.intelligentz.appointmentz.controllers.RpiController;
import com.intelligentz.appointmentz.controllers.SessionController;
import com.intelligentz.appointmentz.exception.IdeabizException;
import com.intelligentz.appointmentz.model.Session;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

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
        String btn_serial = jsonObject.get("mac").getAsString();
        Session session;//request.get("serial").getAsString();
        try {
            session = new SessionController().getButtonSession(btn_serial);
            if (session != null){
                new SessionController().increaseSessionNumber(session);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IdeabizException e) {
            e.printStackTrace();
        }
//        try {
//            String result = new RpiHandler().updateRpiPin(serial,authcode,pin, RpiPinActions.ACTION_ON);
//        } catch (IdeaBizException e) {
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
//        }
        return Response.status(Response.Status.OK).build();
    }

    @Produces(MediaType.TEXT_PLAIN)
    @GET
    @Path("/poll2/{serial}")
    public Response pollget2(@PathParam("serial") String serial) {
        int current_no = 0;
        try {
            current_no = new RpiController().getCurrentNumber(serial);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Response.status(Response.Status.OK).entity(current_no).build();
    }

    @Produces(MediaType.APPLICATION_JSON)
    @GET
    @Path("/reset/{serial}")
    public String pollget(@PathParam("serial") String serial) {
        try {
            new RpiController().setRpiCurrentNumber(serial,0);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IdeabizException e) {
            e.printStackTrace();
        }
        return "reset";
    }
}
