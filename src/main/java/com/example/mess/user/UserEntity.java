package com.example.mess.user;

import jakarta.persistence.*;



@Entity
@Table(name = "Users3")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String rollNo;
    private String mess;
    private int bfCount;
    private int lunchCount;
    private int snacksCount;
    private int dinnerCount;
    private String password;

    public UserEntity() {
    }

    public UserEntity(String rollNo, String mess, int bfCount, int lunchCount, int snacksCount, int dinnerCount, String password) {
        this.rollNo = rollNo;
        this.mess = mess;
        this.bfCount = bfCount;
        this.lunchCount = lunchCount;
        this.snacksCount = snacksCount;
        this.dinnerCount = dinnerCount;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }

    public int getBfCount() {
        return bfCount;
    }

    public void setBfCount(int bfCount) {
        this.bfCount = bfCount;
    }

    public int getLunchCount() {
        return lunchCount;
    }

    public void setLunchCount(int lunchCount) {
        this.lunchCount = lunchCount;
    }

    public int getSnacksCount() {
        return snacksCount;
    }

    public void setSnacksCount(int snacksCount) {
        this.snacksCount = snacksCount;
    }

    public int getDinnerCount() {
        return dinnerCount;
    }

    public void setDinnerCount(int dinnerCount) {
        this.dinnerCount = dinnerCount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
