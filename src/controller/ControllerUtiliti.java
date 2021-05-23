package controller;

import model.QLBenhNhan;

import java.util.ArrayList;

/**
        * Chức năng của lớp này là tìm kiếm, cập nhật chỉnh sủa,xoá, xắp xếp
        **/
public class ControllerUtiliti {

    public ArrayList<QLBenhNhan> xapXepTheoTen(ArrayList<QLBenhNhan>list){
        for(int i=0;i<list.size();i++){
            for(int j=list.size()-1;j>i;j--){
                QLBenhNhan a= list.get(j);
                QLBenhNhan b=list.get(j-1);
                String[] name1=a.getBenhNhan().getTen().split(" ");
                String[] name2=b.getBenhNhan().getTen().split(" ");
                if(name1[name1.length-1].compareTo((name2[name2.length-1]))<0){
                    list.set(j,b);
                    list.set(j-1,a);
                }
            }
        }
        return list;
    }

    public ArrayList<QLBenhNhan> seachTenBenhNhan(ArrayList<QLBenhNhan>list, String name, int cmnd){
        ArrayList<QLBenhNhan> b = new ArrayList<>();
        for (var a:list){
            if(a.getBenhNhan().getTen().equals(name) && a.getBenhNhan().getCmnd()==cmnd){
                b.add(a);
            }
        }
        return b;
    }
}
