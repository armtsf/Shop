import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;
import java.util.NoSuchElementException;

public class Shop {
    private List<Product> products;
    private ShoppingCart cart;
    private Connection conn = null;

    public Shop() {
        this.cart = new ShoppingCart(1) ;
        try {
            Class.forName("org.sqlite.JDBC");
            this.conn = DriverManager.getConnection("jdbc:sqlite:test.db");
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        this.products = Product.getProducts(conn);
    }

    public List<Product> getProducts() {
        return products;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public Product findProduct(int id) throws NoSuchElementException {
        for (Product product : products) {
            if (product.getId() == id)
                return product;
        }
        throw new NoSuchElementException();
    }

    public void buy(int id, int count) throws NoSuchElementException {
        Product newProduct = findProduct(id);
        OrderedItem newItem = new OrderedItem(count, newProduct);
        cart.addToCart(newItem);
    }

    public Connection getConn() {
        return this.conn;
    }

    public void finalizePurchase() {
        try {
            Statement stmt = conn.createStatement();

            List<OrderedItem> list = this.cart.getItems();

            for (OrderedItem item : list) {
                int productId = item.getProduct().getId();
                int purchaseId = this.cart.getId();
                int count = item.getCount();
                String sql = String.format("INSERT INTO ORDEREDITEM (prid, puid, COUNT) VALUES (%d, %d, %d);" ,
                        productId, purchaseId, count);
                stmt.executeUpdate(sql);
            }

            BasicCheckout pricingPolicy = new BasicCheckout();
            double total = pricingPolicy.calculateTotal(this.cart);
            String sql = String.format("INSERT INTO PURCHASE (ID, PRICE) VALUES (%d, %f);" ,
                    cart.getId(), total);
            stmt.executeUpdate(sql);

            stmt.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
