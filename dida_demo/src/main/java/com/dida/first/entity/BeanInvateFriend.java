package com.dida.first.entity;

/**
 * Created by Administrator on 2015-11-10.
 */
public class BeanInvateFriend {
    private int userID;
    private String userName;
    private String userGander;
    private boolean isChecked;

    public boolean isChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserGander() {
        return userGander;
    }

    public void setUserGander(String userGander) {
        this.userGander = userGander;
    }

    public BeanInvateFriend(int userID){
        this.userID=userID;
    }

    @Override
    public String toString() {
        return "BeanInvateFriend{" +
                "userID=" + userID +
                ", userName='" + userName + '\'' +
                ", userGander='" + userGander + '\'' +
                ", isChecked=" + isChecked +
                '}';
    }
}
