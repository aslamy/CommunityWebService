package com.project.model;

import com.project.entities.Message;
import com.project.entities.MessageStatus;
import com.project.entities.User;
import com.project.util.HibernateUtil;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Benjamin on 2015-11-13.
 */
public class MessageModel {

    public ArrayList<Message> getMessages(long userId){
        EntityManager em;
        em = HibernateUtil.getEntityManager();
        ArrayList<Message> messageList = new ArrayList<Message>();
        try {
            em.getTransaction().begin();
            User user = (User) em.createNamedQuery("User.byId").setParameter("userId", userId).getSingleResult();
            messageList = (ArrayList<Message>) em.createNamedQuery("Message.findAllMessagesByUser").setParameter("user", user).getResultList();
            em.getTransaction().commit();
            return messageList;
        } catch (Exception e) {
            return messageList;
        } finally {
            em.close();
        }

    }


    public ArrayList<Message> getMessagesRelatedToReceiver(long userId,long receiverId){
        EntityManager em;
        em = HibernateUtil.getEntityManager();
        ArrayList<Message> messageList = new ArrayList<Message>();
        try {
            em.getTransaction().begin();
            User user = (User) em.createNamedQuery("User.byId").setParameter("userId", userId).getSingleResult();
            User receiver = (User) em.createNamedQuery("User.byId").setParameter("userId", receiverId).getSingleResult();
            messageList = (ArrayList<Message>) em.createNamedQuery("Message.findMessagesByUserAndReceiver").setParameter("user", user).setParameter("receiver",receiver).getResultList();
            em.getTransaction().commit();
            return messageList;
        } catch (Exception e) {
            return messageList;
        } finally {
            em.close();
        }

    }


    public ArrayList<Message> getMessagesRelatedToReceiverBothDirection(long userId,long receiverId){
        EntityManager em;
        em = HibernateUtil.getEntityManager();
        ArrayList<Message> messageList = new ArrayList<Message>();
        try {
            em.getTransaction().begin();
            User user = (User) em.createNamedQuery("User.byId").setParameter("userId", userId).getSingleResult();
            User receiver = (User) em.createNamedQuery("User.byId").setParameter("userId", receiverId).getSingleResult();
            messageList = (ArrayList<Message>) em.createNamedQuery("Message.findAllMessagesInConversation").setParameter("user", user).setParameter("receiver",receiver).getResultList();
            em.getTransaction().commit();
            return messageList;
        } catch (Exception e) {
            return messageList;
        } finally {
            em.close();
        }

    }

    public ArrayList<Message> getMessagesBothDirection(long userId){
        EntityManager em;
        em = HibernateUtil.getEntityManager();
        ArrayList<Message> messageList = new ArrayList<Message>();
        try {
            em.getTransaction().begin();
            User user = (User) em.createNamedQuery("User.byId").setParameter("userId", userId).getSingleResult();
            messageList = (ArrayList<Message>) em.createNamedQuery("Message.findMessagesByUserAndReceiver").setParameter("user", user).setParameter("receiver",user).getResultList();
            em.getTransaction().commit();
            return messageList;
        } catch (Exception e) {
            return messageList;
        } finally {
            em.close();
        }
    }

    public Message insertMessage(long userId,long receiverId, Message message){
        EntityManager em;
        em = HibernateUtil.getEntityManager();
        try {

            em.getTransaction().begin();
            User user = (User) em.createNamedQuery("User.byId").setParameter("userId",userId).getSingleResult();
            User receiver = (User) em.createNamedQuery("User.byId").setParameter("userId",receiverId).getSingleResult();
            message.setUser(user);
            message.setReceiver(receiver);
            em.merge(message);
            em.getTransaction().commit();
            return message;

        } catch (Exception e) {
            return null;

        } finally {
            em.close();
        }
    }

}

