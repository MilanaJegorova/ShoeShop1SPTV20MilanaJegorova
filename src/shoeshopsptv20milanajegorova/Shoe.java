package shoeshopsptv20milanajegorova;

public class Shoe {
    private String name;
    private int price;

    public Shoe(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Shoe{" + "name=" + name + ", price=" + price + '}';
    }
    
}
