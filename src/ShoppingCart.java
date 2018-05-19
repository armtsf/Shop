import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<OrderedItem> items;
    private int id;

    public ShoppingCart(int id) {
        this.items = new ArrayList<>();
        this.id = id;
    }

    public List<OrderedItem> getItems() {
        return items;
    }

    public void setItems(List<OrderedItem> items) {
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addToCart(OrderedItem newItem) {
        items.add(newItem);
    }
}
