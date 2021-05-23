package model;

import java.security.PublicKey;

public class BenhNhan {
    public static int id=1000000000;
    private int benhNhanID;
    private int cmnd;
    private String ten;
    private String diaChi;
    private String sdt;

    //contructor


    public BenhNhan() {
    }
    public BenhNhan(int benhNhanID) {
        this.benhNhanID= benhNhanID;
    }

    public BenhNhan( String ten,int cmnd, String diaChi, String sdt) {

        setBenhNhanID();
        this.ten = ten;
        this.cmnd = cmnd;
        this.diaChi = diaChi;
        this.sdt = sdt;
    }

    //getter and setter
    public static int getId(){return id;}
    public static int setId(int id){BenhNhan.id=id;
        return id;
    }
    public int getBenhNhanID() {
        return benhNhanID;
    }

    public void setBenhNhanID() {
        this.benhNhanID = id++;
    }

    public void setBenhNhanID(int id) {
        this.benhNhanID = id;
    }


    public int getCmnd() {
        return cmnd;
    }

    public void setCmnd(int cmnd) {
        this.cmnd = cmnd;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    //


    @Override
    public String toString() {
        return "BenhNhan{" +
                "benhNhanID=" + benhNhanID +
                ", ten='" + ten + '\'' +
                ", cmnd=" + cmnd +
                ", diaChi='" + diaChi + '\'' +
                ", sdt='" + sdt + '\'' +
                '}';
    }
}
