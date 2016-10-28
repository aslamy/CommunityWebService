package com.project.service;

import com.project.entities.Message;
import com.project.entities.Post;
import com.project.model.MessageModel;
import com.project.model.PostModel;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Benjamin on 2015-11-17.
 */

@Path("/users/{userId}/messages")
public class MessageResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Message> getMessages(@PathParam("userId") long userId){

        MessageModel messageModel = new MessageModel();
        List<Message> messageList = messageModel.getMessages(userId);
        if(messageList!=null){
            for (Message message:messageList) {
                message.setUser(null);
                message.setReceiver(null);
            }
        }
        return messageList;
    }


    @GET
    @Path("/receiver/{receiverId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Message> getMessagesRelatedToReceiver(@PathParam("userId") long userId, @PathParam("receiverId") long receiverId){

        MessageModel messageModel = new MessageModel();
        List<Message> messageList = messageModel.getMessagesRelatedToReceiver(userId,receiverId);
        if(messageList!=null){
            for (Message message:messageList) {
                message.setUser(null);
                message.setReceiver(null);
            }
        }
        return messageList;
    }

    @GET
    @Path("/receiver/{receiverId}/direction/both")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Message> getMessagesRelatedToReceiverBothDirection(@PathParam("userId") long userId, @PathParam("receiverId") long receiverId){

        MessageModel messageModel = new MessageModel();
        List<Message> messageList = messageModel.getMessagesRelatedToReceiverBothDirection(userId,receiverId);
        if(messageList!=null){
            for (Message message:messageList) {
                message.getUser().setPassword(null);
                message.getReceiver().setPassword(null);
            }
        }
        return messageList;
    }

    @GET
    @Path("/direction/both")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Message> getMessagesBothDirection(@PathParam("userId") long userId){

        MessageModel messageModel = new MessageModel();
        List<Message> messageList = messageModel.getMessagesBothDirection(userId);
        if(messageList!=null){
            for (Message message:messageList) {
                message.getUser().setPassword(null);
                message.getReceiver().setPassword(null);
            }
        }
        return messageList;
    }

    @Path("/{receiverId}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Message addMessage(@PathParam("userId") long userId, @PathParam("receiverId") long receiverId, Message message){
       MessageModel messageModel = new MessageModel();
        Message msg= messageModel.insertMessage(userId,receiverId,message);
        if(msg!=null){
            msg.setUser(null);
            return msg ;
        }
        return msg;
    }
}


