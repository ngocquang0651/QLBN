package view;
import controller.ControllerUtiliti;
import controller.DataUltiliti;
import model.Account;
import model.BenhAn;
import model.BenhNhan;
import model.QLBenhNhan;
import java.util.ArrayList;
import java.util.Scanner;

public class View {
        public static void main(String[] args) {
            Object var;
            var benhNhanFileName = "BENHNHAN.DAT";
            var benhAnFileName = "BENHAN.DAT";
            var qlBenhNhanFileName = "QLBENHNHAN.DAT";
            var controller = new DataUltiliti();
            var ultiliti= new ControllerUtiliti();
            var benhNhans = new ArrayList<BenhNhan>();
            var benhAns = new ArrayList<BenhAn>();
            var qlBenhNhans = new ArrayList<QLBenhNhan>();
            var isBenhNhanChecked = false;
            var isBenhAnChecked = false;
            int choice = 0;
            Scanner scanner = new Scanner(System.in);
            // check password
            while (true){
                System.out.println("nhập user: ");
                var username = scanner.nextLine();
                System.out.println("nhập pass: ");
                var pass = scanner.nextLine();
                Account account = new Account(username,pass);
                if(isLoadDataFromService(account)) {
                    break;
                }
            }
            do {
                System.out.println("___________MENU___________");
                System.out.println("1. Thêm mới 1 bệnh nhân trong file.");
                System.out.println("2. Hiện thị tất cả bệnh nhân có trong file.");
                System.out.println("3. Thêm bệnh án vô file. ");
                System.out.println("4. Hiện thị bệnh có trong file.");
                System.out.println("5. Tao ra danh sách ql bệnh nhân va in ra màn hinh ");
                System.out.println("6. Xắp xếp tên bệnh nhân ");
                System.out.println("7. Tìm kiếm tên bệnh nhân, nếu không có thì yêu cầu nhập mới ");
                System.out.println("0. Đăng xuất:");
                choice = scanner.nextInt();
                scanner.nextLine();//bo dong chua lua chon
                switch (choice) {
                    case 0: {
                        System.out.println("Cảm ơn bạn đã sử dụng dịch vụ :");
                        break;
                    }

                    case 1: {
                        if (!isBenhNhanChecked) {
                            checkBenhNhanTD(controller, benhNhanFileName);//Check mã bệnh nhânbị trùng.
                            isBenhNhanChecked = true;
                        }
                        String ten,diaChi,sdt;
                        int cmnd;
                        System.out.println("Nhập tên bệnh nhân");
                        ten = scanner.nextLine();
                        System.out.println("nhập địa chi");
                        diaChi = scanner.nextLine();
                        System.out.println("nhập cmnd của bệnh nhân");
                        cmnd = scanner.nextInt();
                        scanner.nextLine();
                        do {
                            System.out.println("Nhập số điện thoại, yêu cầu nhập đủ 3 sô");
                            sdt = scanner.nextLine();
                        } while (sdt.length() != 3);
                        BenhNhan benhNhan = new BenhNhan(ten,cmnd,diaChi,sdt);
                        controller.writeBenhNhanToFile(benhNhan,benhNhanFileName);
                        break;
                    }

                    case 2: {
                        benhNhans = controller.readBenhNhanFromFile(benhNhanFileName);
                        showBenhNhaninfo(benhNhans);
                        break;
                    }

                    case 3: {
                        if (!isBenhAnChecked) {
                            checkReaderTD(controller, benhAnFileName);//Check IDbị trùng.
                            isBenhAnChecked = true;
                        }
                        String tenBenhAn, tenBacSy, tenKhoa;
                        System.out.println("Nhập tên bệnh án: ");
                        tenBenhAn = scanner.nextLine();
                        System.out.println("Nhập tên bác sỹ: ");
                        tenBacSy = scanner.nextLine();
                        System.out.println("Nhập tên khoa");
                        tenKhoa = scanner.nextLine();
                        BenhAn benhAn = new BenhAn(tenBenhAn,tenBacSy,tenKhoa);
                        controller.writeBenhAnToFile(benhAn, benhAnFileName);
                        break;
                    }

                    case 4: {
                        benhAns = controller.readBenhAnFromFile(benhAnFileName);
                        showBenhAninfo(benhAns);
                        break;
                    }

                    case 5: {
                        //B0: khoi t danh sach
                        benhNhans = controller.readBenhNhanFromFile(benhNhanFileName);
                        benhAns = controller.readBenhAnFromFile(benhAnFileName);
                        qlBenhNhans = controller.readQLBenhNhanFromFile(qlBenhNhanFileName);
                        //B1:
                        int benhNhanID, benhAnID;
                        do {
                            showBenhNhaninfo(benhNhans);
                            System.out.println("Nhập mã bệnh nhân thêm vào, nhâph 0 để thoát.");
                            benhNhanID = scanner.nextInt();
                            if (benhNhanID == 0) {
                                break;//thoat ra
                            }
                        } while (benhNhanID ==0);
                        do {
                            showBenhAninfo(benhAns);
                            System.out.println("Nhập mã bệnh án, nhập 0 để bỏ qua.");
                            benhAnID = scanner.nextInt();
                            if(benhAnID == 0) {
                                break;
                            }
                        } while (benhAnID ==0);
                        //B2 nhap so luong benh an

                        scanner.nextLine();//bo dong co chua so
                        System.out.println("Nhập tình trạng:");
                        String status = "";
                        status = scanner.nextLine();
                        System.out.println("Nhập ngày khám:");
                        String ngayKham=scanner.nextLine();
                        BenhNhan currentBenhNhan = getBenhNhan(benhNhans,benhNhanID);
                        BenhAn currentBenhAn = getBenhAn(benhAns,benhAnID);
                        QLBenhNhan b =new QLBenhNhan(currentBenhNhan,currentBenhAn,status,ngayKham);
                        controller.writeQLBNToFile(b,qlBenhNhanFileName);
                        showQLBenhNhanInfo(controller.readQLBenhNhanFromFile(qlBenhNhanFileName));
                        break;
                    }

                    case 6:{
                        int x=0;
                        qlBenhNhans=controller.readQLBenhNhanFromFile(qlBenhNhanFileName);//doc danh sach trong file

                        for (var a:qlBenhNhans) {
                            System.out.println(a);
                        }
                        do{
                            System.out.println("__________Nhập lựa chọn xắp xếp__________");
                            System.out.println("1. xap xep theo ten benh nhan tu A-->z");
                            System.out.println("0. Thoát khỏi menu");
                            System.out.println("m chon?");
                            x= scanner.nextInt();
                            if(x==0){
                                break;
                            }
                            switch (x){
                                case 1:{
                                    //xap xep theo ten
                                    qlBenhNhans= ultiliti.xapXepTheoTen(qlBenhNhans);
                                    showQLBenhNhanInfo(qlBenhNhans);
                                    break;
                                }

                            }
                        }while(true);
                        break;
                    }

                    case 7:{
                        int toltal1=0;
                        do{
                        qlBenhNhans= controller.readQLBenhNhanFromFile(qlBenhNhanFileName);
                        String name;
                        int cmnd;
                        System.out.println("Nhập tên bệnh nhân muốn tìm kiếm");
                        name=scanner.nextLine();
                        System.out.println("Nhập cmnd bệnh nhân:");
                        cmnd=scanner.nextInt();
                        toltal1 = getsoLanKham(qlBenhNhans, name, cmnd);
                        if(toltal1 !=0) {
                            ArrayList<QLBenhNhan> qlBenhNhan1 = ultiliti.seachTenBenhNhan(qlBenhNhans, name, cmnd);
                            System.out.println(qlBenhNhan1);
                            System.out.println("Số lần khám: "+toltal1);
                        }else {
                            System.out.println("Bệnh nhân không có trong danh sách, bổ sung bệnh nhân vào danh sách QLBenhNhan bằng cách lựa chọn 1 để nhập mới bệnh nhân");
                           break;
                        }
                        }while (toltal1==0 );
                        break;
                    }
                }
            } while (choice != 0);
        }

