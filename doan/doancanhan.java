import java.util.*;
import java.time.*;
interface IDATE{
    void N();
    void X();
    int Gtuoi();
}
class date implements IDATE{
    private int ngay;
    private int thang;
    private int nam;
    public int getNgay() {
		return ngay;
	}
	public void setNgay(int ngay) {
		this.ngay = ngay;
	}
	public int getThang() {
		return thang;
	}
	public void setThang(int thang) {
		this.thang = thang;
	}
	public int getNam() {
		return nam;
	}
	public void setNam(int nam) {
		this.nam = nam;
	}
	public LocalDate ntn;
    Scanner S=new Scanner(System.in);

    public void N(){
        System.out.print("nhap ngay/ thang/ nam : ");
        ngay=S.nextInt();
        thang=S.nextInt();
        nam=S.nextInt();
        while(thang<1||thang>12){
            System.out.print("nhap thang: ");
            thang=S.nextInt();
        }
        while(nam<1900||nam>2999){
            System.out.print("nhap nam: ");
            nam=S.nextInt();
        }
        YearMonth thangVaNam=YearMonth.of(nam, thang);
        int max=thangVaNam.lengthOfMonth();
        ntn=LocalDate.of(nam, thang, ngay );
        while(ngay<1||ngay>max){
            System.out.print("nhap ngay(1-"+max+"): ");
            ngay=S.nextInt();
        }
        
    }
    public void X(){
        System.out.println("ngay/ thang/ nam: "+ngay+"/"+thang+'/'+nam);
    }
    public int Gtuoi(){
        LocalDate hientai= LocalDate.now();
        int tuoi=Period.between(ntn,hientai).getYears();
        return tuoi;
    }

}
abstract class nguoiDung extends date{
    protected String name;
    protected String id;
    protected String stk;
    protected String mk;
    protected String email;
    protected String diaChi;
    protected int tuoi;
    protected date Date;
    protected StringBuilder ghep=new StringBuilder();
    Scanner S=new Scanner(System.in);

    nguoiDung(){}
    
    public void Rsotk(){
            Random New= new Random();
            do{
                System.out.println("\tLua chon");
                System.out.println("1.ngau nhien");
                System.out.println("2.tu chon");
                System.out.println("Nhap Lua chon");
                int chon=S.nextInt();
                if(chon==1){
                    for(int i=0;i<9;i++){
                        int so=New.nextInt(9);
                        ghep.append(so);
                    }
                    stk=ghep.toString();
                    break;
                }else{
                    do{
                        System.out.print("nhap so tai khoan ban muon: ");
                        stk=S.nextLine();
                        if(stk.length()!=9){
                            System.out.println("nhap du 9 chu so! nhap lai: ");
                        }
                    }while(stk.length()!=9);
                    break;
                } 
        }while(true);
        do{
            System.out.print("nhap MK(it nhat 6 ki tu): ");
            mk=S.nextLine();
            if(mk.length()<6){
                System.out.println("chua du ki tu! vui long nhap lai!!!");
            }
        }while(mk.length()<6);
        
    }
    abstract boolean khoaTK();
    abstract String TrangThai();
    public void DK(){
        Date =new date();
        System.out.print("Nhap ten: ");
        this.name=S.nextLine();
        System.out.print("Nhap id: ");
        this.id=S.nextLine();
        System.out.print("Nhap email: ");
        this.email=S.nextLine();        
        System.out.print("Nhap dia chi: ");
        this.diaChi=S.nextLine();
        this.Date.N();
    }
    public void Xtt(){
        System.out.println("ten: "+name+"\nid: "+id+"\nemail: "+email+"\ndia chi: "+diaChi+"\nquyen: ");
        // +quyen+"\ncanh bao"+canhB()+"\ntrang thai: "+TrangThai());
    }
    public int sai;
    public boolean ktrMK(){
        String mkThu;
        while (true) {
            System.out.print("nhap MK: ");
            mkThu=S.nextLine();
            if(!mkThu.equals(this.mk)&&sai<5){
                sai++;
                if(sai<5){
                    System.out.println("MK sai vui long nhap lai!: ");
                }else{
                    System.out.println("da sai qua 5 lan!: ");
                    return false;
                }
            }else{
                break;
            }
        }
        if(sai==5){
            System.out.println("sai 5 lan");
            return false;
        }else{
            return true;
        }
    }
    public void dangNhap(){
        this.ktrMK();
        if(ktrMK()==true){
            this.Xtt();
        }
    }
}
class khachHang extends nguoiDung{
    private float sodu;
    private int loaiHD;
    private int loaiTK;
    private float phiGD;
    private float phiTN;
    
