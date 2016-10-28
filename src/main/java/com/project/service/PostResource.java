package com.project.service;

import com.project.entities.Post;
import com.project.entities.User;
import com.project.model.PostModel;
import com.project.model.UserModel;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Benjamin on 2015-11-15.
 */
@Path("/users/{userId}/posts")
public class PostResource {



    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Post> getUsers(@PathParam("userId") long userId){

        PostModel postModel = new PostModel();
        List<Post> postList = postModel.getPosts(userId);
        if(postList!=null){
            for (Post post: postList) {
                post.setUser(null);
            }
        }

        return postList;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Post addPost(@PathParam("userId") long userId, Post pos){
        PostModel postModel = new PostModel();
        Post post = postModel.insertPost(userId,pos);
        if(post!=null){
            post.setUser(null);
            return post ;
        }
        return post;
    }

    @DELETE
    @Path("{postId}")
    public Post deletePost(@PathParam("userId") long userId, @PathParam("postId") long postId){
        PostModel postModel = new PostModel();
        Post post = postModel.deletePost(userId,postId);
        if(post!=null){
            post.setUser(null);
        }
        return post;
    }
}
