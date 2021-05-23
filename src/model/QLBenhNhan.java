package model;

import java.util.ArrayList;

public class QLBenhNhan {
 private BenhNhan  benhNhan;
 private BenhAn benhAn;
 private String tinhTrang;
private  String ngayKham;
 //contructor


    public QLBenhNhan(BenhNhan benhNhan, BenhAn benhAn, String tinhTrang, String ngayKham) {
        this.benhNhan = benhNhan;
        this.benhAn = benhAn;
        this.tinhTrang = tinhTrang;
        this.ngayKham = ngayKham;
    }

    public QLBenhNhan(BenhNhan benhNhan, BenhAn benhAn, String tinhTrang) {
        this.benhNhan = benhNhan;
        this.benhAn = benhAn;
        this.tinhTrang = tinhTrang;
    }

    //geter and setter

    public String getNgayKham() {
        return ngayKham;
    }

    public void setNgayKham(String ngayKham) {
        this.ngayKham = ngayKham;
    }

    public BenhNhan getBenhNhan() {
        return benhNhan;
    }

    public void setBenhNhan(BenhNhan benhNhan) {
        this.benhNhan = benhNhan;
    }

    public BenhAn getBenhAn() {
        return (BenhAn) benhAn;
    }

    public void setBenhAn(BenhAn benhAn) {
        this.benhAn = benhAn;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    @Override
    public String toString() {
        return "QLBenhNhan{" +
                "benhNhan=" + benhNhan +
                ", benhAn=" + benhAn +
                ", tinhTrang='" + tinhTrang + '\'' +
                ", ngayKham=" + ngayKham +
                '}';
    }

}