    private static boolean isLoadDataFromService(Account account) {
            return new DataUltiliti().isAccountExistFromFile("ACCOUNT.DAT",account);
    }

    private static void showQLBenhNhanInfo(ArrayList<QLBenhNhan> qlBenhNhans) {
            System.out.println("Xuất ra danh sách file");
            for(var b:qlBenhNhans){
                System.out.println(b);
            }
        }

        private static BenhAn getBenhAn(ArrayList<BenhAn> benhAns, int benhAnID) {
            for(int i=0; i<benhAns.size();i++){
                if(benhAns.get(i).getMaBenhAn()==benhAnID){
                    return benhAns.get(i);
                }
            }
            return null;
        }

        private static BenhNhan getBenhNhan(ArrayList<BenhNhan> benhNhans, int benhNhanID) {
            for(int i=0;i<benhNhans.size();i++){
                if(benhNhans.get(i).getBenhNhanID()==benhNhanID){
                    return benhNhans.get(i);
                }
            }
            return null;
        }

        //trả về số lần khám
        private static int getsoLanKham(ArrayList<QLBenhNhan> qlBenhNhans, String name, int cmnd) {
            int count=0;
            for(var b : qlBenhNhans){
                if(b.getBenhNhan().getTen().equals(name) && b.getBenhNhan().getCmnd()==cmnd){
                    count++;
                }
            }
            return count;

        }

        private static void showBenhAninfo (ArrayList < BenhAn > benhAns) {
            System.out.println("Xuất ra danh sách bệnh án trong file");
            for (var c : benhAns) {
                System.out.println(c);
            }
        }

        public static void checkBenhNhanTD (DataUltiliti controller, String fileName){
            var listBenhNhan = controller.readBenhNhanFromFile(fileName);
            if (listBenhNhan.size() != 0) {
                BenhNhan.setId(listBenhNhan.get(listBenhNhan.size() - 1).getBenhNhanID() + 1);
            }
        }

        public static void checkReaderTD (DataUltiliti controller, String fileName){
            var listBenhAn = controller.readBenhAnFromFile(fileName);
            if (listBenhAn.size() != 0) {
                BenhAn.setId(listBenhAn.get(listBenhAn.size() - 1).getMaBenhAn() + 1);
            }
        }

        private static void showBenhNhaninfo (ArrayList < BenhNhan > benhNhans) {
            System.out.println("Xuất ra thông tin bệnh nhân trong  file");
            for (var b : benhNhans) {
                System.out.println(b);
            }
        }

    }


