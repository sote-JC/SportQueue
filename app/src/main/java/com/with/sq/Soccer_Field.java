package com.with.sq;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity
public class Soccer_Field {
    @PrimaryKey(autoGenerate = true)
    private int no = 0;

    private String GK;
    private String CF;
    private String LW;
    private String RW;
    private String SS;
    private String AM;
    private String CM;
    private String DM;
    private String LWB;
    private String RWB;
    private String CB;

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public void setGK(String GK) {
        this.GK = GK;
    }

    public void setCF(String CF) {
        this.CF = CF;
    }

    public void setLW(String LW) {
        this.LW = LW;
    }

    public void setRW(String RW) {
        this.RW = RW;
    }

    public void setSS(String SS) {
        this.SS = SS;
    }

    public void setAM(String AM) {
        this.AM = AM;
    }

    public void setCM(String CM) {
        this.CM = CM;
    }

    public void setDM(String DM) {
        this.DM = DM;
    }

    public void setLWB(String LWB) {
        this.LWB = LWB;
    }

    public void setRWB(String RWB) {
        this.RWB = RWB;
    }

    public void setCB(String CB) {
        this.CB = CB;
    }

    public String getGK() {
        return GK;
    }

    public String getCF() {
        return CF;
    }

    public String getLW() {
        return LW;
    }

    public String getRW() {
        return RW;
    }

    public String getSS() {
        return SS;
    }

    public String getAM() {
        return AM;
    }

    public String getCM() {
        return CM;
    }

    public String getDM() {
        return DM;
    }

    public String getLWB() {
        return LWB;
    }

    public String getRWB() {
        return RWB;
    }

    public String getCB() {
        return CB;
    }
}
