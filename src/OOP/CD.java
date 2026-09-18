package OOP;

//=======File 1: CD.Java========

public class CD {
	
	//------thuộc tính (private)------
	
    private int maCD;
    private String tuaCD;
    private int soBaiHat;
    private double giaThanh;
    
    //------hằng số mặc định-------
    
    public static final int MA_CD_MAC_DINH = 999999;
    public static final String TUA_CD_MAC_DINH = "chua xac dinh";

    //------Constructor mặc định------
    
    public CD() {
        this.maCD = MA_CD_MAC_DINH;
        this.tuaCD = TUA_CD_MAC_DINH;
        this.soBaiHat = 1;
        this.giaThanh = 1.0;
    }
    
    //------Constructor tham số-----
    
    public CD(int maCD, String tuaCD, int soBaiHat, double giaThanh) {
        setMaCD(maCD);
        setTuaCD(tuaCD);
        setSoBaiHat(soBaiHat);
        setGiaThanh(giaThanh);
    }

    //-----Getter-----
    
    public int getMaCD()        {return maCD; }
    public String getTuaCD()    {return tuaCD; }
    public int getSoBaiHat()    {return soBaiHat; }
    public double getGiaThanh() {return giaThanh; }

    //-----Setter có kiểm tra ràng buộc------
    
    public void setMaCD(int maCD) {
        if (maCD <= 0) {
            throw new IllegalArgumentException("Loi: Ma CD phai > 0!");
        }
        this.maCD = maCD;
    }

    public void setTuaCD(String tuaCD) {
        if (tuaCD == null || tuaCD.trim().isEmpty()) {
            throw new IllegalArgumentException("Loi: Tua CD khong duoc rong!");
        }
        this.tuaCD = tuaCD;
    }

    public void setSoBaiHat(int soBaiHat) {
        if (soBaiHat <= 0) {
            throw new IllegalArgumentException("Loi: So bai hat phai > 0!");
        }
        this.soBaiHat = soBaiHat;
    }

    public void setGiaThanh(double giaThanh) {
        if (giaThanh <= 0) {
            throw new IllegalArgumentException("Loi: Gia thanh phai > 0!");
        }
        this.giaThanh = giaThanh;
    }

    //------toString định dạng bảng------
    
    @Override
    public String toString() {
        String tuaHienThi = tuaCD;
        if (tuaHienThi != null && tuaHienThi.length() > 22) {
            tuaHienThi = tuaHienThi.substring(0, 19) + "...";
        }
        return String.format("| %-10d | %-25s | %-12d | %-15.2f |", 
                maCD, tuaHienThi, soBaiHat, giaThanh);
    }
}