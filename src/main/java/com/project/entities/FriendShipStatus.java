package com.project.entities;

/**
 * Created by Benjamin on 2015-11-06.
 */
public enum FriendShipStatus {

    FRIEND(0),REQUESTED(1),ONWAIT(2),DENIED(3);

    private int value;

    FriendShipStatus(int number) {
        this.value = number;
    }

    public int getValue() { return value; }
}
