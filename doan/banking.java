import java.util.random.*;
import java.util.*;
import java.io.IOException;

class nguoi{
    protected String name;
    protected String stk;
    protected double balance;
    protected String tenDn;
    protected String matKhau;
    nguoi(){
        this.balance=100;
    }
    public void ranD(){
        Random ran=new Random();
        StringBuilder somoi=new StringBuilder();
        for(int i=0;i<=9;i++){
            int so=ran.nextInt(9);
            somoi.append(so);
        }
        stk=somoi.toString();
    }
    public void N(){
        Scanner S=new Scanner(System.in);
        

        System.out.print("nhap ten: ");
        name =S.nextLine();
        
        System.out.print ("So du ban dau: ");
        balance=S.nextDouble();

        System.out.println("Da tao tai khoan");
        
        System.out.println("");

    }
    public void taoTk(){
        Scanner S=new Scanner(System.in);
        int chon;
        StringBuilder somoi=new StringBuilder();
        while(true){
            System.out.print("Tu chon(1)||May chon(2): ");
            chon=S.nextInt();

            if(chon==1){
                System.out.print("nhap so tai khoan(10 so):");
                String nhap;
                StringBuilder moi=new StringBuilder();
                do{
                    nhap="";
                    System.out.println("Vui long nhap lai! (Nhap 10 chu so): ");
                    nhap=S.next();
                }while(nhap.length()!=10);
                moi.append(nhap);
                stk=moi.toString();

            break;
            }else if(chon==2){
                ranD();
                break;
            }else{
                System.out.println("lua chon sai! Nhap lai!!!");
            }          
        }
        System.out.println(stk);        
    }
    public void Xuat(){
        System.out.println("    TAI KHOAN");
        System.out.println("TEN: "+name);
        System.out.println("SO TAI KHOAN: "+stk);
        System.out.println("SO DU: "+ balance );
        System.out.println();
    }
}
class NganHang{
    private nguoi[] N=new nguoi[1000];
    private String ma,xacnhan;
    private boolean ktrD=true;
    private nguoi thu;

    int n=0;
    public void maXM(){
        Scanner S=new Scanner (System.in);
        Random R=new Random();
        StringBuilder build=new StringBuilder();
       
        for(int i=0;i<6;i++){
            int so=R.nextInt(9);
            build.append(so);
        }
        ma=build.toString();
        System.out.println("Ma xac nhan: "+ma);

        System.out.print("Nhap ma xac nhan: ");
        xacnhan=S.next();
    }
    public void Taotk(){
        Scanner S=new Scanner(System.in);
        boolean ktr=true;

        N[n]=new nguoi();
        while (true) {
            System.out.print("Nhap Ten Dang Nhap: ");
            N[n].tenDn=S.nextLine();
            for(int i=0;i<n;i++){
                if(N[i].tenDn.equals(N[n].tenDn)){
                    System.out.print("Da co tai khoan! Moi nhap lai: ");
                    ktr=false;
                }else{
                    ktr=true;
                }
            }
            if(ktr==true){
                System.out.print("Dat mat khau: ");
                    N[n].matKhau=S.nextLine();
                    N[n].N();
                    N[n].taoTk();
                    break;
            }
        }
        n++;
    }
    
