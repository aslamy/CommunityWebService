package com.project.entities;

/**
 * Created by Benjamin on 2015-11-06.
 */
public enum MessageStatus {

    READ(1),UNREAD(2) ,DELETED(3);

    private int value;

    MessageStatus(int number) {
        this.value = number;
    }

    public int getValue() { return value; }
}
