import java.util.ArrayList;
import java.util.List;

public class Product {
    private String name;
    private double price;
    private int id;

    public Product(String name, double price, int id) {
        this.name = name;
        this.price = price;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //DATABASE
    public static List<Product> getProducts() {
        Product a = new Product("monitor", 20, 1);
        Product b = new Product("TV", 50, 2);
        Product c = new Product("Laptop", 500, 3);
        List<Product> l = new ArrayList<>();
        l.add(a);
        l.add(b);
        l.add(c);
        return l;
    }
}
