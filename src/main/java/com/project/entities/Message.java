package com.project.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by Benjamin on 2015-11-06.
 */
@Entity
@Table(name = "message")

@NamedQueries({
        @NamedQuery(name = "Message.findAllMessages", query = "from Message order by created asc "),
        @NamedQuery(name = "Message.findAllMessagesByUser", query = "from Message m where m.user=:user order by created asc "),
        @NamedQuery(name = "Message.findAllMessagesInConversation", query = "from Message where (user = :user and receiver = :receiver) or (user=:receiver and receiver=:user) order by created asc "),
        @NamedQuery(name = "Message.findMessagesByUserAndReceiver", query = "from Message where user = :user or receiver =:receiver order by created desc "),
})
@XmlRootElement
public class Message {

    public Message() {
    }

    public Message(String text, String subtitle, Date created, MessageStatus status, User user, User receiver) {
        this.text = text;
        this.subtitle = subtitle;
        this.created = created;
        this.status = status;
        this.user = user;
        this.receiver = receiver;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  long messageId ;

    private String text ;

    private String subtitle ;

    private Date created;

    private MessageStatus status;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId",nullable = false)
    private User user ;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "receiverId",nullable = false)
    private User receiver ;

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public Date getCreated() {
        return this.created;
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

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User userTo) {
        this.receiver = userTo;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public MessageStatus getStatus() {
        return status;
    }

    public void setStatus(MessageStatus status) {
        this.status = status;
    }
}
