package com.project.model;

import com.project.entities.FriendShip;
import com.project.entities.User;
import com.project.util.HibernateUtil;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Benjamin on 2015-11-16.
 */
public class FriendShipModel {

    public FriendShip getFriendShip(long userId, long friendId) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            User user = (User) em.createNamedQuery("User.byId").setParameter("userId", userId).getSingleResult();
            User friend = (User) em.createNamedQuery("User.byId").setParameter("userId", friendId).getSingleResult();
            FriendShip friendShip = (FriendShip) em.createNamedQuery("FriendShip.byUserIdAndFriendId").setParameter("user", user).setParameter("friend",friend).getSingleResult();
            return friendShip;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }


    public List<FriendShip> getFriendShips(long userId){
        EntityManager em;
        em = HibernateUtil.getEntityManager();
        List<FriendShip> friendShips;
        try {
            em.getTransaction().begin();
            User user = (User) em.createNamedQuery("User.byId").setParameter("userId",userId).getSingleResult();
            friendShips = (ArrayList<FriendShip>)em.createNamedQuery("FriendShip.findAllRequest").setParameter("user",user ).getResultList();
            em.getTransaction().commit();
            return friendShips;

        } catch (Exception e) {
            return null;

        } finally {
            em.close();
        }
    }


    public FriendShip addFriendShip(long userId, FriendShip friendShip) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            User user = (User) em.createNamedQuery("User.byId").setParameter("userId", userId).getSingleResult();
            friendShip.setUser(user);
            em.persist(friendShip);
           em.getTransaction().commit();
            return friendShip;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }


    public FriendShip updateFriendShip(long userId, FriendShip friendShip) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            User user = (User) em.createNamedQuery("User.byId").setParameter("userId", userId).getSingleResult();
            User friend = (User) em.createNamedQuery("User.byId").setParameter("userId", friendShip.getFriend().getUserId()).getSingleResult();
            em.createNamedQuery("FriendShip.updateStatus").setParameter("status",friendShip.getStatus()).setParameter("user", user).setParameter("friend",friend).executeUpdate();
            em.getTransaction().commit();
            return friendShip;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
    public FriendShip deleteFriendShip(long userId, long friendId) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            User user = (User) em.createNamedQuery("User.byId").setParameter("userId",userId).getSingleResult();
            User friend = (User) em.createNamedQuery("User.byId").setParameter("userId",friendId).getSingleResult();
            FriendShip friendShip = (FriendShip)em.createNamedQuery("FriendShip.byUserIdAndFriendId").setParameter("user",user).setParameter("friend",friend).getSingleResult();
            em.createNamedQuery("FriendShip.deleteByUserIdAndPostId").setParameter("user", user).setParameter("friend", friend).executeUpdate();
            em.getTransaction().commit();
            return friendShip;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}
