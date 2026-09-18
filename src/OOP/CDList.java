package OOP;

//======File 2: CDList (Mảng động + tìm kiếm gần giống)======

public class CDList {
	
	// -----Thuộc tính------
	
    private CD[] danhSachCD;
    private int soLuong;
    private int khaNang;

    private static final int KICH_THUOC_BAN_DAU = 5;

    //------Constructor------
    
    public CDList() {
        this.khaNang = KICH_THUOC_BAN_DAU;
        this.danhSachCD = new CD[khaNang];
        this.soLuong = 0;
    }

    public int tinhSoLuongCD() {return soLuong;}

    public CD layCDTaiViTri(int i) {
    	if (i >= 0 && i < soLuong) {return danhSachCD[i];}
        return null;}

    
    //-----Tự động mở rộng mảng------
    
    private void moRongMang() {
        int kichThuocMoi = khaNang * 2;
        CD[] mangMoi = new CD[kichThuocMoi];
        for (int i = 0; i < soLuong; i++) {
        	mangMoi[i] = danhSachCD[i];
        	}
        danhSachCD = mangMoi;
        khaNang = kichThuocMoi;
    }

    //------Tìm vị trí theo mã------
    
    public int timViTriTheoMa(int maCD) {
        for (int i = 0; i < soLuong; i++) {
            if (danhSachCD[i].getMaCD() == maCD) {
                return i;
            }
        }
        return -1;
    }
    
    //------Thêm (chống trùng + tự mở rộng)------
    
    public boolean themCD(CD cd) {
        if (cd == null) return false;
        if (timViTriTheoMa(cd.getMaCD()) != -1) {
            System.out.println("Loi: Ma CD " + cd.getMaCD() + " da ton tai!");
            return false;
        }
        if (soLuong == khaNang) {moRongMang();}
        
        danhSachCD[soLuong] = cd;
        soLuong++;
        return true;
    }

    //======Xóa======
    	
    public boolean xoaCD(int maCD) {
        int viTri = timViTriTheoMa(maCD);
        if (viTri == -1) {
            System.out.println("Khong tim thay CD ma " + maCD);
            return false;
        }
        for (int i = viTri; i < soLuong - 1; i++) {
            danhSachCD[i] = danhSachCD[i + 1];
        }
        danhSachCD[soLuong - 1] = null;
        soLuong--;
        return true;
    }
    
    //------Sửa/Cập nhật------

    public boolean capNhatCD(int maCD, String tuaMoi, int sbhMoi, double giaMoi) {
        int viTri = timViTriTheoMa(maCD);
        if (viTri == -1) {
            System.out.println("Khong tim thay CD ma " + maCD);
            return false;
        }
        CD cd = danhSachCD[viTri];
        cd.setTuaCD(tuaMoi);
        cd.setSoBaiHat(sbhMoi);
        cd.setGiaThanh(giaMoi);
        return true;
    }

    //======Tìm theo tự CD======
    
    public CD timTheoMa(int maCD) {
        int viTri = timViTriTheoMa(maCD);
        return (viTri == -1) ? null : danhSachCD[viTri];
    }
    
    //======TÌM THEO TỰA CD (3 CHẾ ĐỘ)
    /**
     * Tìm theo tiền tố (so khớp BÊN TRÁI).
     * ví dụ: "Nhac" khớp "Nhạc Trịnh", "Nhạc Vàng".
     * @return CDList chứa các CD có tựa bắt đầu bằng từ khóa.
     */

    public CDList timTheoTienTo(String tuKhoa) {
        CDList ketQua = new CDList();
        if (tuKhoa == null) return ketQua;
        String tk = tuKhoa.toLowerCase();
        for (int i = 0; i < soLuong; i++) {
            if (danhSachCD[i].getTuaCD().toLowerCase().startsWith(tk)) {
                ketQua.themCD(danhSachCD[i]);
            }
        }
        return ketQua;
    }
    
