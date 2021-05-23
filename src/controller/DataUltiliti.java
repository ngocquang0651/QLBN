package controller;

import model.Account;
import model.BenhNhan;
import model.BenhAn;
import model.QLBenhNhan;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class DataUltiliti {

        private FileWriter fileWriter;
        private BufferedWriter bufferedWriter;
        private PrintWriter printWriter;
        private Scanner scanner;

        //___________mo va dong file khi viet_____________

        void openFileToWrite (String fileName ){
            try {
                fileWriter = new FileWriter(fileName,true);
                bufferedWriter = new BufferedWriter(fileWriter);
                printWriter = new PrintWriter(bufferedWriter);
            }catch (IOException e){
                e.printStackTrace();
            }

        }

        public void closeFileAfterWrite (String fileName){
            try {
                printWriter.close();
                bufferedWriter.close();
                fileWriter.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        //______________Mo va dong file khi doc_____________________
        public void openFileToRead (String fileName){
            try{
                File file =new File(fileName);
                if(!file.exists()){// Neu file chua ton tai
                    file.createNewFile();//tao file moi
                }
                scanner = new Scanner(Paths.get(fileName),"UTF-8");
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        public void closeFileToRead(String fileName){
            try{
                scanner.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        //_______________Bệnh nhân_______________
        public void writeBenhNhanToFile (BenhNhan benhNhan, String fileName){
            openFileToWrite(fileName);
            printWriter.println(benhNhan.getBenhNhanID()+"|"+benhNhan.getTen()+"|"+benhNhan.getCmnd()+"|"
                    +benhNhan.getDiaChi() +"|"+benhNhan.getSdt());
            closeFileAfterWrite(fileName);
        }

        public ArrayList<BenhNhan> readBenhNhanFromFile(String fileName){
            openFileToRead(fileName);
            ArrayList<BenhNhan> benhNhans = new ArrayList<>();//kiểu dữ liệu mảng động giống vector trong c++ tự động mở rộng mảng khi có phàn tử thêm vào
            while (scanner.hasNextLine()){ // Nếu còn dòng thì đọc dữ liệu trong file và in vào Benhnhán
                String data = scanner.nextLine();
                BenhNhan benhNhan = createBenhNhanFromData(data);
                benhNhans.add(benhNhan);
            }

            closeFileToRead(fileName);
            return benhNhans;
        }

        public BenhNhan createBenhNhanFromData(String data) {
            BenhNhan benhNhan = new BenhNhan();
            String[] datas = data.split("\\|");
            benhNhan.setBenhNhanID(Integer.parseInt(datas[0]));
            benhNhan.setTen(datas[1]);
            benhNhan.setCmnd(Integer.parseInt(datas[2]));
            benhNhan.setDiaChi(datas[3]);
            benhNhan.setSdt(datas[4]);
            return benhNhan;
        }

        //_________________Bệnh án_____________________

        public void writeBenhAnToFile(BenhAn benhAn, String fileName){
            openFileToWrite(fileName);
            printWriter.println(benhAn.getMaBenhAn()+"|"+benhAn.getTenBenhAn()+"|"+benhAn.getTenBacSy()
                    +"|"+benhAn.getTenKhoa());
            closeFileAfterWrite(fileName);
        }

        public ArrayList<BenhAn> readBenhAnFromFile(String fileName){
            openFileToRead(fileName);
            ArrayList<BenhAn> benhAns = new ArrayList<>();
            while (scanner.hasNextLine()){
                String data = scanner.nextLine();
                BenhAn benhAn = createBenhAnFromData(data);
                benhAns.add(benhAn);
            }
            closeFileToRead(fileName);
            return benhAns;
        }

        public BenhAn createBenhAnFromData(String data) {
            String[] datas = data.split("\\|");//Tách chuỗi thành mảng nếu gặp | thì tạo ra 1 phần tử
            BenhAn benhAn =new BenhAn();
            benhAn.setMaBenhAn(Integer.parseInt(datas[0]));
            benhAn.setTenBenhAn(datas[1]);
            benhAn.setTenBacSy(datas[2]);
            benhAn.setTenKhoa(datas[3]);
            return benhAn;
        }

        //_______________BRM_______________

        public void writeQLBNToFile(QLBenhNhan qlBenhNhan, String fileName){
            openFileToWrite(fileName);
            printWriter.println(qlBenhNhan.getBenhNhan().getBenhNhanID()+
                    "|"+qlBenhNhan.getBenhAn().getMaBenhAn()+"|"
                    +qlBenhNhan.getTinhTrang()+"|"+qlBenhNhan.getNgayKham());
            closeFileAfterWrite(fileName);
        }

        public ArrayList<QLBenhNhan> readQLBenhNhanFromFile(String fileName){
            Object var;
            var benhNhans = readBenhNhanFromFile("BENHNHAN.DAT");
            var benhAns = readBenhAnFromFile("BENHAN.DAT");
            openFileToRead(fileName);
            ArrayList<QLBenhNhan> qlBenhNhans = new ArrayList<>();
            while(scanner.hasNextLine()){
                String data = scanner.nextLine();
                QLBenhNhan qlBenhNhan = createQLBenhNhansFromData(data,benhNhans,benhAns);
                qlBenhNhans.add(qlBenhNhan);
            }
            closeFileToRead(fileName);
            return qlBenhNhans;
        }

        public QLBenhNhan createQLBenhNhansFromData(String data,ArrayList<BenhNhan>benhNhans,ArrayList<BenhAn>benhAns) {
            String[] datas = data.split("\\|");
            QLBenhNhan qlBenhNhan =new QLBenhNhan((getBenhNhan(benhNhans,Integer.parseInt(datas[0]))),
                    getBenhAn(benhAns,Integer.parseInt(datas[1])),datas[2],datas[3]);

            return qlBenhNhan;
        }

        /**
         * Phương thức trả về 1 đối tượng Bệnh án  trong danh sách với ID cho trước
         * @param benhAns
         * @param maBenhAn
         * @return
         */
        private static BenhAn getBenhAn(ArrayList<BenhAn> benhAns, int maBenhAn) {
            for(int i=0; i<benhAns.size();i++){
                if(benhAns.get(i).getMaBenhAn()==maBenhAn){
                    return benhAns.get(i);
                }
            }
            return null;
        }

        private static BenhNhan getBenhNhan(ArrayList<BenhNhan> benhNhans, int maBenhNhan) {
            for (int i = 0; i < benhNhans.size(); i++) {
                if (benhNhans.get(i).getBenhNhanID() == maBenhNhan) {
                    return benhNhans.get(i);
                }
            }
            return null;

        }

    public boolean isAccountExistFromFile(String fileName,Account account){
        openFileToRead(fileName);
        while (scanner.hasNextLine()){
            String data = scanner.nextLine();
            Account account1 = createAccountFromData(data);
           if (account1.getUserName().equals(account.getUserName())  && account1.getPassword().equals(account.getPassword())){
               return true;
           }
            System.out.println();
        }
        closeFileToRead(fileName);
        return false;
    }

    private Account createAccountFromData(String data) {
        String[] datas = data.split("\\|");
        Account account =new Account(datas[0],datas[1]);

        return account;
    }


}
