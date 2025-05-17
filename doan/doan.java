
import java.io.*;
import java.util.*;
import java.time.*;
interface IBANK {
    String EXCELLENT = "Excellent";
    String AVERAGE = "Average";
    String GOOD = "Good";
    String POOR = "Poor";
    String ACTIVE = "Active";
    String INACTIVE = "Inactive";
    String BANNED = "Banned";
    String SUSPENDED = "Suspended";

    void signup();
    void viewInfo(); // Đã sửa lại chính tả
}

class myDate {
    private int year;
    private int month;
    private int day;
    private LocalDate now = LocalDate.now();
    private LocalDate date;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public LocalDate getNow() {
        return now;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void enterDate() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter day/month/year: ");
        
        this.setDay(scanner.nextInt());
        this.setMonth(scanner.nextInt());
        this.setYear(scanner.nextInt());

        // Kiểm tra tính hợp lệ của tháng
        while (month < 1 || month > 12) {
            System.out.print("Enter month (1-12) again: ");
            this.setMonth(scanner.nextInt());
        }

        // Kiểm tra tính hợp lệ của năm
        while (year < 1900 || year > 2999) {
            System.out.print("Enter year (1900-2999) again: ");
            this.setYear(scanner.nextInt());
        }

        // Kiểm tra tính hợp lệ của ngày
        YearMonth yearMonth = YearMonth.of(year, month);
        int maxDay = yearMonth.lengthOfMonth();
        while (day < 1 || day > maxDay) {
            System.out.print("Enter day (1-" + maxDay + ") again: ");
            this.setDay(scanner.nextInt());
        }

        // Đặt ngày tháng hợp lệ
        this.setDate(LocalDate.of(year, month, day));
    }

    public String displayDate() {
        return day + "/" + month + "/" + year;
    }

    // Tính toán số năm giữa ngày hiện tại và ngày đã nhập
    public int calculateYears() {
        return Period.between(now, date).getYears();
    }

    // Tính toán số tháng giữa ngày hiện tại và ngày đã nhập
    public int calculateMonths() {
        return (int) Period.between(now, date).toTotalMonths();
    }

    public void setNow(LocalDate now) {
        this.now=now;
    }
}

abstract class bank implements IBANK {
    public String name;
    public String id;
    public String passWord;
    public String accountNumber;
    public String email;
    public myDate birthDay;
    public int incorrectNumber;
    public myDate lastLogin;
    public String status;
    public String role;
    public String phoneNumber;
    Scanner S=new Scanner(System.in);
    
    public String spacesOfFile() {
        return String.format("|  %-30s  |  %-50s  |  %-30s  |  %-15s  |  %-10s  |  %-10s  |  %-20s  |  %-10s  |  %-15s  |  %-20s  ",
            this.getName(), this.getId(), this.getEmail(), this.getAccountNumber(), this.getPassWord(), 
            this.getBirthDay().displayDate(),
            this.getLastLogin().displayDate(),this.getStatus(), this.getRole(), this.getPhoneNumber());
    }
    
