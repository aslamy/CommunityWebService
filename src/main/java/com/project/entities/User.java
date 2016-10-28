package com.project.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user")

@NamedQueries({

        @NamedQuery(name = "User.findAllUsers", query = "from User"),
        @NamedQuery(name = "User.byId", query = "from User where userId = :userId"),
        @NamedQuery(name = "User.byUserName", query = "from User where userName = :username"),
        @NamedQuery(name = "User.byNameAndPass", query = "from User where userName = :username and password = :password"),
        @NamedQuery(name = "User.NameBeginsWith", query = "from User where userName like :username"),
        @NamedQuery(name = "User.Id", query = "select userId from User where userName = :username and password = :password"),


})
@XmlRootElement
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userId")
    private long userId;

    @Column(unique = true, nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;

    private String firstName;

    private String lastName;


    private String email;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }



    @Override
    public String toString() {
        return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
    }

}
