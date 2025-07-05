import cart.Cart;
import core.Customer;
import order.OrderProcessor;
import product.Cheese;
import product.MobileScratchCard;
import product.TV;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Normal case
        System.out.println("=== Normal Checkout ===");
        Cheese cheese = new Cheese("Cheese", 100, 5, LocalDate.of(2025, 7, 10), 200);
        TV tv = new TV("TV", 500, 3, 5000);
        MobileScratchCard scratchCard = new MobileScratchCard("Scratch Card", 10, 100);
        Customer customer = new Customer(1000);
        Cart cart = new Cart();

        try {
            cart.add(cheese, 2);
            cart.add(tv, 1);
            cart.add(scratchCard, 1);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        OrderProcessor processor = new OrderProcessor();
        processor.processOrder(customer, cart, LocalDate.of(2025, 7, 5));

        // Corner case: Empty cart
        System.out.println("\n=== Empty Cart ===");
        Cart emptyCart = new Cart();
        processor.processOrder(customer, emptyCart, LocalDate.of(2025, 7, 5));

        // Corner case: Insufficient balance
        System.out.println("\n=== Insufficient Balance ===");
        Customer poorCustomer = new Customer(100);
        Cart cart2 = new Cart();
        try {
            cart2.add(cheese, 2);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        processor.processOrder(poorCustomer, cart2, LocalDate.of(2025, 7, 5));

        // Corner case: Out of stock
        System.out.println("\n=== Out of Stock ===");
        Cart cart3 = new Cart();
        try {
            cart3.add(cheese, 6); // Only 5 available
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        // Corner case: Expired product
        System.out.println("\n=== Expired Product ===");
        Cheese expiredCheese = new Cheese("Expired Cheese", 100, 5, LocalDate.of(2025, 7, 4), 200);
        Cart cart4 = new Cart();
        try {
            cart4.add(expiredCheese, 1);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        processor.processOrder(customer, cart4, LocalDate.of(2025, 7, 5));
    }
}