    public void infile(String file) throws IOException {
        try (FileWriter fw = new FileWriter(new File(file), true)) { // Append = true để nối dữ liệu
            String data = this.getName() + "," + 
                          this.getId() + "," + 
                          this.getEmail() + "," + 
                          this.getAccountNumber() + "," + 
                          this.getPassWord() + "," + 
                          (this.getBirthDay().displayDate()) + "," +
                          ( this.getLastLogin().displayDate()) + "," +
                          this.getStatus() + "," + 
                          this.getRole() + "," + 
                          this.getPhoneNumber() + "\n"; // Thêm xuống dòng
            fw.write(data); // Ghi dữ liệu của lớp cha
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getPassWord() { return passWord; }
    
    public void setPassWord(String passWord) {
        while (passWord.length() < 9) {
            System.out.println("Password must be at least 9 characters. Please enter again:");
            Scanner S = new Scanner(System.in); // Nên tách phần nhập vào để rõ ràng hơn
            passWord = S.nextLine();
        }
        this.passWord = passWord;
        System.out.println("Password is: " + this.getPassWord());
    }

    public String getAccountNumber() { return accountNumber; }
    
    public void setAccountNumber() {
        while (true) {
            System.out.print("1. Custom Account Number\n2. Random Account Number\nEnter your choice: ");
            Scanner S = new Scanner(System.in);
            int choose = S.nextInt();
            if (choose == 1) {
                this.customAccountNumber();
            } else if (choose == 2) {
                this.randomAccountNumber();
            } else {
                break;
            }
        } 
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public myDate getBirthDay() { return birthDay; }
    public void setBirthDay(myDate birthDay) { this.birthDay = birthDay; }

    public int getIncorrectNumber() { return incorrectNumber; }
    public void setIncorrectNumber(int incorrectNumber) { this.incorrectNumber = incorrectNumber; }

    public myDate getLastLogin() { return lastLogin; }
    public void setLastLogin(myDate lastLogin) { this.lastLogin = lastLogin; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    private void randomAccountNumber() {
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 11; i++) {
            int so = random.nextInt(10);
            builder.append(so);
        }
        this.accountNumber = builder.toString();
        System.out.println("Generated Account Number: " + this.accountNumber);
    }

    private void customAccountNumber() {
        boolean valid;
        do {
            valid = true;
            System.out.print("Enter your Account Number (11 digits): ");
            Scanner S = new Scanner(System.in);
            String accountNumberT = S.nextLine();

            if (accountNumberT.length() != 11) {
                System.out.println("It must be exactly 11 digits, please enter it again.");
                valid = false;
            } else {
                for (char token : accountNumberT.toCharArray()) {
                    if (token == ' ' || !Character.isDigit(token)) {
                        System.out.println("Account Number must only contain digits with no spaces!");
                        valid = false;
                        break;
                    }
                }
            }
            if (valid) {
                this.accountNumber = accountNumberT;
            } else {
                System.out.println("Do you want to continue? (press 1 for Yes, 0 for No)");
                int cont = S.nextInt();
                if (cont == 0) {
                    break;
                }
            }
        } while (!valid);
    }

    public boolean testPassWord(String passTest) {
        setIncorrectNumber(0);
        while (getIncorrectNumber() < 5) {
            if (passTest.equals(this.getPassWord())) {
                return true; // Password is correct
            } else {
                this.incorrectNumber++;
                System.out.print("Incorrect password, try again: ");
                Scanner S = new Scanner(System.in);
                passTest = S.nextLine(); // Re-enter password
            }
        }
        return false; // Password is incorrect after 5 attempts
    }

    public void input() {
        birthDay = new myDate();
        birthDay.enterDate();
        System.out.print("Enter email: ");
        this.setEmail(S.nextLine());
        System.out.print("Enter name: ");
        this.setName(S.nextLine());
        System.out.print("Enter phone number: ");
        this.setPhoneNumber(S.nextLine());
    }

    public void signup() {
        System.out.println("\tSignup\n");
        this.input();
        System.out.print("Enter your new password: ");
        this.setPassWord(S.nextLine());
    }

    public void viewInfo() {
        System.out.println("Account Number: " + this.getAccountNumber() + 
                           "\nBirthday: " + this.getBirthDay().displayDate() +
                           "\nName: " + this.getName() +
                           "\nID: " + this.getId() +
                           "\nEmail: " + this.getEmail() +
                           "\nPassword: " + this.getPassWord() +
                           "\nPhone Number: " + this.getPhoneNumber() +
                           "\nRole: " + this.getRole() +
                           "\nStatus: " + this.getStatus());
    }

    abstract boolean lockAccount();
    abstract String accountStatus();

    public void login() {
        System.out.println("\tLogin\nAccount Number: " + this.getAccountNumber() + 
                           "\nName: " + this.getName());
        lastLogin = new myDate();
        lastLogin.setNow(LocalDate.now());
    }
}
class customer extends bank {
    double balance;
    int accountType; // Sửa lỗi đánh vần
    int serviceAccount;
    double transactionFee;
    double monthlyFee;

    public void login() {
        super.login();
        System.out.println("Balance: " + this.getBalance());
    }

    public String spacesOfFile() {
        // Gọi phương thức của lớp cha
        String parentSpace = super.spacesOfFile();

        // Định dạng thêm các trường riêng của lớp customer
        return parentSpace + String.format("|  %-20.2f  |  %-20s  |  %-20s  |  %-20.2f  |  %-20.2f  |\n",
            this.getBalance(),
            this.getAccountType(),
            this.getServiceAccount(),
            this.getTransactionFee(),
            this.getMonthlyFee());
    }

    public void infile(String file) throws IOException {
        super.infile(file); // Gọi phương thức của lớp cha để ghi thông tin chung
        
        try (FileWriter fw = new FileWriter(new File(file), true)) { // Append = true để nối dữ liệu
            String data = this.getBalance() + "," +
                          this.getAccountType() + "," +
                          this.getServiceAccount() + "," +
                          this.getTransactionFee() + "," +
                          this.getMonthlyFee() + "\n"; // Thêm xuống dòng
            fw.write(data); // Ghi dữ liệu của lớp con 1 (customer)
        }
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        if (balance < 0) {
            System.out.println("Balance cannot be negative. Please enter a valid balance.");
            return; // Hoặc yêu cầu nhập lại
        }
        this.balance = balance;
    }

    public String getAccountType() { // Sửa lỗi logic
        switch (accountType) {
            case 1: // Tài khoản ngân hàng
                this.setMonthlyFee(0);
                this.setTransactionFee(10.0 / 100);
                return "Bank Account";
            case 2: // Thẻ tín dụng
                this.setMonthlyFee(500000);
                this.setTransactionFee(15.0 / 100);
                return "Credit Card";
            case 3: // Thẻ ngân hàng
                this.setMonthlyFee(65000);
                this.setTransactionFee(5.0 / 100);
                return "Debit Card";
            default:
                return "Unknown"; // Trả về giá trị mặc định
        }
    }

    public void setAccountType(int accountType) {
        while (accountType < 1 || accountType > 3) {
            System.out.print("ERROR! (1-3) enter again: ");
            Scanner S = new Scanner(System.in);
            while (!S.hasNextInt()) { // Kiểm tra đầu vào
                System.out.print("Invalid input. Enter a number (1-3): ");
                S.next(); // Clear invalid input
            }
            accountType = S.nextInt();
        }
        this.accountType = accountType;
    }

    public String getServiceAccount() { // Sửa lỗi logic
        switch (serviceAccount) {
            case 1: // Phổ thông
                return "Standard";
            case 2: // Thương mại
                return "Commercial";
            case 3: // VIP
                return "VIP";
            default:
                return "Unknown"; // Trả về giá trị mặc định
        }
    }

    public void setServiceAccount(int serviceAccount) {
        while (serviceAccount < 1 || serviceAccount > 3) {
            System.out.print("ERROR! (1-3) enter again: ");
            Scanner S = new Scanner(System.in);
            while (!S.hasNextInt()) { // Kiểm tra đầu vào
                System.out.print("Invalid input. Enter a number (1-3): ");
                S.next(); // Clear invalid input
            }
            serviceAccount = S.nextInt();
        }
        this.serviceAccount = serviceAccount;
    }

    public double getTransactionFee() {
        return transactionFee;
    }

    public void setTransactionFee(double transactionFee) {
        this.transactionFee = transactionFee;
    }

    public double getMonthlyFee() {
        return monthlyFee;
    }

    public void setMonthlyFee(double monthlyFee) {
        this.monthlyFee = monthlyFee;
    }

    public void input() {
        super.input();
        System.out.print("Enter account type (1-3): ");
        this.setAccountType(S.nextInt());
        System.out.print("Enter service account (1-3): ");
        this.setServiceAccount(S.nextInt());
        System.out.print("Enter balance: ");
        this.setBalance(S.nextDouble());
    }

    public void viewInfor() {
        super.viewInfo();
        System.out.println("Account Type: " + this.getAccountType() +
                           "\nService Account: " + this.getServiceAccount() +
                           "\nBalance: " + this.getBalance() +
                           "\nTransaction Fee: " + this.getTransactionFee() +
                           "\nMonthly Fee: " + this.getMonthlyFee());
    }

    public void signup() {
        super.signup();
    }
    @Override
    boolean lockAccount() {
        return this.getIncorrectNumber() == 5;
    }

    String accountStatus() {
        if (this.getIncorrectNumber() == 5) {
            return "BANNED";
        } else if (this.getIncorrectNumber() == 3) {
            return "SUSPENDED";
        } else {
            return "ACTIVE";
        }
    }


    
}
class employee extends bank {
    myDate startDate;
    double salary;
    double taxRate;
    double insuranceRate;
    int performanceRating;

    public String spacesOfFile() {
        // Gọi phương thức của lớp cha
        String parentSpace = super.spacesOfFile();

        // Định dạng thêm các trường riêng của lớp employee
        return parentSpace + String.format("|  %-20s  |  %-20.2f  |  %-20.2f  |  %-20.2f  |  %-10d  |\n",
             this.getStartDate().displayDate() ,
            this.getSalary(),
            this.getTaxRate(),
            this.getInsuranceRate(),
            this.getPerformanceRating());
    }

    public void infile(String file) throws IOException {
        super.infile(file); // Gọi phương thức của lớp cha để ghi thông tin chung
        
        try (FileWriter fw = new FileWriter(new File(file), true)) { // Append = true để nối dữ liệu
            String data = ( this.getStartDate().displayDate() ) + "," +
                          this.getSalary() + "," +
                          this.getTaxRate() + "," +
                          this.getInsuranceRate() + "," +
                          this.getPerformanceRating() + "\n"; // Thêm xuống dòng
            fw.write(data); // Ghi dữ liệu của lớp con 2 (employee)
        }
    }

    public int getPerformanceRating() {
        return performanceRating; // Trả về trực tiếp
    }

    public void setPerformanceRating(int performanceRating) {
        this.performanceRating = performanceRating;
    }

    public String disPER() {
        switch (performanceRating) {
            case 4:
                return "EXCELLENT";
            case 3:
                return "GOOD";
            case 2:
                return "AVERAGE";
            default:
                return "POOR";
        }
    }

    public myDate getStartDate() {
        return startDate;
    }

    public void setStartDate(myDate startDate) {
        this.startDate = startDate; // Chỉ gán giá trị
    }

    public double getSalary() {
        if (checkForSalaryIncreaseEligibility()) {
            return salary + 50000000; // Tăng lương nếu đủ điều kiện
        }
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary; // Chỉ gán giá trị
    }

    public double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    public double getInsuranceRate() {
        return insuranceRate;
    }

    public void setInsuranceRate(double insuranceRate) {
        this.insuranceRate = insuranceRate;
    }

    public double calSal() {
        return this.getSalary() + this.bonus() - this.getSalary() * this.getTaxRate();
    }

    public void login() {
        super.login();
        System.out.println("Start date: " + this.getStartDate());
    }

    public void viewInfor() {
        super.viewInfo();
        this.getStartDate().displayDate();
        System.out.println("Salary: " + this.getSalary() +
                           "\nTax: " + this.getTaxRate() +
                           "\nInsurance: " + this.getInsuranceRate());
    }

    public void signup() {
        super.signup();
    }
    @Override
    boolean lockAccount() {
        System.out.print("Lock account? (right or not): ");
        String co = S.nextLine();
        return co.equals("right"); // Sử dụng equals để so sánh
    }

    String accountStatus() {
        if (this.getLastLogin() != null && this.getLastLogin().calculateMonths() > 5) {
            return "BANNED"; // Trả về giá trị hằng số
        } else if (this.getLastLogin() != null && this.getLastLogin().calculateMonths() > 3) {
            return "SUSPENDED"; // Trả về giá trị hằng số
        } else {
            return "ACTIVE"; // Trả về giá trị hằng số
        }
    }

    public double bonus() {
        switch (this.getPerformanceRating()) {
            case 4: // Xuất sắc
                return 500000;
            case 2: // Tốt
                return 200000;
            case 1: // Kém
                return -100000;
            default: // Trung bình
                return 0;
        }
    }

    public void input() {
        super.input();
        startDate=new myDate();
        System.out.println("enter entry date: ");
        startDate.enterDate();
        System.out.print("Enter salary: ");
        double salary = S.nextDouble();
        while (salary < 0) { // Kiểm tra đầu vào
            System.out.print("Salary cannot be negative. Enter again: ");
            salary = S.nextDouble();
        }
        this.setSalary(salary);

        System.out.print("Enter Tax: ");
        double taxRate = S.nextDouble();
        while (taxRate < 0) { // Kiểm tra đầu vào
            System.out.print("Tax cannot be negative. Enter again: ");
            taxRate = S.nextDouble();
        }
        this.setTaxRate(taxRate);

        System.out.print("Enter performance rating: ");
        this.setPerformanceRating(S.nextInt());
        
        System.out.print("Enter insurance rate: ");
        double insuranceRate = S.nextDouble();
        while (insuranceRate < 0) { // Kiểm tra đầu vào
            System.out.print("Insurance rate cannot be negative. Enter again: ");
            insuranceRate = S.nextDouble();
        }
        this.setInsuranceRate(insuranceRate);
    }

    public boolean checkForSalaryIncreaseEligibility() {
        int currentYear = LocalDate.now().getYear(); /* lấy năm hiện tại */;
        if ((currentYear - this.getStartDate().getYear()) > 5 && this.getPerformanceRating() > 3) {
            return true;
        }
        return false;
    }

   

}class list {
    List<bank> users = new ArrayList<>();
    List<bank> lockAcc = new ArrayList<>();
    Scanner S = new Scanner(System.in);

    private boolean checkUsers() {
        return users.size() > 0;
    }

    private boolean checklockAccount() {
        return lockAcc.size() > 0;
    }

    public boolean LockAcc(bank obj) {
        if (obj instanceof customer && ((customer) obj).lockAccount()) {
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).equals(obj)) {
                    lockAcc.add(users.get(i));
                    users.remove(i);
                    return true;
                }
            }
        }
        return false;
    }

