package com.project.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by Benjamin on 2015-11-07.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "Post.getAllUserPostes", query = "from Post where user = :user order by created desc"),
        @NamedQuery(name = "Post.deleteByUserIdAndPostId", query = "delete from Post p where p.user=:user and p.postId=:postId"),
        @NamedQuery(name = "Post.byIdAndUserId", query = "from Post where user=:user and postId = :postId")
})
@XmlRootElement
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long postId ;

    private String title ;

    private String text ;

    private Date created;

    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
    @JoinColumn(name = "userId",nullable = false)
    private User user ;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
