package shoeshopsptv20milanajegorova;

public class Customer {
    private String name;
    private String surname;
    private int money;
    private String phone;
    private String email;

    public Customer(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Customer(String name, String surname, int money) {
        this.name = name;
        this.surname = surname;
        this.money = money;
    }

    public Customer(String name, String surname, int money, String phone, String email) {
        this.name = name;
        this.surname = surname;
        this.money = money;
        this.phone = phone;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Customer{" + "name=" + name + ", surname=" + surname + ", money=" + money + ", phone=" + phone + ", email=" + email + '}';
    } 
    
}