    /**
     * Tìm theo hậu tố (so khớp từ BÊN PHẢI).
     * Ví dụ: "Trịnh" khớp "Nhạc Trịnh", "Trịnh Công Sơn".
     * @return CDList chứa các CD có tựa kết thúc bằng tuKhoa.
     */

    public CDList timTheoHauTo(String tuKhoa) {
        CDList ketQua = new CDList();
        if (tuKhoa == null) return ketQua;
        String tk = tuKhoa.toLowerCase();
        for (int i = 0; i < soLuong; i++) {
            if (danhSachCD[i].getTuaCD().toLowerCase().endsWith(tk)) {
                ketQua.themCD(danhSachCD[i]);
            }
        }
        return ketQua;
    }

    /**
     * Tìm gần giống (chứa từ khóa ở BẤT KỲ vị trí nào).
     * Đây là tìm kiếm rộng, BAO TRÙM cả TIỀN TỐ và HẬU TỐ.
     * @return CDList chứa các CD có tự chứa tuKhoa.
     */
    
    public CDList timGanGiong(String tuKhoa) {
        CDList ketQua = new CDList();
        if (tuKhoa == null) return ketQua;
        String tk = tuKhoa.toLowerCase();
        for (int i = 0; i < soLuong; i++) {
            if (danhSachCD[i].getTuaCD().toLowerCase().contains(tk)) {
                ketQua.themCD(danhSachCD[i]);
            }
        }
        return ketQua;
    }
    
    //------THỐNG KÊ------

    public double tinhTongGiaThanh() {
        double tong = 0;
        for (int i = 0; i < soLuong; i++) {
            tong += danhSachCD[i].getGiaThanh();
        }
        return tong;
    }

    public double tinhGiaTrungBinh() {
        if (soLuong == 0) return 0;
        return tinhTongGiaThanh() / soLuong;
    }

    public CD timCDDatNhat() {
        if (soLuong == 0) return null;
        CD max = danhSachCD[0];
        for (int i = 1; i < soLuong; i++) {
            if (danhSachCD[i].getGiaThanh() > max.getGiaThanh()) {
                max = danhSachCD[i];
            }
        }
        return max;
    }

    public CD timCDReNhat() {
        if (soLuong == 0) return null;
        CD min = danhSachCD[0];
        for (int i = 1; i < soLuong; i++) {
            if (danhSachCD[i].getGiaThanh() < min.getGiaThanh()) {
                min = danhSachCD[i];
            }
        }
        return min;
    }
    
    //------HIỂN THỊ------

    public void sapXepGiamGiaTangTua() {
        for (int i = 0; i < soLuong - 1; i++) {
            for (int j = i + 1; j < soLuong; j++) {
                if (danhSachCD[i].getGiaThanh() < danhSachCD[j].getGiaThanh()) {
                    CD temp = danhSachCD[i];
                    danhSachCD[i] = danhSachCD[j];
                    danhSachCD[j] = temp;
                } else if (danhSachCD[i].getGiaThanh() == danhSachCD[j].getGiaThanh()) {
                    if (danhSachCD[i].getTuaCD().compareToIgnoreCase(danhSachCD[j].getTuaCD()) > 0) {
                        CD temp = danhSachCD[i];
                        danhSachCD[i] = danhSachCD[j];
                        danhSachCD[j] = temp;
                    }
                }
            }
        }
    }
    
    //------HIỂN THỊ------

    public void hienThiDanhSach() {
        if (soLuong == 0) {
            System.out.println("Danh sach CD trong!");
            return;
        }
        System.out.println("----------------------------------------------------------------------");
        System.out.printf("| %-10s | %-25s | %-12s | %-15s |\n", "MA CD", "TUA CD", "SO BAI HAT", "GIA THANH");
        System.out.println("----------------------------------------------------------------------");
        for (int i = 0; i < soLuong; i++) {
            System.out.println(danhSachCD[i]);
        }
        System.out.println("----------------------------------------------------------------------");
    }
}