    //kiem tra ten dang nhap
    public void ktrTen(){
        Scanner S=new Scanner(System.in);
        String testN;
        boolean test=false;
        do{
            System.out.print("Ten dang nhap: ");
            testN=S.nextLine();
            for(int i=0;i<n;i++){
                if(N[i].name.equals(testN)){
                    thu=N[i];
                    test=true;
                    break;
                }
            }
            if(test){
                ktrD=true;
            }else{
                ktrD=false;
                System.out.println("Khong co ten tai khoan: ");
            }
        }while(test==false); 
    }
    //ktr mat khau
    public void ktrMK(){
        Scanner S=new Scanner(System.in);
        String testMK;
        if(ktrD){
            do{
                System.out.print("Nhap mat khau: ");
                testMK=S.nextLine();
                if(testMK.equals(thu.matKhau)){
                    thu.Xuat();
                    break;
                }else{
                    System.out.println("Sai mat khau!: ");
                }
            }while(true);
        }
    }
    //tong hop 3 phan sau ktrMK va ktr ten TK
    public void dangNhap()throws IOException{
        System.out.println();
        this.ktrTen();
        this.ktrMK();
        this.sau(thu);

    }
    public void chuyenKhoan(nguoi thu) throws IOException{
        Scanner S=new Scanner(System.in);
        System.out.println();
        System.out.println("CHUYEN KHOAN");
        System.out.print("Nhap so tai khoan nhan: ");
        char tiep;boolean ktrD=true;
        do {
            String stkN=S.nextLine();
            for(int i=0;i<n;i++){
                if(stkN.equals(N[i].stk)){
                    System.out.println("so tai khoan nhan: "+N[i].stk);
                    System.out.println("ten tai khoan nhan: "+N[i].name);

                    System.out.print("nhap so tien chuyen: ");
                    double chuyen=S.nextDouble();
                    if(thu.balance>=chuyen){
                        thu.balance=thu.balance-chuyen;
                        N[i].balance=N[i].balance+chuyen;
                        System.out.println("Chuyen tien thanh cong: -" +chuyen);
                    }else{
                        System.out.println("so du khong du: "+thu.balance);
                    }
                    ktrD=false;
                    break;
                }
            }
            if(ktrD){
                System.out.print("Sai tai khoan nhan!(tiep tuc: t || Ket thuc: k): ");
            }
            tiep =(char) System.in.read();
            System.in.skip(tiep);
        }while(tiep=='t'|| tiep=='T');
        
    }
    public void rutTien(nguoi thu){
        System.out.println();
        System.out.println("RUT TIEN");

        Scanner S=new Scanner(System.in);
        System.out.print("Nhap so tien can rut: ");
        int rut=S.nextInt();
        if(thu.balance>=rut){
            maXM();
            if(ma.equals(xacnhan)){
                thu.balance-=rut;
                System.out.println("Rut tien thanh cong: -"+rut);
            }else{
                System.out.println("xac minh that bai");
            }
            
        }else{
            System.out.println("So tien trong tai khoan khong du!");
        }
    }
    public void napTien(nguoi thu){
        System.out.println();
        System.out.println("NAP TIEN");

        System.out.print("So tien muon nap: ");
        Scanner S=new Scanner (System.in);
        int nap=S.nextInt();
        if(nap>=1){
            maXM();
            if(ma.equals(xacnhan)){
                thu.balance+=nap;
                System.out.println("Nap tien thanh cong: +"+nap);
            }else{
                System.out.println("Ma xac minh sai!");
            }
        }
    }
    //tong hop 3 phan chuyen khoan, nap tien va rut tien
    public void sau(nguoi thu) throws IOException{
        Scanner S=new Scanner (System.in);
        System.out.println("1.chuyen tien");
        System.out.println("2.rut tien");
        System.out.println("3.nap tien");
        System.out.println("0.dang xuat");
        int chon;
        while (true) {
            System.out.print("Nhap lua chon: ");
            chon=S.nextInt();

            if(chon==1){
                chuyenKhoan(thu);
            }else if(chon==2){
                rutTien(thu);
            }else if(chon==3){
                napTien(thu);
            }else if(chon==0){
                break;
            } else{
                System.out.println("sai  cu phap!: ");
            }
        }

    }
    public void Manager() throws IOException{
        int chon;
        Scanner S=new Scanner(System.in);
        
        while (true) {
            System.out.println("\tNgan Hang Nguyen The Vu");
            System.out.println("1.Dang Nhap");
            System.out.println("2.Dang Ki");
            System.out.println("3.Thoat");
            System.out.print("Nhap lua chon: ");
            chon=S.nextInt();
            S.nextLine();

            if(chon==1){
                System.out.println("\tDang Nhap\n");
                dangNhap();
            }else if(chon==2){
                System.out.println("\tDang Ki\n");
                Taotk();
            }else if(chon==3){
                S.close();
                break;
            }else{
                System.out.print("Sai lua chon! Nhap lai: ");
            }
        }
    }
}
public class banking {
    public static void main(String[] args) throws IOException {
        nguoi test=new nguoi();
        NganHang n=new NganHang();
        n.Manager();
    }
}
