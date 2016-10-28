package com.project.service;

import com.project.entities.FriendShip;
import com.project.entities.Post;
import com.project.model.FriendShipModel;
import com.project.model.PostModel;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Benjamin on 2015-11-16.
 */
@Path("/users/{userId}/friends")
public class FriendShipResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{friendId}")
    public FriendShip getFriend(@PathParam("userId") long userId, @PathParam("friendId") long firendId){
        FriendShipModel friendShipModel = new FriendShipModel();
        FriendShip friendShip = friendShipModel.getFriendShip(userId,firendId);
        if(friendShip!=null){
            friendShip.setUser(null);
            friendShip.setFriend(null);
        }
        return friendShip;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<FriendShip> getFriends(@PathParam("userId") long userId){
        FriendShipModel friendShipModel = new FriendShipModel();
        List<FriendShip> friendShips = friendShipModel.getFriendShips(userId);
        if(friendShips!=null){
            for (FriendShip friendShip: friendShips) {
                friendShip.setUser(null);
                friendShip.getFriend().setPassword(null);
            }
        }
        return friendShips;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public FriendShip addFriend(@PathParam("userId") long userId, FriendShip friendShip){
        FriendShipModel friendShipModel = new FriendShipModel();
        FriendShip friendShipp = friendShipModel.addFriendShip(userId,friendShip);
        return friendShipp;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public FriendShip updateFriend(@PathParam("userId") long userId, FriendShip friendShip){
        FriendShipModel friendShipModel = new FriendShipModel();
        FriendShip friendShipp = friendShipModel.updateFriendShip(userId,friendShip);
        return friendShipp;
    }

    @DELETE
    @Path("{friendId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public FriendShip deleteFriend(@PathParam("userId") long userId, @PathParam("friendId") long friendId){
        FriendShipModel friendShipModel = new FriendShipModel();
        FriendShip friendShipp = friendShipModel.deleteFriendShip(userId,friendId);
        return friendShipp;
    }
}
