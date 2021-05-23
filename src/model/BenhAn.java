package model;

import java.util.ArrayList;

public class BenhAn extends ArrayList<BenhAn> {
    public static int id= 100000;
    private int maBenhAn;
    private String tenBenhAn;
    private String tenBacSy;
    private String tenKhoa;

    public static int getId(){return id;}
    public static int setId(int id){BenhAn.id=id;
        return id;
    }

    //Constructor
    public BenhAn(int i) {
        this.maBenhAn = i;
    }

    public BenhAn(int maBenhAn,String tenBenhAn, String tenBacSy, String tenKhoa){}

    public BenhAn(String tenBenhAn, String tenBacSy, String tenKhoa) {
        setMaBenhAn();
        this.tenBenhAn = tenBenhAn;
        this.tenBacSy = tenBacSy;
        this.tenKhoa = tenKhoa;

    }

    public BenhAn() {
    }

    //getter and setter
    public int getMaBenhAn() {
        return maBenhAn;
    }
    public void setMaBenhAn(){
        this.maBenhAn=id++;
    }
    public void setMaBenhAn(int id) {
        this.maBenhAn = id;
    }

    public String getTenBenhAn() {
        return tenBenhAn;
    }

    public void setTenBenhAn(String tenBenhAn) {
        this.tenBenhAn = tenBenhAn;
    }

    public String getTenBacSy() {
        return tenBacSy;
    }

    public void setTenBacSy(String tenBacSy) {
        this.tenBacSy = tenBacSy;
    }

    public String getTenKhoa() {
        return tenKhoa;
    }

    public void setTenKhoa(String tenKhoa) {
        this.tenKhoa = tenKhoa;
    }

    @Override
    public String toString() {
        return "BenhAn{" +
                "maBenhAn=" + maBenhAn +
                ", tenBenhAn='" + tenBenhAn + '\'' +
                ", tenBacSy='" + tenBacSy + '\'' +
                ", tenKhoa='" + tenKhoa + '\'' +
                '}';
    }
}
