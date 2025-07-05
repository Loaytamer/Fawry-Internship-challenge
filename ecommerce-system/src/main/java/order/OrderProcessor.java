package order;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import cart.Cart;
import cart.CartItem;
import core.Customer;
import core.Product;
import shipping.ProductShipmentItem;
import shipping.Shippable;
import shipping.ShippableItem;
import shipping.ShippingService;
import util.Expirable;

public class OrderProcessor {
    private ShippingService shippingService = new ShippingService();

    public void processOrder(Customer customer, Cart cart, LocalDate currentDate) {
        List<CartItem> items = cart.getItems();
        if (items.isEmpty()) {
            System.out.println("Error: Cart is empty");
            return;
        }

        for (CartItem item : items) {
            Product product = item.getProduct();
            if (item.getQuantity() > product.getQuantityAvailable()) {
                System.out.println("Error: Product " + product.getName() + " is out of stock");
                return;
            }
            if (product instanceof Expirable) {
                Expirable expirable = (Expirable) product;
                if (expirable.getExpirationDate().isBefore(currentDate)) {
                    System.out.println("Error: Product " + product.getName() + " is expired");
                    return;
                }
            }
        }

        double subtotal = 0;
        for (CartItem item : items) {
            subtotal += item.getProduct().getPrice() * item.getQuantity();
        }

        double shippingFees = 30; // Flat rate assumption
        double paidAmount = subtotal + shippingFees;

        if (customer.getBalance() < paidAmount) {
            System.out.println("Error: Insufficient balance");
            return;
        }

        customer.deductBalance(paidAmount);

        for (CartItem item : items) {
            Product product = item.getProduct();
            product.setQuantityAvailable(product.getQuantityAvailable() - item.getQuantity());
        }

        List<ShippableItem> shippableItems = new ArrayList<>();
        for (CartItem item : items) {
            Product product = item.getProduct();
            if (product instanceof Shippable) {
                Shippable shippable = (Shippable) product;
                for (int i = 0; i < item.getQuantity(); i++) {
                    shippableItems.add(new ProductShipmentItem(product.getName(), shippable.getWeight()));
                }
            }
        }

        if (!shippableItems.isEmpty()) {
            shippingService.ship(shippableItems);
        }

        System.out.println("** Checkout receipt **");
        for (CartItem item : items) {
            System.out.println(item.getQuantity() + "x " + item.getProduct().getName() + " " + item.getProduct().getPrice());
        }
        System.out.println("----------------------");
        System.out.println("Subtotal " + subtotal);
        System.out.println("Shipping " + shippingFees);
        System.out.println("Amount " + paidAmount);
        System.out.println("Customer current balance: " + customer.getBalance());
    }
}
