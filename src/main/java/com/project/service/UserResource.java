package com.project.service;

import com.project.entities.User;
import com.project.model.UserModel;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Benjamin on 2015-11-13.
 */
@Path("/users")
public class UserResource {


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getUsers(@QueryParam("username") String username, @QueryParam("password") String password){
        UserModel userModel = new UserModel();
        List<User> userList = new ArrayList<>();
        if(username!=null && password != null){
            userList = userModel.getUser(username,password);
        }else if(username!=null){
            userList = userModel.getAllUserBeginsWith(username);
        }else{
            userList = userModel.getUsers() ;
        }
        for (User user:userList) {
            user.setPassword(null);
        }
        return userList;
    }



    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User addUser(User user){
        UserModel userModel = new UserModel();
        return userModel.addUser(user);
    }



    @GET
    @Path("/{userParam}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@PathParam("userParam") String userInParam){
        UserModel userModel = new UserModel();
        try {
            long userId = Long.valueOf(userInParam);
            User user = userModel.getUser(userId);
            if(user!=null) {
                user.setPassword(null);
            }
            return user ;
        }catch(NumberFormatException e){
            User user = userModel.getUser(userInParam) ;
            if(user!=null) {
                user.setPassword(null);
            }
            return user ;
        }
    }
}
