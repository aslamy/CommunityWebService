package com.project.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Benjamin on 2015-11-06.
 */
@Entity
@Table( uniqueConstraints=
@UniqueConstraint(columnNames={"userId", "friendId"}))
@NamedQueries({
        @NamedQuery(name = "FriendShip.deleteByUserIdAndPostId", query = "delete from FriendShip f where f.user=:user and f.friend=:friend"),
        @NamedQuery(name = "FriendShip.byUserIdAndFriendId", query = "from FriendShip where user = :user and friend= :friend"),
        @NamedQuery(name = "FriendShip.updateStatus", query = "update FriendShip set status =:status where user = :user and friend= :friend"),
        @NamedQuery(name = "FriendShip.findFriendRequest", query = "from FriendShip where friend =:friend and status =:status"),
        @NamedQuery(name = "FriendShip.findAllRequest", query = "from FriendShip where user =:user order by created desc"),
})
public class FriendShip {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long friendShipId ;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId",nullable = false)
    private User user ;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "friendId",nullable = false)
    private User friend ;

    private FriendShipStatus status ;

    private Date created ;

    public FriendShipStatus getStatus() {
        return status;
    }

    public void setStatus(FriendShipStatus status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public User getFriend() {
        return friend;
    }

    public void setFriend(User friend) {
        this.friend = friend;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getFriendShipId() {
        return friendShipId;
    }

    public void setFriendShipId(long friendShipId) {
        this.friendShipId = friendShipId;
    }
}
