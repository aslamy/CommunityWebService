package com.project.model;

import com.project.entities.User;
import com.project.util.HibernateUtil;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Benjamin on 2015-11-13.
 */
public class UserModel {

    public List<User> getUsers(){

        EntityManager em;
        em = HibernateUtil.getEntityManager();
        List<User> userList;
            em.getTransaction().begin();
            userList = (List<User>) em.createNamedQuery("User.findAllUsers").getResultList();
            em.getTransaction().commit();
            return userList;
    }

    public User getUser(long userId){

        EntityManager em;
        em = HibernateUtil.getEntityManager();
       User user ;
        try {
            em.getTransaction().begin();
            user = (User) em.createNamedQuery("User.byId").setParameter("userId",userId).getSingleResult();
            em.getTransaction().commit();
            return user;

        } catch (Exception e) {
            return null;

        } finally {
            em.close();
        }
    }

    public User getUser(String username){

        EntityManager em;
        em = HibernateUtil.getEntityManager();
        User user;
        try {
            em.getTransaction().begin();
            user = (User) em.createNamedQuery("User.byUserName").setParameter("username",username).getSingleResult();
            em.getTransaction().commit();
            return user;

        } catch (Exception e) {
            return null;

        } finally {
            em.close();
        }
    }

    public List<User> getUser(String username,String password){

        EntityManager em;
        em = HibernateUtil.getEntityManager();
        List<User> userList;
        try {
            em.getTransaction().begin();
            userList = (List<User>) em.createNamedQuery("User.byNameAndPass").setParameter("username",username).setParameter("password",password).getResultList();
            em.getTransaction().commit();
            return userList;

        } catch (Exception e) {
            return null;

        } finally {
            em.close();
        }
    }


    public User getUserByIdAndPass(String username,String password){

        EntityManager em;
        em = HibernateUtil.getEntityManager();
        User user;
        try {
            em.getTransaction().begin();
            user = (User) em.createNamedQuery("User.byNameAndPass").setParameter("username",username).setParameter("password",password).getSingleResult();
            em.getTransaction().commit();
            return user;

        } catch (Exception e) {
            return null;

        } finally {
            em.close();
        }
    }


    public User addUser(User user){
        EntityManager em;
        em = HibernateUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            return user ;

        } catch (Exception e) {
            return null ;
        } finally {
            em.close();
        }
    }

    public ArrayList<User> getAllUserBeginsWith(String username) {

        EntityManager em;
        em = HibernateUtil.getEntityManager();
        ArrayList<User> userList = new ArrayList<User>();
        try {
            em.getTransaction().begin();
            userList = (ArrayList<User>) em.createNamedQuery("User.NameBeginsWith").setParameter("username", username+"%").getResultList();
            em.getTransaction().commit();

            return userList;

        } catch (Exception e) {

            return null;

        } finally {
            em.close();
        }
    }


}
