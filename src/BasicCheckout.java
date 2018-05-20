import java.util.List;

public class BasicCheckout implements Checkout {

    @Override
    public double calculateTotal(ShoppingCart cart) {
        double total = 0;
        List<OrderedItem> items = cart.getItems();
        for (OrderedItem currItem : items) {
            total += currItem.getCount() * currItem.getProduct().getPrice();
        }
        return total;
    }

}