    public float getSodu() {
		return sodu;
	}
	public void setSodu(float sodu) {
		this.sodu = sodu;
	}
	public int getLoaiHD() {
		return loaiHD;
	}
	public void setLoaiHD(int loaiHD) {
		this.loaiHD = loaiHD;
	}
	public int getLoaiTK() {
		return loaiTK;
	}
	public void setLoaiTK(int loaiTK) {
		this.loaiTK = loaiTK;
	}
	public float getPhiGD() {
		return phiGD;
	}
	public void setPhiGD(float phiGD) {
		this.phiGD = phiGD;
	}
	public float getPhiTN() {
		return phiTN;
	}
	public void setPhiTN(float phiTN) {
		this.phiTN = phiTN;
	}
	public void DK(){
        System.out.println("\tdang ki tai khoan ngan hang\n");
        super.DK();
        System.out.print("nhap so du: ");
        sodu=S.nextFloat();
        while (true) {
            System.out.print("Loai TK(1:pho thong || 2: thuong mai || 3.VIP ): ");
            loaiTK=S.nextInt();
            if(loaiTK<1&&loaiTK>3){
                System.out.println("vui long nhap lai!");
            }else{
                break;
            }
        }
        while (true) {
            System.out.print("Loai HD(1:tai khoan thuong || 2:the ngan hang || 3:the tin dung ): ");
            loaiHD=S.nextInt();
            if(loaiTK<1&&loaiTK>3){
                System.out.println("vui long nhap lai!");
            }else{
                break;
            }
        }
        System.out.println("nhap mat khau: ");
    }
    public void Xtt(){
        super.Xtt();
        System.out.println("loai hoat dong: "+this.gLHD()+"\nloai tai khoan "+this.gLTK()+"canh bao "+this.canhB());
    }
    public String gLHD(){
        String loai="";
        switch(loaiHD){
            case 1: loai="pho thong";
                this.phiGD=(7/100);
                break;
            case 2: loai="thuong mai";
                this.phiGD=(5/100);
                break;
            case 3: loai="VIP";
                this.phiGD=(3/100);
                break;
        }
        return loai;
    }
    public String gLTK(){
        String loai="";
        switch(loaiHD){
            case 2: loai="the tin dung";
                this.phiTN=500;
                break;
            case 3: loai="tai khoan thuong";
                this.phiTN=60;
                break;
            case 1: loai="the ngan hang";
                this.phiTN=0;
                break;
        }
        return loai;
    }
    String canhB() {
        if(sai==5){
            return "tai khoan da khoa do sai 5 lan!";
        }else if(sai==3){
            return "tai khoan da sai 3 lan, ban nen thay doi mat khau!";
        }else{
            return "binh thuong";
        }
    }

    boolean khoaTK() {
        if(sai==5) {
        	return true;
        }else {
        	return false;
        }
    }

