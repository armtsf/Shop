import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class Shop {
    private List<Product> products;
    private ShoppingCart cart;

    public Shop() {
        this.products = Product.getProducts();
        this.cart = new ShoppingCart(1) ;
    }

    public List<Product> getProducts() {
        return products;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public Product findProduct(int id) {
        for (Product product : products) {
            if (product.getId() == id)
                return product;
        }
        throw new NoSuchElementException();
    }

    public void buy(int id, int count) {
        Product newProduct = findProduct(id);
        OrderedItem newItem = new OrderedItem(count, newProduct);
        cart.addToCart(newItem);
    }

}
