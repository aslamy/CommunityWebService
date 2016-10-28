package com.project.model;

import com.project.entities.Post;
import com.project.entities.User;
import com.project.util.HibernateUtil;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Benjamin on 2015-11-14.
 */
public class PostModel {

    public List<Post> getPosts(long userId){
        EntityManager em;
        em = HibernateUtil.getEntityManager();
        List<Post> postList = new ArrayList<>();
        try {
            em.getTransaction().begin();
            User user = (User) em.createNamedQuery("User.byId").setParameter("userId",userId).getSingleResult();
            postList = (List<Post>) em.createNamedQuery("Post.getAllUserPostes").setParameter("user",user).getResultList();
            em.getTransaction().commit();
            return postList;

        } catch (Exception e) {
            return postList;

        } finally {
            em.close();
        }
    }

    public Post insertPost(long userId,Post post){
        EntityManager em;
        em = HibernateUtil.getEntityManager();
        try {

            em.getTransaction().begin();
            User user = (User) em.createNamedQuery("User.byId").setParameter("userId",userId).getSingleResult();
            post.setUser(user);
            em.merge(post);
            em.getTransaction().commit();
            return post;

        } catch (Exception e) {
            return null;

        } finally {
            em.close();
        }
    }


    public Post deletePost(long userId, long postId){
        EntityManager em;
        em = HibernateUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            User user = (User) em.createNamedQuery("User.byId").setParameter("userId",userId).getSingleResult();
            Post post = (Post) em.createNamedQuery("Post.byIdAndUserId").setParameter("user", user).setParameter("postId", postId).getSingleResult();
            em.createNamedQuery("Post.deleteByUserIdAndPostId").setParameter("user", user).setParameter("postId", postId).executeUpdate();

            em.getTransaction().commit();
            return post ;

        } catch (Exception e) {

            return null;

        } finally {
            em.close();
        }
    }
}
