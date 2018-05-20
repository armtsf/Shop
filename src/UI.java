import java.util.List;
import java.util.Scanner;

public class UI {
    private Shop shop;
    private BasicCheckout pricingPolicy;

    public UI() {
        this.shop = new Shop();
        this.pricingPolicy = new BasicCheckout();
    }

    public void showProducts() {
        System.out.println("Welcome! Choose from the list below:");
        System.out.println();
        List<Product> products = this.shop.getProducts();
        for (Product product : products) {
            System.out.print(product.getId());
            System.out.print("- ");
            System.out.print(product.getName());
            System.out.print(" ");
            System.out.println(product.getPrice());
        }
        System.out.println("0. Exit");
        System.out.println();
    }

    public void getInput() {
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("Enter your choice:");
            int id = in.nextInt();
            if (id == 0)
                break;
            System.out.println("Enter number of products:");
            int count = in.nextInt();
            shop.buy(id, count);
            System.out.println();
        }
        System.out.println();
    }

    public void showFinalCart() {
        List<OrderedItem> shoppingList = this.shop.getCart().getItems();
        System.out.println("Your shopping cart:");
        for (OrderedItem item : shoppingList) {
            System.out.print(item.getProduct().getName());
            System.out.print(" * ");
            System.out.println(item.getCount());
        }
        System.out.print("Total: ");
        System.out.println(this.pricingPolicy.calculateTotal(this.shop.getCart()));
    }

    public static void main(String[] argc) {
        UI ui = new UI();
        ui.showProducts();
        ui.getInput();
        ui.showFinalCart();
    }

}