    String TrangThai() {
    	if(sai==5) {
    		return "tai khoan hien dang bi khoa";
    	}else{
            return "binh thuong";
        }
    }
    
}
class nhanVien extends nguoiDung{
    private date nam;
    private String sdt;
    private double sal;
    public void DK(){
        nam=new date();
        super.DK();
        System.out.println("nhap nam vao lam: ");
        nam.N();
        System.out.print("so dien thoa: ");
        this.setSdt(S.nextLine());
        System.out.println("nhap luong");
        this.setSal(S.nextDouble());
    }
    public double setSal(double sal){
        return this.sal=sal;
    }
	public int getSen(){
        LocalDate hientai=LocalDate.now();
        return Period.between(hientai, nam.ntn).getYears();
    }
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public double getBonus(){
        if(this.getSen()>=5){
            return 5000000;
        }else if(this.getSen()>=3) {
            return 3000000;
        }else{
            return 0;
        }
    }
    public double getSal(){
        return this.sal*this.getBonus();
    }
        boolean khoaTK() {
        System.out.println("xac nhan khoa(1:true)");
        int chon=S.nextInt();
        if(chon==1){
            return true;
        }else{
            return false;
        }
    }   
    String TrangThai() {
        if(this.getSen()>5){
            return "senior employee";
        }else{
            return"New employee";
        }
    }
}
class nganghangList{
    List<nguoiDung> nh=new ArrayList<>();
    List<nguoiDung> tkKhoa=new ArrayList<>();
    Scanner S=new Scanner(System.in);
    public String ktrDK(nguoiDung test){
        int tiep=0;
        while(true) {
            test.Rsotk();
            boolean ktr=true;
            for(int i=0;i<nh.size();i++){
                if(nh.get(i).stk.contains(test.stk)){
                    ktr= false;
                    break;
                }
            }
            if(ktr==true){
                return test.stk;
            }else{
                System.out.println("nhap lai so tai khoan!");
            }
            System.out.print("tiep tuc(1): ");
            tiep=S.nextInt();
            S.nextLine();
            if(tiep!=1){
                return "";
            }
        }
        
    }
    public void dangKi(){
        while(true){

            System.out.print("Add New\n1.Nhan Vien\n2.Khach Hang\nNhap lua chon: ");
            int chon=S.nextInt();
            
            if(chon==1){
                nguoiDung nv=new nhanVien();
                nv.DK();
                nv.stk=this.ktrDK(nv);
                if(!nv.stk.isEmpty()){
                    nh.add(nv);
                }
            }else if(chon==2){
                nguoiDung kh=new khachHang();
                kh.DK();
                kh.stk=this.ktrDK(kh);
                if(!kh.stk.isEmpty()){
                    nh.add(kh);
                }
            }else if(chon==3){
                break;
            }else{
                System.out.println("sai! nhap lai!");
            }
        }
    }
    public boolean ktrKH(nguoiDung n){
        if(n instanceof khachHang){
            return true;
        }else{
            return false;
        }
    }
    public void chuyenKhoan(nguoiDung n){
        int tiep;
        boolean co=false;
        do{
            System.out.print("nhap tai khoan nhan");
            String tknhan=S.nextLine();
            for(int i=0;i<nh.size();i++){
                if(tknhan.equals(nh.get(i).stk)){
                    if(ktrKH(nh.get(i))){
                        co=true;
                        nh.get(i).Xtt();
                        System.out.print("(1) tiep tuc");
                        int chon=S.nextInt();
                        System.out.println("tien chuyen: ");
                        float chuyen=S.nextInt();
                        if(((khachHang)n).getSodu()<chuyen){
                            System.out.println("So du tai khoan khong du");
                        }else{
                            ((khachHang)n).setSodu(((khachHang)n).getSodu() -chuyen);
                            System.out.println("chuyen "+chuyen +" thanh cong: ");
                        }
                    }
                }
            }
            if(!co){
                System.out.println("tai khoan khach hang khong ton tai");

            }
            System.out.println("tiep tuc hay khong(1)");
            tiep=S.nextInt();
        }while(tiep!=1);
    }
    public void Dn(){
        String stkm=S.nextLine();
        for(int i=0;i<nh.size();i++){
            if(nh.get(i).id.contains(stkm)){
                nh.get(i).dangNhap();
                while (true) {
                    if(nh.get(i) instanceof khachHang){                      
                        System.out.println("\t1.chuyen khoan\n\t2.rut tien\n\t");
                        int lc=S.nextInt();
                        if(lc==1){
                            this.chuyenKhoan(nh.get(i));
                        }else if(lc==2){
                            this.rutTien(nh.get(i));
                        }
                    }else{

                    }
                    System.out.println("");
                }
            }
        }
    }
    public void find(String tstk){
        for(int i=0;i<nh.size();i++){
            if(nh.get(i).id.contains(tstk)){
                nh.get(i).Xtt();
            }
        }
    }
    public void rutTien(nguoiDung e){
        while(true){
            System.out.print("nhap so tien rut");
            int rut=S.nextInt();
            if(rut>((khachHang)e).getSodu()){
                ((khachHang)e).setSodu(((khachHang)e).getSodu()-rut);
                System.out.println("da tru thanh cong!\nSo du con lai "+((khachHang)e).getSodu());
            }else{
                System.out.println("so du tai khoan khong du");
            }
        }
    }
    public void lock(String tstk){
        for(int i=0;i<nh.size();i++){
            if(nh.get(i).id.contains(tstk)){
                nh.get(i).Xtt();
                nh.get(i).khoaTK();
                tkKhoa.add(nh.get(i));
                nh.remove(i);
            }
        }  
    }
    public void unlock(String tstk){
        for(int i=0;i<nh.size();i++){
            if(nh.get(i).id.contains(tstk)){
                tkKhoa.get(i).Xtt();
                System.out.println("mo khoa khong: ");
                int chon=S.nextInt();
                if(chon==1){
                    nh.add(null);
                    tkKhoa.remove(i);
                }
            }
        }  
    }
    Comparator<nguoiDung> sortByName = new Comparator<nguoiDung>() {
        public int compare(nguoiDung o1, nguoiDung o2) {
            return o1.name.compareTo(o2.name);  // So sánh tên theo thứ tự từ điển
        }
    };
    Comparator<nguoiDung> sortBySen=new Comparator<nguoiDung>() {
        public int compare(nguoiDung o1,nguoiDung o2){
            return Integer.compare(((nhanVien)o1).getSen(), ((nhanVien)o2).getSen());
        }
    };
    public void sort(){
        List<nguoiDung> nv=new ArrayList<>();
        List<nguoiDung> kh=new ArrayList<>();

        for(int i=0;i<nh.size();i++){
            if(nh.get(i) instanceof nhanVien){
                nv.add(nh.get(i));
                
            }else{
                kh.add(nh.get(i));
            }
        }
        Collections.sort(nv,sortBySen);
        Collections.sort(kh,sortByName);
        System.out.println("danh sach Nhan vien: ");
        for(int i=0;i<nv.size();i++){
            nv.get(i).Xtt();
        }
        System.out.println("danh sach Khach hang: ");
        for(int i=0;i<nv.size();i++){
             nv.get(i).Xtt();
        }
    }
    
}
public class doancanhan {
    public static void main(String[] args) {
        nganghangList n=new nganghangList();
        n.dangKi();
        n.dangKi();
        n.Dn();
    }
}