    public void LockAcc(String ID) {
        boolean have = false;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().contains(ID)) {
                have = true;
                users.get(i).viewInfo();
                System.out.print("Do you want to lock this account: (1:true): ");
                int choose = S.nextInt();
                S.nextLine(); // Clean buffer
                if (choose == 1) {
                    lockAcc.add(users.get(i));
                    users.remove(i);
                }
                break; // Để thoát khỏi vòng lặp sau khi tìm thấy
            }
        }
        if (!have) {
            System.out.println("This ID doesn't exist in the list");
        }
    }

    public void findID(String account) {
        boolean have = false;
        int cont;

        do {
            System.out.println("Do you want to find this ID from Lock Account List(1) or Active Account List(2): ");
            int choose = S.nextInt();
            S.nextLine(); // Clean buffer

            if (choose == 1) {
                if (checklockAccount()) {
                    for (bank accountLock : lockAcc) {
                        if (accountLock.getAccountNumber().contains(account)) {
                            have = true;
                            accountLock.viewInfo();
                        }
                    }
                }
            } else if (choose == 2) {
                if (checkUsers()) {
                    for (bank accountUser : users) {
                        if (accountUser.getAccountNumber().contains(account)) {
                            have = true;
                            accountUser.viewInfo();
                        }
                    }
                }
            } else {
                System.out.println("The choice is incorrect!");
            }

            if (!have) {
                System.out.println("This ID doesn't exist in the list");
            }
            System.out.print("Do you want to continue? (1 for yes): ");
            cont = S.nextInt();
            S.nextLine(); // Clean buffer
        } while (cont == 1);
    }

    Comparator<bank> sortBySal = new Comparator<bank>() {
        @Override
        public int compare(bank o1, bank o2) {
            return Double.compare(((employee) o1).calSal(), ((employee) o2).calSal());
        }
    };

    Comparator<bank> sortByType = new Comparator<bank>() {
        @Override
        public int compare(bank o1, bank o2) {
            return ((customer) o1).getAccountType().compareTo(((customer) o2).getAccountType());
        }
    };

    public void sort() {
        List<bank> emp = new ArrayList<>();
        List<bank> cus = new ArrayList<>();

        for (bank user : users) {
            if (user instanceof customer) {
                cus.add(user);
            } else {
                emp.add(user);
            }
        }

        while (true) {
            System.out.println("1. Sort all\n2. Sort the customer list\n3. Sort the employee list");
            int choose = S.nextInt();
            S.nextLine(); // Clean buffer

            if (choose == 1) {
                Collections.sort(users, Comparator.comparing(bank::getName)); // Sắp xếp theo tên
            } else if (choose == 2) {
                Collections.sort(cus, sortByType);
                System.out.println("Information after sort: ");
                for(int i=0;i<cus.size();i++){
                    cus.get(i).viewInfo();
                }
            } else if (choose == 3) {
                Collections.sort(emp, sortBySal);
                System.out.println("Information after sort: ");
                for(int i=0;i<cus.size();i++){
                    cus.get(i).viewInfo();
                }
            } else {
                break;
            }
        }
    }

    public boolean checkIDandAccountNumber(bank account) {
        boolean have = false;
        do {
            System.out.println("Enter your ID:");
            account.setId(S.nextLine());
            System.out.println("Enter your Account Number:");
            account.setAccountNumber(); // Đảm bảo có tham số đầu vào

            for (bank user : users) {
                if (user.getAccountNumber().contains(account.getAccountNumber()) && user.getId().contains(account.getId())) {
                    have = true;
                    System.out.println("This account already exists!");
                    break;
                }
            }
            if (have) {
                return true;
            } else {
                return false;
            }
        } while (have);
    }
    public void checkID(bank obj) {
        while (true) {
            boolean have = false;
            System.out.print("Enter your ID: ");
            String ID = S.nextLine();
            
            // Kiểm tra ID có tồn tại hay không
            if(checkUsers()){
                for (int i = 0; i < users.size(); i++) {
                    if (users.get(i).getId() != null && users.get(i).getId().equals(ID)) { // So sánh chính xác ID
                        have = true;
                        System.out.println("This ID already exists! Try again.");
                        break; // Thoát khỏi vòng lặp for khi tìm thấy ID trùng
                    }
                }
            }
    
            // Nếu không tìm thấy ID trùng, gán ID cho đối tượng và thoát vòng lặp
            if (!have) {
                obj.setId(ID);
                System.out.println("ID successfully set: " + obj.getId());
                break;
            }
        }
    }public void checkAN(bank obj) {
        while (true) {
            boolean have = false;
            obj.setAccountNumber(); // Nhập số tài khoản từ người dùng
            
            // Kiểm tra số tài khoản có trùng hay không
            if(checkUsers()){
                for (int i = 0; i < users.size(); i++) {
                    if (users.get(i).getAccountNumber().equals(obj.getAccountNumber())) { // So sánh chính xác
                        have = true;
                        System.out.println("This account number already exists! Try again.");
                        break; // Thoát khỏi vòng lặp for để yêu cầu nhập lại
                    }
                }
            }
    
            // Nếu không trùng, xuất số tài khoản và thoát vòng lặp
            if (!have) {
                System.out.println("Account number successfully set: " + obj.getAccountNumber());
                break;
            }
        }
    }
    
    
    public void inFile(String file) throws IOException {
        File fi = new File(file);
        try (FileWriter fw = new FileWriter(fi)) { // Ghi tiêu đề với chế độ ghi đè (write)
            String header = String.format("|  %-30s  |  %-50s  |  %-30s  |  %-15s  |  %-10s  |  %-10s  |  %-20s  |  %-10s  |  %-15s  |  %-20s  |  %-20s  |  %-20s  |  %-20s  |  %-20s  |\n",
                    "Name", "ID", "Email", "Account Number", "Password", "Birth Day", "Last Login", "Status", "Role", "Phone Number", "Balance", "Account Type", "Service Account", "Transaction Fee", "Monthly Fee");
            int length = header.length();
            String separator = new String(new char[length - 1]).replace("\0", "-") + "\n";
            fw.write(header); // Ghi tiêu đề
            fw.write(separator); // Ghi dấu phân cách tiêu đề và dữ liệu
    
            // Lặp qua danh sách user và ghi dữ liệu
            for (bank user : users) {
                fw.write(user.spacesOfFile());
                user.infile(file); // Gọi infile của từng đối tượng bank để ghi dữ liệu
            }
            fw.write(separator); // Đóng bảng
    
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void displayFileContent(String fileName) {
        File file = new File(fileName);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    

    public void unlock(String accountNumber) {
        boolean have = false;
        for (int i = 0; i < lockAcc.size(); i++) {
            if (lockAcc.get(i).getAccountNumber().contains(accountNumber)) {
                have = true;
                lockAcc.get(i).viewInfo();
                System.out.println("Do you want to unlock this account? (1 for true): ");
                int c = S.nextInt();
                S.nextLine(); // Clean buffer
                if (c == 1) {
                    users.add(lockAcc.get(i));
                    lockAcc.remove(i);
                }
                break; // Thoát khỏi vòng lặp sau khi tìm thấy
            }
        }
        if (!have) {
            System.out.println("Not found this account!");
        }
    }

    public void login(String ID) {
        boolean have = false;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(ID)) {
                System.out.print("Enter your password: ");
                String passwordInput = S.nextLine(); // Lấy đầu vào mật khẩu

                if (users.get(i).testPassWord(passwordInput)) {
                    have = true;
                    users.get(i).login(); // Đăng nhập thành công

                    if (users.get(i) instanceof customer) {
                        if (!this.LockAcc(users.get(i))) {
                            while (true) {
                                System.out.print("\tLOGIN\n1. Transfer\n2. Withdraw\n3. Find Account Number\n0. Exit\nEnter your choice: ");
                                int choice = S.nextInt();
                                S.nextLine(); // Clean buffer
                                switch (choice) {
                                    case 1:
                                        this.transfer((customer) users.get(i));
                                        break;
                                    case 2:
                                        this.withdraw((customer) users.get(i));
                                        break;
                                    case 3:
                                        System.out.print("Enter account number you want to find: ");
                                        this.findAcc(S.nextLine());
                                        break;
                                    case 0:
                                        return; // Thoát khỏi hàm
                                    default:
                                        System.out.println("Invalid choice!"); // Thông báo lựa chọn không hợp lệ
                                }
                            }
                        }
                    }
                } else {
                    System.out.println("Wrong password!"); // Thông báo mật khẩu sai
                }
                break; // Thoát khỏi vòng lặp sau khi tìm thấy
            }
        }
        if (!have) {
            System.out.println("Account number not found!"); // Thông báo không tìm thấy tài khoản
        }
    }

    // Add thêm các phương thức `withdraw`, `transfer`, `findAcc`, ...
    public void transfer(customer ojb){
		System.out.println("account number: ");
		String acc=S.nextLine();
		for(int i=0;i<users.size();i++){
			if(users.get(i) instanceof customer){
				if(users.get(i).getAccountNumber().contains(acc)){
					users.get(i).login();
					System.out.println("enter your trensfer: ");
					double trans=S.nextDouble();
					if(trans>ojb.getBalance()){
						System.out.println("your balance is not enough, try again!");
					}else{
						System.out.println("do you accept? (1==yes)");
						int chon=S.nextInt();
						if(chon==1){
							((customer) users.get(i)).setBalance(((customer) users.get(i)).getBalance()+trans);
							ojb.setBalance(ojb.getBalance()-trans-ojb.getTransactionFee());
							System.out.println("transfer is successful, your balance: "+ojb.getBalance());
						}else{
							break;
						}
					}
				}
			}
		}
	}
    public void withdraw(customer obj){
		System.out.println("Enter the amount to withdraw");
		double with=S.nextDouble();
		if(with<obj.getBalance()){
			System.out.println("do you accept? (1==yes)");
			int chon=S.nextInt();
			if(chon==1){
				obj.setBalance(obj.getBalance()-with);
				System.out.println("withdraw successful");
			}
		}
	}
    public void findAcc(String ID){
		boolean have=false;
		if(checkUsers()){
			for(int i=0;i<users.size();i++){
				if(users.get(i).getId().contains(ID)){
					users.get(i).viewInfo();
					have=true;
				}
			}
			if(!have){
				System.out.println("not found!");
			}
		}else{
			System.out.println("the list is empty!");
		}
	}
    public void showMenu() throws IOException {
        while (true) {
            System.out.println("=== Bank Management System ===");
            System.out.println("1. Sign Up");
            System.out.println("2. Login");
            System.out.println("3.sort\n4.save to file\n5.read to file");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = S.nextInt();
            S.nextLine(); // Xóa bộ đệm

            switch (choice) {
                case 1:
                    signup();
                    break;
                case 2:
                    login();
                    break;
                case 6:
                    System.out.println("Exiting the system. Goodbye!");
                    return;
                case 3:
                    this.sort();
                    break;
                case 4:
                    this.inFile("D:\\JAVA\\tailieu\\doancanhantext.txt");
                    break;
                case 5:
                    this.displayFileContent("D:\\JAVA\\tailieu\\doancanhantext.txt");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
    public void delete(String acn){
        boolean have=false;
        for(int i=0;i<users.size();i++){
            if(users.get(i).getAccountNumber().equals(acn)){
                have=true;
                if(users.get(i) instanceof customer){
                    System.out.println("Do you want to delete account?(1=yes) ");
                    int chon=S.nextInt();
                    if(chon==1){
                        users.remove(i);
                    }
                }else{
                    users.get(i).viewInfo();
                    System.out.println("Do you want to delete this account?(1=yes) ");
                    int chon=S.nextInt();
                    if(chon==1){
                        users.remove(i);
                    }
                }
            }
        }
        if(!have){
            System.out.println("this id is not found! try again");
        }
    }
    // Đăng ký tài khoản
    public void signup() {
        while (true){
            System.out.print("\tSignUp\n1. Employee\n2. Customer\n0.exit\nEnter your choice: ");
            int choice = S.nextInt();
            S.nextLine(); // Xóa bộ đệm
            
            bank account;
            if (choice == 1) {
                account = new employee();
                account.signup();
            } else if (choice == 2) {
                account = new customer();
                account.signup();
            }else if(choice==0){
                break;
            } else {
                System.out.println("Invalid choice! Please try again.");
                continue;
            } 
            this.checkID(account);
            this.checkAN(account); 
            users.add(account);

        } 
    }

    // Đăng nhập
    public void login() {
        System.out.print("Enter your account number: ");
        String accountNumber = S.nextLine();
        
        boolean found = false;
        for (bank user : users) {
            if (user.getAccountNumber().contains(accountNumber)) {
                System.out.print("Enter your password: ");
                String passwordInput = S.nextLine();
                
                if (user.testPassWord(passwordInput)) {
                    found = true;
                    user.login(); // Đăng nhập thành công
                    System.out.println("Welcome, " + user.getName() + "!");

                    // Thực hiện các chức năng sau khi đăng nhập thành công
                    if(user instanceof customer){
                        this.cusMenu(user);
                    }else{
                        this.empMenu(user);
                    }
                } else {
                    System.out.println("Wrong password!"); // Thông báo mật khẩu sai
                }
                break; // Thoát khỏi vòng lặp sau khi tìm thấy
            }
        }
        if (!found) {
            System.out.println("Account number not found!"); // Thông báo không tìm thấy tài khoản
        }
    }
    public void lockAccount(){
        for(int i=0;i<users.size();i++){
            if(users.get(i).lockAccount()){
                lockAcc.add(users.get(i));
                users.remove(i);
            }
        }
    }
    // Menu sau khi đăng nhập thành công
    private void cusMenu(bank user) {
        while (true) {
            System.out.println("1. Withdraw");
            System.out.println("2. Transfer");
            System.out.println("3. Find Account");
            System.out.println("4. delete Account");
            System.out.println("5. Logout");
            System.out.print("Enter your choice: ");
            int choice = S.nextInt();
            S.nextLine(); // Xóa bộ đệm

            switch (choice) {
                case 1:
                    this.withdraw((customer)user); // Rút tiền
                    break;
                case 2:
                    this.transfer((customer)user); // Chuyển khoản
                    break;
                case 3:
                    System.out.println("enter your id do you want to find: ");
                    this.findAcc(S.nextLine()); // tìm tài khoản
                    break;
                case 4:
                    this.delete(user.getAccountNumber());
                    break;
                case 5:
                    System.out.println("Logout successfully!"); // Thoát đăng nhập
                    return; // Thoát khỏi phương thức
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
    private void empMenu(bank user) {
        while (true) {
            System.out.println("1. Lock Account");
            System.out.println("2. Find Account");
            System.out.println("3. Unlock Account");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");
            int choice = S.nextInt();
            S.nextLine(); // Xóa bộ đệm

            switch (choice) {
                case 2:
                    System.out.println("enter id to find: ");
                    this.findAcc(S.nextLine());// Tìm tài khoản
                    break;
                case 3:
                System.out.print("enter your id do you want to unlock: ");
                    this.unlock(S.nextLine());; // mở khóa
                    break;
                case 1:
                    this.lockAccount(); // Khóa tài khoản
                    break;
                case 4:
                    System.out.println("enter your account number do you want to delete: ");
                    this.delete(S.nextLine());
                    break;
                case 5:
                    System.out.println("Logout successfully!"); // Thoát đăng nhập
                    return; // Thoát khỏi phương thức
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
    //nạp tiền
    public void deposit(bank obj){
        for(int i=0;i<users.size();i++){
            if(users.get(i).getAccountNumber().equals(obj.getAccountNumber())){
                System.out.println("enter you money do you want to deposit: ");
                ((customer)obj).setBalance(S.nextInt());
                System.out.println("Deposit is successfully!");
            }
        }
    }
}

public class doan {
    public static void main(String[] args) throws IOException {
        list l=new list();
        l.showMenu();
    }
}
