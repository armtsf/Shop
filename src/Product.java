import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
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
    public static List<Product> getProducts(Connection conn) {
//        Product a = new Product("monitor", 20, 1);
//        Product b = new Product("TV", 50, 2);
//        Product c = new Product("Laptop", 500, 3);
//        List<Product> l = new ArrayList<>();
//        l.add(a);
//        l.add(b);
//        l.add(c);
        try {
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery( "SELECT * FROM PRODUCT;" );
            List<Product> l = new ArrayList<>();

            while (res.next()) {
                int id = res.getInt("id");
                String name = res.getString("name");
                double price = res.getDouble("price");
                Product product = new Product(name, price, id);
                l.add(product);
            }
            return l;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
